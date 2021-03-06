package com.zm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringCloudApplication
@RestController
@EnableFeignClients
public class ApiShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiShopApplication.class, args);
	}

	@RequestMapping("/")
	public String index() throws Exception {
		return "micro-service-api-shop";
	}
}
