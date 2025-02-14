package com.prk.repository;

import com.prk.model.Speaker;

import java.util.ArrayList;
import java.util.List;

public class StubSpeakerRepositoryImpl implements SpeakerRepository {

    @Override
    public List<Speaker> findAll() {
        List<Speaker> speakers = new ArrayList<>();

        Speaker speaker = new Speaker();
        speaker.setFirstName("Joe");
        speaker.setLastName("Smith");
        speakers.add(speaker);

        return speakers;
    }
}
