package com.epam.hw.netflix.controllers

import com.epam.hw.netflix.api.CreditLimitAPIClient
import com.epam.hw.netflix.domain.Amount
import com.epam.hw.netflix.domain.CreditLimit
import com.epam.hw.netflix.domain.Currency
import com.epam.hw.netflix.domain.Order
import com.epam.hw.netflix.repository.OrdersRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.ActiveProfiles

import static com.epam.hw.netflix.domain.CreditLimitStatus.APPROVED
import static com.epam.hw.netflix.domain.CreditLimitStatus.EXCEEDED
import static com.epam.hw.netflix.domain.OrderStatus.DRAFT
import static groovy.json.JsonOutput.toJson
import static org.hamcrest.Matchers.notNullValue
import static org.mockito.Matchers.eq
import static org.mockito.Mockito.when
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import static org.springframework.http.MediaType.APPLICATION_JSON
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@ActiveProfiles("test")
@SpringBootTest(
        properties = [
                'spring.cloud.discovery.enabled=false',
                'spring.cloud.config.enabled=false'
        ],
        webEnvironment = RANDOM_PORT
)
class OrderControllerSpec extends BaseIntegrationSpec {

    @Autowired
    OrdersRepo ordersRepo

    @MockBean
    CreditLimitAPIClient creditLimitAPIClient

    def setup() {
        ordersRepo.deleteAll()
    }

    def 'Successfully create new order if credit limit not exceeded'() {
        given: 'request for new order'
        def order = new Order(null, "0000001", "12345678901234567890", new Amount(2500000, new Currency('EUR', 100)), DRAFT)
        when(creditLimitAPIClient.getCreditLimit(eq(order.cardNumber), eq(order.amount))) thenReturn new CreditLimit(APPROVED)

        when: 'request sent to order service'
        def response = mockMvc.perform(post("/")
                .contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON)
                .content(toJson(order))
        )

        then: 'Order created'
        response.andExpect(status().isCreated())
                .andExpect(jsonPath('$.id', notNullValue()))

        def orders = ordersRepo.findAll()
        orders.size() == 1
        orders[0].cardNumber == order.cardNumber
        orders[0].amount == order.amount
        orders[0].ownerId == order.ownerId
        orders[0].status == order.status
    }

    def 'Fail creating new order if credit limit exceeded'() {
        given: 'request for new order'
        def order = new Order(null, "0000001", "12345678901234567890", new Amount(2500000, new Currency('EUR', 100)), DRAFT)
        when(creditLimitAPIClient.getCreditLimit(eq(order.cardNumber), eq(order.amount))) thenReturn new CreditLimit(EXCEEDED)

        when: 'request sent to order service'
        def response = mockMvc.perform(post("/")
                .contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON)
                .content(toJson(order))
        )

        then: 'Order was not created'
        response.andExpect(status().isNotAcceptable())
        ordersRepo.count() == 0
    }
}
