package com.prk.lake_profile_service.controller;

import com.prk.lake_profile_service.model.LakeProfile;

import com.prk.lake_profile_service.simulator.LakeProfileSimulator;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/lake-profiles")
public class LakeProfileController {

    private final LakeProfileSimulator simulator;

    // replaced LakeProfileService with ... Simulator to create profile-dependent behavior
    public LakeProfileController(LakeProfileSimulator simulator) {
        this.simulator = simulator;
    }

    @PostMapping
    public ResponseEntity<LakeProfile> create(@RequestBody @Valid LakeProfile profile) {
        return new ResponseEntity<>(simulator.createLakeProfile(profile), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LakeProfile> get(@PathVariable UUID id) {
        return simulator.getLakeProfile(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<LakeProfile> getAll() {
        return simulator.getAllProfiles();
    }

    @PutMapping("/{id}")
    public ResponseEntity<LakeProfile> update(@PathVariable UUID id, @RequestBody LakeProfile profile) {
        return ResponseEntity.ok(simulator.updateProfile(id, profile));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        simulator.deleteProfile(id);
        return ResponseEntity.noContent().build();
    }
}

