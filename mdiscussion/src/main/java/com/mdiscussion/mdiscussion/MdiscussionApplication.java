package com.mdiscussion.mdiscussion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableConfigurationProperties
@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
public class MdiscussionApplication {

	public static void main(String[] args) {
		SpringApplication.run(MdiscussionApplication.class, args);
	}

}
