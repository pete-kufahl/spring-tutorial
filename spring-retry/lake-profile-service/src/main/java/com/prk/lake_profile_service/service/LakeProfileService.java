package com.prk.lake_profile_service.service;

import com.prk.lake_profile_service.model.LakeProfile;
import com.prk.lake_profile_service.repository.LakeProfileRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class LakeProfileService {
    private final LakeProfileRepository repository;

    public LakeProfileService(LakeProfileRepository repository) {
        this.repository = repository;
    }

    public LakeProfile createProfile(LakeProfile profile) {
        return repository.save(profile);
    }

    public Optional<LakeProfile> getProfile(UUID id) {
        return repository.findById(id);
    }

    public List<LakeProfile> getAllProfiles() {
        return repository.findAll();
    }

    public void deleteProfile(UUID id) {
        repository.deleteById(id);
    }

    public LakeProfile updateProfile(UUID id, LakeProfile updated) {
        return repository.findById(id)
                .map(existing -> {
                    existing.setName(updated.getName());
                    existing.setLocation(updated.getLocation());
                    existing.setAreaSqKm(updated.getAreaSqKm());
                    existing.setDepthMeters(updated.getDepthMeters());
                    existing.setType(updated.getType());
                    return repository.save(existing);
                })
                .orElseThrow(() -> new EntityNotFoundException("Lake profile not found"));
    }
}
