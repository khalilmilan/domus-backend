package com.permission.mpermission;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableConfigurationProperties
@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
public class MpermissionApplication {

	public static void main(String[] args) {
		SpringApplication.run(MpermissionApplication.class, args);
	}

}
