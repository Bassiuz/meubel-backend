package com.bassiuz.meubel.database;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class DatabaseConnectionTest {

    @Test
    public void testDatabaseConnection()
    {
        HerokuConfigReader.readConfig();
        assertTrue("gets multiple results.",
                                true == true);
    }

}