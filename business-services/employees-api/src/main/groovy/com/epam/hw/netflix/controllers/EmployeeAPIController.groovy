package com.epam.hw.netflix.controllers

import com.epam.hw.netflix.api.WorkspaceAPI
import com.epam.hw.netflix.services.EmployeeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/employees")
class EmployeeAPIController {

    @Autowired
    EmployeeService employeeService

    @Autowired
    WorkspaceAPI workspaceAPIClient

    @RequestMapping("/{id}")
    def describeEmployee(@PathVariable("id") String id) {
        def employee = employeeService.findEmployee(id)

        [
                id       : employee.id,
                firstName: employee.firstName,
                lastName : employee.lastName,
                email    : employee.email,
                workspace: workspaceAPIClient.getWorkspaceById(employee.workspaceId)
        ]
    }
}
