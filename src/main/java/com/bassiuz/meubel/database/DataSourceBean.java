package com.bassiuz.meubel.database;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Configuration
@Component
public class DataSourceBean {

    @ConfigurationProperties(prefix = "spring.datasource.secondary.hikari")
    @Bean
    @Primary
    public DataSource getDataSource() {
        DatabaseCredentialsPojo dataBaseCredentials = DatabaseCredentialsProvider.readConfig();

        return DataSourceBuilder
        .create()
        .url(dataBaseCredentials.getDatabaseUrl())
        .username(dataBaseCredentials.getUsername())
        .password(dataBaseCredentials.getPassword())
        .driverClassName("com.mysql.jdbc.Driver")
        .build();
    }
}