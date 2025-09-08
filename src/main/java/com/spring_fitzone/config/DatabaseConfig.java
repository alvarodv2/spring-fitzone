package com.spring_fitzone.config;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfig {

    @Bean
    public DataSource dataSource(){
        Dotenv dotenv = Dotenv.configure()
                .directory("src/main/resources/config")
                .filename(".env")
                .load();

        return DataSourceBuilder.create()
                .url(dotenv.get("DB_URL"))
                .username(dotenv.get("DB_USER"))
                .password(dotenv.get("DB_PASSWORD"))
                .build();
    }

}
