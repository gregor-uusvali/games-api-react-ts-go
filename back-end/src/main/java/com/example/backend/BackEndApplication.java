package com.example.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;


@SpringBootApplication
public class BackEndApplication {

	// Autowire the Environment object for accessing environment variables
	@Autowired
	private Environment environment;

	public static void main(String[] args) {
		SpringApplication.run(BackEndApplication.class, args);
	}

}
