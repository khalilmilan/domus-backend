package com.rank.mrank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableConfigurationProperties
@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
public class MrankApplication {

	public static void main(String[] args) {
		SpringApplication.run(MrankApplication.class, args);
	}

}
