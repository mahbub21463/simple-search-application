package com.searchapp;

import com.searchapp.seeders.DatabaseSeeder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SimpleSearchApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimpleSearchApplication.class, args);
                DatabaseSeeder seeder = new DatabaseSeeder();
                seeder.seed();
	}
}
