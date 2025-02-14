package com.prk;

import com.prk.repository.SpeakerRepository;
import com.prk.repository.StubSpeakerRepositoryImpl;
import com.prk.service.SpeakerService;
import com.prk.service.SpeakerServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean (name="speakerService")
    public SpeakerService getSpeakerService() {
        // constructor injection
        SpeakerServiceImpl service = new SpeakerServiceImpl(getSpeakerRepository());
        return service;
    }

    @Bean (name="speakerRepository")
    public SpeakerRepository getSpeakerRepository() {
        return new StubSpeakerRepositoryImpl();
    }
}
