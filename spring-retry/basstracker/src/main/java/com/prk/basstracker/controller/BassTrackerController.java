package com.prk.basstracker.controller;

import com.prk.basstracker.model.LakeProfile;
import com.prk.basstracker.service.LakeProfileClient;
import org.springframework.web.bind.annotation.*;

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
        Thread.sleep(3000); // simulate delay
        return client.getLakeProfile(created.getId());
    }
}
