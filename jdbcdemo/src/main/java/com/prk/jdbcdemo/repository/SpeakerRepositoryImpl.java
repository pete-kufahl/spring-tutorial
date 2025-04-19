package com.prk.jdbcdemo.repository;

import com.prk.jdbcdemo.model.Speaker;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("speakerRepository")
public class SpeakerRepositoryImpl implements SpeakerRepository {
    @Override
    public List<Speaker> findAll() {
        Speaker speaker = new Speaker();
        speaker.setName("Pancho the Dog");
        speaker.setSkill("Barking");
        List<Speaker> speakers = new ArrayList<>();
        speakers.add(speaker);
        return speakers;
    }
}
