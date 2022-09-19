package com.example.Banking.application1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@CrossOrigin(origins = "http://localhost:4200")
public class BankingApplication1Application {

	public static void main(String[] args) {
		SpringApplication.run(BankingApplication1Application.class, args);
	}

}
