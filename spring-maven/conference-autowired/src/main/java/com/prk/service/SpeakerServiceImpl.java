package com.prk.service;

import com.prk.model.Speaker;
import com.prk.repository.SpeakerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("speakerService")
@Scope(value= BeanDefinition.SCOPE_SINGLETON)
public class SpeakerServiceImpl implements SpeakerService {


    public SpeakerServiceImpl() {
        System.out.println("SpeakerServiceImpl default constructor...");
    }

    @Autowired
    public SpeakerServiceImpl(SpeakerRepository repository) {
        System.out.println("SpeakerServiceImpl repository constructor...");
        this.repository = repository;
    }

    private SpeakerRepository repository;

    /*
    @Autowired
    public void setRepository(SpeakerRepository repository) {
        System.out.println("SpeakerServiceImpl setter...");
        this.repository = repository;
    }
    */

    @Override
    public List<Speaker> findAll() {
        return repository.findAll();
    }
}
