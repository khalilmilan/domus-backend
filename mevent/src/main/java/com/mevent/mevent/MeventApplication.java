package com.mevent.mevent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableConfigurationProperties
@EnableDiscoveryClient
@EnableFeignClients
//@SpringBootApplication(scanBasePackages = {"com.mevent.mevent.controller","com.mevent.mevent.configurations","com.mevent.mevent.model"})
@SpringBootApplication
public class MeventApplication {

	public static void main(String[] args) {
		SpringApplication.run(MeventApplication.class, args);
	}

}
