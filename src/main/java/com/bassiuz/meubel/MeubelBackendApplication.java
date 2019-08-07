package com.bassiuz.meubel;

import com.bassiuz.meubel.database.DatabaseCredentialsPojo;
import com.bassiuz.meubel.database.DatabaseCredentialsProvider;

import org.flywaydb.core.Flyway;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MeubelBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(MeubelBackendApplication.class, args);
		
		DatabaseCredentialsPojo databaseCredentials = DatabaseCredentialsProvider.readConfig();
		Flyway flyway = Flyway.configure().dataSource(databaseCredentials.getDatabaseUrl(), databaseCredentials.getUsername(), databaseCredentials.getPassword()).load();
		flyway.migrate();
	}
}
