package com.bassiuz.meubel.database;

public class HerokuConfigReader
{   
    public static void readConfig()
    {
        System.out.println("CLEARDB_DATABASE_URL" + System.getenv("CLEARDB_DATABASE_URL"));
        System.out.println("CLEARDB_USERNAME" + System.getenv("CLEARDB_USERNAME"));
        System.out.println("CLEARDB_PASSWORD" + System.getenv("CLEARDB_PASSWORD"));

    }
}