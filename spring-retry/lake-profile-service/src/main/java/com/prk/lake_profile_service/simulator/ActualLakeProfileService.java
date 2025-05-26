package com.prk.lake_profile_service.simulator;


import com.prk.lake_profile_service.model.LakeProfile;
import com.prk.lake_profile_service.repository.LakeProfileRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Profile("default")
public class ActualLakeProfileService implements LakeProfileSimulator {

    private final LakeProfileRepository repository;

    public ActualLakeProfileService(LakeProfileRepository repository) {
        this.repository = repository;
    }

    @Override
    public LakeProfile createLakeProfile(LakeProfile request) {
        return repository.save(request);
    }

    @Override
    public Optional<LakeProfile> getLakeProfile(UUID id) {
        return repository.findById(id);
    }

    @Override
    public List<LakeProfile> getAllProfiles() {
        return repository.findAll();
    }

    @Override
    public void deleteProfile(UUID id) {
        repository.deleteById(id);
    }

    @Override
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
