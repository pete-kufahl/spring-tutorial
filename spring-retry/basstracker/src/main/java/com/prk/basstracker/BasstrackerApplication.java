package com.prk.basstracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

@SpringBootApplication
@EnableRetry
public class BasstrackerApplication {
	public static void main(String[] args) {
		SpringApplication.run(BasstrackerApplication.class, args);
	}
}

