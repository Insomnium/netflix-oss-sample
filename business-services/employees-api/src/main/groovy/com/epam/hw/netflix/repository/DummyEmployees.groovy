package com.epam.hw.netflix.repository

import com.epam.hw.netflix.domain.Employee
import groovy.transform.CompileStatic
import org.springframework.boot.context.properties.ConfigurationProperties

@CompileStatic
@ConfigurationProperties("employees")
class DummyEmployees {
    List<Employee> dummy
}