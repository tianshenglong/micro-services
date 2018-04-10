package com.zm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer  //服务注册中心注解
@SpringBootApplication
public class ServiceEurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceEurekaApplication.class, args);
	}
}
