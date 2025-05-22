package com.prk.jdbcdemo.controller;

import com.prk.jdbcdemo.model.Speaker;
import com.prk.jdbcdemo.service.SpeakerService;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/speaker")
    public Speaker createSpeaker(@RequestBody Speaker speaker) {
        System.out.println("Name: " + speaker.getName());

        return speakerService.create(speaker);
    }
}
