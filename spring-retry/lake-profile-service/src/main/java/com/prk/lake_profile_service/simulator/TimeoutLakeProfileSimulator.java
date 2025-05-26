package com.prk.lake_profile_service.simulator;

import com.prk.lake_profile_service.model.LakeProfile;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Profile("timeout")
public class TimeoutLakeProfileSimulator implements LakeProfileSimulator {

    private void simulateDelay() {
        try {
            Thread.sleep(5000); // 5-second delay
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public LakeProfile createLakeProfile(LakeProfile request) {
        simulateDelay();
        return null;
    }

    @Override
    public Optional<LakeProfile> getLakeProfile(UUID id) {
        simulateDelay();
        return Optional.empty();
    }

    @Override
    public List<LakeProfile> getAllProfiles() {
        simulateDelay();
        return Collections.emptyList();
    }

    @Override
    public void deleteProfile(UUID id) {
        simulateDelay();
    }

    @Override
    public LakeProfile updateProfile(UUID id, LakeProfile updated) {
        simulateDelay();
        return null;
    }
}

