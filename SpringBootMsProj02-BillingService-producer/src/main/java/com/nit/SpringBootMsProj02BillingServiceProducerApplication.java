package com.nit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class SpringBootMsProj02BillingServiceProducerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootMsProj02BillingServiceProducerApplication.class, args);
	}

}
