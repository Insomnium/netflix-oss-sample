package com.epam.hw.netflix

import com.epam.hw.netflix.repository.DummyEmployees
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.cloud.netflix.eureka.EnableEurekaClient
import org.springframework.cloud.netflix.feign.EnableFeignClients


@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EnableConfigurationProperties(DummyEmployees)
class EmployeesApplication {
    static void main(String[] args) {
        SpringApplication.run(EmployeesApplication, args)
    }
}
