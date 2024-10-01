package com.muser.muser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableConfigurationProperties
@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients
public class MuserApplication {

	public static void main(String[] args) {
		SpringApplication.run(MuserApplication.class, args);
	}

}
