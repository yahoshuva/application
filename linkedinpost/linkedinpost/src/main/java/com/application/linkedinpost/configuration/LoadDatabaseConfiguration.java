package com.application.linkedinpost.configuration;

import com.application.linkedinpost.model.AuthenticationUser;
import com.application.linkedinpost.repository.AuthenticationUserRepository;
import com.application.linkedinpost.utils.Encoder;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabaseConfiguration {

    private final Encoder encoder;

    public LoadDatabaseConfiguration(Encoder encoder){
        this.encoder = encoder;
    }

    @Bean
    public CommandLineRunner initDatabase(AuthenticationUserRepository authenticationUserRepository){
        return args ->{
            AuthenticationUser authenticationUser = new AuthenticationUser("yaho@gmail.com", encoder.encode("password"));

            authenticationUserRepository.save(authenticationUser);
        };
    }
}
