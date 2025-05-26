package com.prk.lake_profile_service.simulator;

import com.prk.lake_profile_service.model.LakeProfile;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import static org.springframework.http.HttpStatus.SERVICE_UNAVAILABLE;

@Service
@Profile("socket")
public class SocketFailureSimulator implements LakeProfileSimulator {

    private final Map<UUID, Integer> requestAttempts = new ConcurrentHashMap<>();
    private final Map<UUID, LakeProfile> store = new ConcurrentHashMap<>();

    private static final int FAILURE_THRESHOLD = 2;

    private void maybeFail(UUID id) {
        requestAttempts.putIfAbsent(id, 0);
        int count = requestAttempts.get(id);
        if (count < FAILURE_THRESHOLD) {
            requestAttempts.put(id, count + 1);
            throw new ResponseStatusException(SERVICE_UNAVAILABLE, "Simulated socket error");
        }
    }

    @Override
    public LakeProfile createLakeProfile(LakeProfile request) {
        UUID id = UUID.randomUUID();
        request.setId(id);
        store.put(id, request);
        return request;
    }

    @Override
    public Optional<LakeProfile> getLakeProfile(UUID id) {
        maybeFail(id);
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<LakeProfile> getAllProfiles() {
        return new ArrayList<>(store.values());
    }

    @Override
    public void deleteProfile(UUID id) {
        store.remove(id);
    }

    @Override
    public LakeProfile updateProfile(UUID id, LakeProfile updated) {
        updated.setId(id);
        store.put(id, updated);
        return updated;
    }
}

