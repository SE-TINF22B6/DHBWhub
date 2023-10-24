package de.tinf22b6.dhbwhub;

import de.tinf22b6.dhbwhub.database.DBConnection;
import de.tinf22b6.dhbwhub.utils.EnvUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;

@SpringBootApplication
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);

		// TODO: This is just temporary for test purposes
		DBConnection dbConnection = new DBConnection(
				EnvUtils.getEnv("DATABASE_URL"),
				EnvUtils.getEnv("DATABASE_USERNAME"),
				EnvUtils.getEnv("DATABASE_PASSWORD")
		);

		try {
			dbConnection.getConnection();
		} catch (SQLException e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(1);
		}

		System.out.println("Successfully connected to the database");
	}
}