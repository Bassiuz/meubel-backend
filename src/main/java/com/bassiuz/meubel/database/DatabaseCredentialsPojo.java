package com.bassiuz.meubel.database;

public class DatabaseCredentialsPojo {
    private String databaseUrl;
    private String username;
    private String password;

    public String getDatabaseUrl() {
        return databaseUrl;
    }

    public void setDatabaseUrl(String databaseUrl) {
        this.databaseUrl = databaseUrl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public DatabaseCredentialsPojo(String databaseUrl, String username, String password) {
        this.databaseUrl = databaseUrl;
        this.username = username;
        this.password = password;
    }

    @Override
    public String toString() {
        return "DatabaseCredentialsPojo [databaseUrl=" + databaseUrl + ", username="
                + username + "]";
    }

    
}