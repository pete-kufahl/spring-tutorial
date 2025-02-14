package com.prk.service;

import com.prk.model.Speaker;
import com.prk.repository.SpeakerRepository;
import com.prk.repository.StubSpeakerRepositoryImpl;

import java.util.List;

public class SpeakerServiceImpl implements SpeakerService {

    private SpeakerRepository repository = new StubSpeakerRepositoryImpl();

    @Override
    public List<Speaker> findAll() {
        return repository.findAll();
    }
}
