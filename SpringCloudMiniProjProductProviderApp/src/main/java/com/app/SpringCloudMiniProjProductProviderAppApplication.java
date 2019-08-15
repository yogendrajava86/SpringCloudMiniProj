package com.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableHystrix
@EnableFeignClients
@RefreshScope
public class SpringCloudMiniProjProductProviderAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudMiniProjProductProviderAppApplication.class, args);
	}

}
