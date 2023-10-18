package de.tinf22b6.dhbwhub;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.DriverManager;

@SpringBootApplication
public class DHBWhubApplication {
	public static void main(String[] args) {
		SpringApplication.run(DHBWhubApplication.class, args);

		Dotenv dotenv = Dotenv.load();

		Connection connection = null;
		try {
			connection = DriverManager.getConnection(dotenv.get("DATABASE_URL"), dotenv.get("DATABASE_USERNAME"), dotenv.get("DATABASE_PASSWORD"));
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			return;
		}

		System.out.println("Successfully connected to the database");
	}
}