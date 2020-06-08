package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:oauth.properties")
public class DemoOauthWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoOauthWebApplication.class, args);
	}

}
