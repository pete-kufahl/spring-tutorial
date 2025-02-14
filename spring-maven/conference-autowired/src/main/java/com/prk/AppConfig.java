package com.prk;

import com.prk.repository.SpeakerRepository;
import com.prk.repository.StubSpeakerRepositoryImpl;
import com.prk.service.SpeakerService;
import com.prk.service.SpeakerServiceImpl;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
@ComponentScan({"com.prk"})
public class AppConfig {

    /*
    @Bean (name="speakerService")
    @Scope(value=BeanDefinition.SCOPE_SINGLETON)
    public SpeakerService getSpeakerService() {
        // constructor injection
        // SpeakerServiceImpl service = new SpeakerServiceImpl(getSpeakerRepository());

        // uses autowired repository
        SpeakerServiceImpl service = new SpeakerServiceImpl();

        // uses setter injection
        // service.setRepository(getSpeakerRepository());

        return service;
    }
    */
    /*
    @Bean (name="speakerRepository")
    public SpeakerRepository getSpeakerRepository() {
        return new StubSpeakerRepositoryImpl();
    }
     */
}
