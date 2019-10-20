package com.example.demo;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class MiscServerApplication implements ApplicationRunner{

	public static void main(String[] args) {
		SpringApplication.run(MiscServerApplication.class, args);
	}
	
	@Override
	public void run(ApplicationArguments arg0) throws Exception {
		System.out.println("Server is running");
	}
}
