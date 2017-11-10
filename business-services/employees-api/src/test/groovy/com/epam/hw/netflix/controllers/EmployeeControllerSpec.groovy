package com.epam.hw.netflix.controllers

import com.epam.hw.netflix.api.WorkspaceAPI
import com.epam.hw.netflix.domain.Workspace
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import spock.lang.Specification
import spock.lang.Unroll

import static com.epam.hw.netflix.domain.OSFamily.WINDOWS
import static org.hamcrest.Matchers.equalTo
import static org.mockito.Mockito.when
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
class EmployeeControllerSpec extends Specification {

    @Autowired
    WebApplicationContext context

    @MockBean
    WorkspaceAPI workspaceAPIClient

    MockMvc mockMvc

    def setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build()
    }

    def 'Successfully get full employee info'() {
        given: 'There is a workspace for employee'
        when(workspaceAPIClient.getWorkspaceById('007')) thenReturn Workspace.builder()
                .id('007')
                .unit(1)
                .seat(3)
                .serialNumber('XXXXXXXXXXXXXXXXXXXXXXXXXXX')
                .osFamily(WINDOWS)
                .build()
        when: 'Request employee by id'
        def response = mockMvc.perform(get('/{id}/describe', '00001')
                .accept(APPLICATION_JSON))
        then: 'Full employee info received'
        response.andExpect(status().isOk())
            .andExpect(jsonPath('$.id', equalTo('00001')))
            .andExpect(jsonPath('$.firstName', equalTo('Ivanov')))
            .andExpect(jsonPath('$.lastName', equalTo('Ivan')))
            .andExpect(jsonPath('$.email', equalTo('IIvanov@mail.org')))
            .andExpect(jsonPath('$.workspace.id', equalTo('007')))
            .andExpect(jsonPath('$.workspace.unit', equalTo(1)))
            .andExpect(jsonPath('$.workspace.seat', equalTo(3)))
            .andExpect(jsonPath('$.workspace.osFamily', equalTo('WINDOWS')))
            .andExpect(jsonPath('$.workspace.serialNumber', equalTo('XXXXXXXXXXXXXXXXXXXXXXXXXXX')))
    }

    def 'Successfully get employee'() {
        given: 'There is a workspace for employee'
        when(workspaceAPIClient.getWorkspaceById('007')) thenReturn Workspace.builder()
                .id('007')
                .unit(1)
                .seat(3)
                .serialNumber('XXXXXXXXXXXXXXXXXXXXXXXXXXX')
                .osFamily(WINDOWS)
                .build()
        when: 'Request employee by id'
        def response = mockMvc.perform(get('/{id}', '00001')
                .accept(APPLICATION_JSON))
        then: 'Employee received'
        response.andExpect(status().isOk())
            .andExpect(jsonPath('$.id', equalTo('00001')))
            .andExpect(jsonPath('$.firstName', equalTo('Ivanov')))
            .andExpect(jsonPath('$.lastName', equalTo('Ivan')))
            .andExpect(jsonPath('$.email', equalTo('IIvanov@mail.org')))
            .andExpect(jsonPath('$.workspaceId', equalTo('007')))
    }

    @Unroll
    def 'Get 404 status if no employee found #description'() {
        when: 'Request non-existent employee full info'
        def response = mockMvc.perform(get(uri, '00999')
                .accept(APPLICATION_JSON))
        then: 'Receive 404 status'
        response.andExpect(status().isNotFound())
        where:
        uri                 | description
        '/{id}/describe'    | 'by full info request'
        '/{id}'             | ''
    }
}
