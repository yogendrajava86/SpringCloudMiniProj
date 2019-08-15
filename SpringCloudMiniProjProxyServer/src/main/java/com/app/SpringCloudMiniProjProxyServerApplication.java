package com.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class SpringCloudMiniProjProxyServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudMiniProjProxyServerApplication.class, args);
	}

}
