package com.bassiuz.meubel.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.bassiuz.meubel.database.DatabaseCredentialsPojo;

public class ConfigFileReader {

    /**
     * This file is going to read the config.properties file. 
     */
    public static DatabaseCredentialsPojo getDatabaseConfigValues() throws IOException {
        Properties prop = new Properties();
        String propFileName = "databaseconfig.properties";

		try (InputStream inputStream = ConfigFileReader.class.getClassLoader().getResourceAsStream(propFileName)){
            
			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            }
            
			String databaseUrl = prop.getProperty("databaseurl");
			String username = prop.getProperty("username");
            String password = prop.getProperty("password");

            return new DatabaseCredentialsPojo(databaseUrl, username, password);
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		}
		return null;
	}

}