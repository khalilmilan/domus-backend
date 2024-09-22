package com.muser_device.muser_device;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableConfigurationProperties
@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
public class MuserDeviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MuserDeviceApplication.class, args);
	}

}
