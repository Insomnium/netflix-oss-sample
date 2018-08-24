package net.ins.hw.netflix

import net.ins.hw.netflix.api.CreditLimitAPIClient
import net.ins.hw.netflix.domain.Amount
import net.ins.hw.netflix.domain.Currency
import net.ins.hw.netflix.domain.Limit
import net.ins.hw.netflix.repository.CreditLimitRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import spock.lang.Specification
import spock.lang.Unroll

import static groovy.json.JsonOutput.toJson
import static net.ins.hw.netflix.domain.CreditLimitStatus.APPROVED
import static net.ins.hw.netflix.domain.CreditLimitStatus.EXCEEDED
import static org.hamcrest.Matchers.equalTo
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
class CreditLimitControllerSpec extends Specification {

    @Autowired
    WebApplicationContext context

    @Autowired
    CreditLimitRepo limitRepo

    MockMvc mockMvc

    def setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build()
        limitRepo.deleteAll()
    }

    @Unroll
    def 'Check card limit #description'() {
        given: 'There is a limit for card'
        limitRepo.save Limit.builder().cardNumber('01234567890000000000').limit(500000).currencyCode('EUR').build()
        when: 'Check status for a card with limit above'

        def response = mockMvc.perform(post(CreditLimitAPIClient.LIMIT_BY_CARD_NUMBER_URI, '01234567890000000000')
                .contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON)
                .content(toJson(new Amount(amount, new Currency('EUR', 100))))
        )

        then: 'Card limit checked correctly'
        response.andExpect(status().isOk())
                .andExpect(jsonPath('$.status', equalTo(expectedStatus.toString())))

        where:
        amount | expectedStatus | description
        499999 | APPROVED       | 'not exceeded'
        500000 | APPROVED       | 'fits exactly'
        500001 | EXCEEDED       | 'exceeded'

    }

    def 'Check limit approved when it is not set'() {
        when: 'Check status for card with no limit'
        def response = mockMvc.perform(post(CreditLimitAPIClient.LIMIT_BY_CARD_NUMBER_URI, '01234567890000000010')
                .contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON)
                .content(toJson(new Amount(1000, new Currency('EUR', 100))))
        )

        then: 'Receive 404 status'
        response.andExpect(status().isOk())
                .andExpect(jsonPath('$.status', equalTo(APPROVED.toString())))
    }
}
