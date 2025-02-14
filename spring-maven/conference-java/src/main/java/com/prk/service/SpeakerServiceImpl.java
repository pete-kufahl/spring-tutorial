package com.prk.service;

import com.prk.model.Speaker;
import com.prk.repository.SpeakerRepository;

import java.util.List;

public class SpeakerServiceImpl implements SpeakerService {

    public SpeakerServiceImpl(SpeakerRepository repository) {
        this.repository = repository;
    }

    private SpeakerRepository repository;

    public void setRepository(SpeakerRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Speaker> findAll() {
        return repository.findAll();
    }
}
