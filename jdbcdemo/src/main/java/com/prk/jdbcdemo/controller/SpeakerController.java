package com.prk.jdbcdemo.controller;

import com.prk.jdbcdemo.model.Speaker;
import com.prk.jdbcdemo.service.SpeakerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SpeakerController {
    private SpeakerService speakerService;

    public SpeakerController(SpeakerService speakerService) {
        this.speakerService = speakerService;
    }

    @GetMapping("/speaker")
    public List<Speaker> getSpeakers() {
        return speakerService.findAll();
    }

    @PutMapping("/speaker")
    public Speaker createSpeaker(@RequestBody Speaker speaker) {
        System.out.println("Name: " + speaker.getName());

        return speakerService.create(speaker);
    }
}
