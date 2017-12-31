package com.epam.hw.netflix;

import com.epam.hw.netflix.repository.DummyCreditLimits;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
@EnableConfigurationProperties(DummyCreditLimits.class)
public class CreditLimitApplication {

	public static void main(String[] args) {
		SpringApplication.run(CreditLimitApplication.class, args);
	}
}
