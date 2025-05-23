package com.prk.jdbcdemo.controller;

import com.prk.jdbcdemo.model.Speaker;
import com.prk.jdbcdemo.service.SpeakerService;
import com.prk.jdbcdemo.util.ServiceError;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/speaker/{id}")
    public Speaker getSpeaker(@PathVariable(value = "id") int id) {
        return speakerService.getSpeaker(id);
    }

    @PostMapping("/speaker")
    public Speaker createSpeaker(@RequestBody Speaker speaker) {
        return speakerService.create(speaker);
    }

    @PutMapping("/speaker")
    public Speaker updateSpeaker(@RequestBody Speaker speaker) {
        System.out.println("Name: " + speaker.getName());
        return speakerService.update(speaker);
    }

    @GetMapping("/speaker/batch")
    public Object batch() {
        speakerService.batch();
        return null;
    }

    @DeleteMapping("/speaker/delete/{id}")
    public Object deleteSpeaker(@PathVariable(value = "id") int id) {
        speakerService.deleteSpeaker(id);
        return null;
    }

    @GetMapping("/speaker/test")
    public Object test() {
        throw new DataAccessException("testing exception thrown") { };
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ServiceError> handle (RuntimeException ex) {
        ServiceError error = new ServiceError(HttpStatus.OK.value(), ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.OK);
    }
}
