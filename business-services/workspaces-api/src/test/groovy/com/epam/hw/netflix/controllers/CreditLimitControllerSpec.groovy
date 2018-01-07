package com.epam.hw.netflix.controllers

import com.epam.hw.netflix.api.CreditLimitAPIClient
import com.epam.hw.netflix.domain.Limit
import com.epam.hw.netflix.repository.CreditLimitRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext

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
class CreditLimitControllerSpec extends BaseIntegrationSpec {

    @Autowired
    WebApplicationContext context

    @Autowired
    CreditLimitRepo limitRepo

    MockMvc mockMvc

    def setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build()
        limitRepo.save([
                Limit.builder().cardNumber('01234567890000000000').limit(2499999).currencyCode('EUR').build(),
                Limit.builder().cardNumber('01234567890000000001').limit(10000000).currencyCode('RUR').build()
        ])
    }

    def cleanup() {
        limitRepo.deleteAll()
    }

    def 'Successfully get card limit by card number'() {
        when: 'Request limit by card number'
        def response = mockMvc.perform(post(CreditLimitAPIClient.LIMIT_BY_CARD_NUMBER_URI, '01234567890000000000')
                .accept(APPLICATION_JSON))

        then: 'Card limit received'
        response.andExpect(status().isOk())
                .andExpect(jsonPath('$.limit', equalTo(2499999)))
                .andExpect(jsonPath('$.currencyCode', equalTo('EUR')))
    }

    def 'Get 404 status if limit not found by card number'() {
        when: 'Request card limit for non-existing card'
        def response = mockMvc.perform(post(CreditLimitAPIClient.LIMIT_BY_CARD_NUMBER_URI, '01234567890000000010')
                .accept(APPLICATION_JSON))

        then: 'Receive 404 status'
        response.andExpect(status().isNotFound())
    }
}
