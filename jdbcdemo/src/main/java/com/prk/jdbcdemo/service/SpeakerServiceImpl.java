package com.prk.jdbcdemo.service;

import com.prk.jdbcdemo.model.Speaker;
import com.prk.jdbcdemo.repository.SpeakerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("speakerService")
public class SpeakerServiceImpl implements SpeakerService {

    private SpeakerRepository speakerRepository;

    public SpeakerServiceImpl(SpeakerRepository speakerRepository) {
        this.speakerRepository = speakerRepository;
    }

    @Override
    public List<Speaker> findAll() {
        return speakerRepository.findAll();
    }

    @Override
    public Speaker create(Speaker speaker) {
        return speakerRepository.create(speaker);
    }

    @Override
    public Speaker getSpeaker(int id) {
        return speakerRepository.getSpeaker(id);
    }

    @Override
    public Speaker update(Speaker speaker) {
        return speakerRepository.update(speaker);
    }
}
