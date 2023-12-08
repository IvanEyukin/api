package fmc.config;

import fmc.database.client.Client;
import fmc.utils.Utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = "application.properties")
public class Config {
    @Value("${deploy.database}")
    public String dbPath = "db/database.db";

    @Bean 
    public Utils utils() {
        return new Utils();
    }

    @Bean
    public Client client() {
        return new Client();
    }
}