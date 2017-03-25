package com.epam.hw.netflix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@SpringBootApplication
@EnableEurekaClient
public class WorkplacesApplication {

	public static void main(String[] args) {
		SpringApplication.run(WorkplacesApplication.class, args);
	}
}
