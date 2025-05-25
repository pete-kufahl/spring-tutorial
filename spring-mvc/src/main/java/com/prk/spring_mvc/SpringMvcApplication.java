package com.prk.spring_mvc;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringMvcApplication {
	@Value("${spring.mvc.view.prefix}")
	private String viewPrefix;

	@PostConstruct
	public void init() {
		System.out.println("View prefix from YAML: " + viewPrefix);
	}
	public static void main(String[] args) {
		SpringApplication.run(SpringMvcApplication.class, args);
	}

}
