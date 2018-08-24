package net.ins.hw.netflix

import net.ins.hw.netflix.dummy.DummyOrders
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.cloud.netflix.eureka.EnableEurekaClient
import org.springframework.cloud.netflix.feign.EnableFeignClients

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EnableConfigurationProperties(DummyOrders)
class OrderApplication {
    static void main(String[] args) {
        SpringApplication.run(OrderApplication, args)
    }
}
