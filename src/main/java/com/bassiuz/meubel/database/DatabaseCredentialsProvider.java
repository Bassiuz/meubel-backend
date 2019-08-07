package com.bassiuz.meubel.database;

import java.io.IOException;

import com.bassiuz.meubel.util.ConfigFileReader;

public class DatabaseCredentialsProvider {

    public static DatabaseCredentialsPojo readConfig() {
        if (System.getenv("CLEARDB_DATABASE_URL") != null) {
            // using the credentials configured on the Heroku server
            return new DatabaseCredentialsPojo(System.getenv("CLEARDB_JBDC_DATABASE_URL"), System.getenv("CLEARDB_USERNAME"),
                    System.getenv("CLEARDB_PASSWORD"));

        } else {
            // No heroku credentials found, so using the credentials in the local config
            // file
            try {
                return ConfigFileReader.getDatabaseConfigValues();
            } catch (IOException e) {
                System.out.println("Database Credentials couldn't be loaded from config file.");
                e.printStackTrace();
            }
        }
        return null;
    }
}