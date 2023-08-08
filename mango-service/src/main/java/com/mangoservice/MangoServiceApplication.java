package com.mangoservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MangoServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MangoServiceApplication.class, args);
		System.out.println("MongoDB Is Running... ");
	}

}
