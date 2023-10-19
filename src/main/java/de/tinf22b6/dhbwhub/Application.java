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
		String dbUrl = EnvUtils.getEnv("DATABASE_URL");
		String dbUsername = EnvUtils.getEnv("DATABASE_USERNAME");
		String dbPassword = EnvUtils.getEnv("DATABASE_PASSWORD");
		DBConnection dbConnection = new DBConnection(dbUrl, dbUsername, dbPassword);

		try {
			dbConnection.getConnection();
		} catch (SQLException e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(1);
		}

		System.out.println("Successfully connected to the database");
	}
}