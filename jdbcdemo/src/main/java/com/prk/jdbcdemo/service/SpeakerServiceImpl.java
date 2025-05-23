package com.prk.jdbcdemo.service;

import com.prk.jdbcdemo.model.Speaker;
import com.prk.jdbcdemo.repository.SpeakerRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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

    @Override
    @Transactional
    public void batch() {

        List<Speaker> speakers = speakerRepository.findAll();
        List<Object[]> pairs = new ArrayList<>();
        for(var speaker : speakers) {
            Object[] tmp = { "Java", speaker.getId() };
            pairs.add(tmp);
        }
        speakerRepository.update(pairs);

        // this will roll back the transaction
        // throw new DataAccessException("error in batch") { };
    }

    @Override
    public void deleteSpeaker(int id) {
        speakerRepository.delete(id);
    }
}
