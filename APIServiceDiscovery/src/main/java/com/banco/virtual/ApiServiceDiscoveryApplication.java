package com.banco.virtual;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
@EnableEurekaServer
@SpringBootApplication
public class ApiServiceDiscoveryApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiServiceDiscoveryApplication.class, args);
	}

}
