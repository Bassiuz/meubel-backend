package com.bassiuz.meubel.database;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class DatabaseConnectionTest {

    @Test
    public void testDatabaseCredentials() {
        DatabaseCredentialsPojo databasePojo = HerokuConfigReader.readConfig();
        assertTrue("DatabaseURL is configured.",
                databasePojo.getDatabaseUrl() != null && databasePojo.getDatabaseUrl().length() > 0);

        assertTrue("Username is configured.",
                databasePojo.getUsername() != null && databasePojo.getUsername().length() > 0);

        assertTrue("Password is configured.",
                databasePojo.getPassword() != null && databasePojo.getPassword().length() > 0);
    }

}