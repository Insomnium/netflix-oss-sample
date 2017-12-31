package com.epam.hw.netflix.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import spock.lang.Specification

import static com.epam.hw.netflix.controllers.CreditLimitAPIClientController.ROOT_URI
import static com.epam.hw.netflix.controllers.CreditLimitAPIClientController.LIMIT_BY_CARD_NUMBER_URI
import static org.hamcrest.Matchers.equalTo
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import static org.springframework.http.MediaType.APPLICATION_JSON
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
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

    MockMvc mockMvc

    def setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build()
    }

    def 'Successfully get all card limits'() {
        when: 'Request limits for all the cards'
        def response = mockMvc.perform(get(ROOT_URI)
                .accept(APPLICATION_JSON))

        then: 'Limits received'
        response.andExpect(status().isOk())
                .andExpect(jsonPath('$.01234567890000000000.limit', equalTo(2499999.99d)))
                .andExpect(jsonPath('$.01234567890000000000.currencyCode', equalTo('EUR')))
                .andExpect(jsonPath('$.01234567890000000001.limit', equalTo(10000000.0d)))
                .andExpect(jsonPath('$.01234567890000000001.currencyCode', equalTo('RUR')))

    }

    def 'Successfully get card limit by card number'() {
        when: 'Request limit by card number'
        def response = mockMvc.perform(get(LIMIT_BY_CARD_NUMBER_URI, '01234567890000000000')
                .accept(APPLICATION_JSON))

        then: 'Card limit received'
        response.andExpect(status().isOk())
                .andExpect(jsonPath('$.limit', equalTo(2499999.99d)))
                .andExpect(jsonPath('$.currencyCode', equalTo('EUR')))
    }

    def 'Get 404 status if limit not found by card number'() {
        when: 'Request card limit for non-existing card'
        def response = mockMvc.perform(get(LIMIT_BY_CARD_NUMBER_URI, '01234567890000000010')
                .accept(APPLICATION_JSON))

        then: 'Receive 404 status'
        response.andExpect(status().isNotFound())
    }
}
