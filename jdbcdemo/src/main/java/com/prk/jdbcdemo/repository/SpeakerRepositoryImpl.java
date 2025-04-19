package com.prk.jdbcdemo.repository;

import com.prk.jdbcdemo.model.Speaker;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("speakerRepository")
public class SpeakerRepositoryImpl implements SpeakerRepository {

    private JdbcTemplate jdbcTemplate;

    public SpeakerRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Speaker> findAll() {
        Speaker speaker = new Speaker();
        speaker.setName("Pancho the Dog");
        speaker.setSkill("Barking");
        List<Speaker> speakers = new ArrayList<>();
        speakers.add(speaker);
        return speakers;
    }

    @Override
    public Speaker create(Speaker speaker) {
        jdbcTemplate.update("INSERT INTO speaker (name) values (?)", speaker.getName());
        return null;
    }
}
