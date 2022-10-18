package com.app.salesapi;

import com.app.salesapi.model.Client;
import com.app.salesapi.repository.ClientRepository;
import com.app.salesapi.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SalesApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SalesApiApplication.class, args);
	}

}
