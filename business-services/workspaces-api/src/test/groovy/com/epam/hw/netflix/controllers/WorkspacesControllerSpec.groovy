package com.epam.hw.netflix.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import spock.lang.Specification

import static com.epam.hw.netflix.controllers.WorkspaceAPIController.ROOT_URI
import static com.epam.hw.netflix.controllers.WorkspaceAPIController.WORKSPACE_ID_URI
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
class WorkspacesControllerSpec extends Specification {

    @Autowired
    WebApplicationContext context

    MockMvc mockMvc

    def setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build()
    }

    def 'Successfully get all workspaces'() {
        when: 'Request all workspaces'
        def response = mockMvc.perform(get(ROOT_URI)
                .accept(APPLICATION_JSON))

        then: 'All workspaces received'
        response.andExpect(status().isOk())
            .andExpect(jsonPath('$.[0].id', equalTo('0000001')))
            .andExpect(jsonPath('$.[0].unit', equalTo(1)))
            .andExpect(jsonPath('$.[0].seat', equalTo(1)))
            .andExpect(jsonPath('$.[0].serialNumber', equalTo('d91f00a5-b5a2-48ce-8c02-71ee518d8407')))
            .andExpect(jsonPath('$.[0].osFamily', equalTo('WINDOWS')))
            .andExpect(jsonPath('$.[1].id', equalTo('0000002')))
            .andExpect(jsonPath('$.[1].unit', equalTo(1)))
            .andExpect(jsonPath('$.[1].seat', equalTo(2)))
            .andExpect(jsonPath('$.[1].serialNumber', equalTo('531b3b85-b628-44d9-85db-3aae6dbff523')))
            .andExpect(jsonPath('$.[1].osFamily', equalTo('LINUX')))
    }

    def 'Successfully get workspace by id'() {
        when: 'Request workspace by id'
        def response = mockMvc.perform(get(WORKSPACE_ID_URI, '0000001')
                .accept(APPLICATION_JSON))

        then: 'Received workspace'
        response.andExpect(status().isOk())
                .andExpect(jsonPath('$.id', equalTo('0000001')))
                .andExpect(jsonPath('$.unit', equalTo(1)))
                .andExpect(jsonPath('$.seat', equalTo(1)))
                .andExpect(jsonPath('$.serialNumber', equalTo('d91f00a5-b5a2-48ce-8c02-71ee518d8407')))
                .andExpect(jsonPath('$.osFamily', equalTo('WINDOWS')))
    }

    def 'Get 404 status if workspace not found'() {
        when: 'Request non-existent workspace by id'
        def response = mockMvc.perform(get(WORKSPACE_ID_URI, '0000003')
                .accept(APPLICATION_JSON))

        then: 'Receive 404 status'
        response.andExpect(status().isNotFound())
    }
}
