package net.ins.hw.netflix;

import net.ins.hw.netflix.dummy.DummyLimits;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@SpringBootApplication
@EnableEurekaClient
@EnableConfigurationProperties(DummyLimits.class)
public class CreditLimitApplication {

	public static void main(String[] args) {
		SpringApplication.run(CreditLimitApplication.class, args);
	}
}
