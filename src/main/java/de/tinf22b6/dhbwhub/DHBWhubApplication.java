package de.tinf22b6.dhbwhub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.DriverManager;

@SpringBootApplication
public class DHBWhubApplication {
	public static void main(String[] args) {
		SpringApplication.run(DHBWhubApplication.class, args);

		Connection connection = null;
		try {
			// Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + args[0], args[1], args[2]);
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			return;
		}

		System.out.println("Successfully connected to the database");
	}
}