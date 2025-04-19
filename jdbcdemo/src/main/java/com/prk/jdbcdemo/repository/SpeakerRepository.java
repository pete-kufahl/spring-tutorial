package com.prk.jdbcdemo.repository;

import com.prk.jdbcdemo.model.Speaker;

import java.util.List;

public interface SpeakerRepository {
    List<Speaker> findAll();

    Speaker create(Speaker speaker);
}
