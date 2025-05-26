package com.prk.basstracker.controller;

import com.prk.basstracker.model.LakeProfile;
import com.prk.basstracker.service.LakeProfileClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/bass-tracker")
public class BassTrackerController {

    private final LakeProfileClient client;

    public BassTrackerController(LakeProfileClient client) {
        this.client = client;
    }

    @PostMapping("/create-and-fetch")
    public LakeProfile createAndFetch(@RequestBody LakeProfile profile) throws InterruptedException {

        LakeProfile created = client.createLakeProfile(profile);
        if (created == null) {
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Failed to create LakeProfile after retries");
        }
        Thread.sleep(3000); // simulate delay
        return client.getLakeProfile(created.getId());

    }
}
