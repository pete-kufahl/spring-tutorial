package com.prk.lake_profile_service.controller;

import com.prk.lake_profile_service.model.LakeProfile;
import com.prk.lake_profile_service.service.LakeProfileService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/lake-profiles")
public class LakeProfileController {
    private final LakeProfileService service;

    public LakeProfileController(LakeProfileService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<LakeProfile> create(@RequestBody @Valid LakeProfile profile) {
        return new ResponseEntity<>(service.createProfile(profile), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LakeProfile> get(@PathVariable UUID id) {
        return service.getProfile(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<LakeProfile> getAll() {
        return service.getAllProfiles();
    }

    @PutMapping("/{id}")
    public ResponseEntity<LakeProfile> update(@PathVariable UUID id, @RequestBody LakeProfile profile) {
        return ResponseEntity.ok(service.updateProfile(id, profile));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.deleteProfile(id);
        return ResponseEntity.noContent().build();
    }
}

