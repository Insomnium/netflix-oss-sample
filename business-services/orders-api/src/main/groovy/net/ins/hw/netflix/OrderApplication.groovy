package net.ins.hw.netflix


import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.netflix.eureka.EnableEurekaClient
import org.springframework.cloud.netflix.feign.EnableFeignClients


@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
class OrderApplication {
    static void main(String[] args) {
        SpringApplication.run(OrderApplication, args)
    }
}
