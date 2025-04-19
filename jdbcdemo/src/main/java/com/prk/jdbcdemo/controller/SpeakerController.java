package com.prk.jdbcdemo.controller;

import com.prk.jdbcdemo.model.Speaker;
import com.prk.jdbcdemo.service.SpeakerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SpeakerController {
    private SpeakerService speakerService;

    public SpeakerController(SpeakerService speakerService) {
        this.speakerService = speakerService;
    }

    @GetMapping
    public List<Speaker> getSpeakers() {
        return speakerService.findAll();
    }
}
