package com.prk.lake_profile_service.simulator;

import com.prk.lake_profile_service.model.LakeProfile;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Profile("timeout")
public class TimeoutLakeProfileSimulator implements LakeProfileSimulator {

    private final Map<UUID, LakeProfile> store = new HashMap<>();

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
        UUID id = UUID.randomUUID();
        request.setId(id);
        store.put(id, request);
        return request;
    }

    @Override
    public Optional<LakeProfile> getLakeProfile(UUID id) {
        simulateDelay();
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<LakeProfile> getAllProfiles() {
        simulateDelay();
        return new ArrayList<>(store.values());
    }

    @Override
    public void deleteProfile(UUID id) {
        simulateDelay();
        store.remove(id);
    }

    @Override
    public LakeProfile updateProfile(UUID id, LakeProfile updated) {
        simulateDelay();
        updated.setId(id);
        store.put(id, updated);
        return updated;
    }
}

