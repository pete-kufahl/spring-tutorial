package com.prk.lake_profile_service.simulator;

import com.prk.lake_profile_service.model.LakeProfile;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Service
@Profile("flaky")
public class FlakySimulator implements LakeProfileSimulator {

    private final Map<UUID, LakeProfile> store = new HashMap<>();
    private final Random random = new Random();

    private void maybeFail() {
        if (random.nextBoolean()) {
            throw new ResponseStatusException(INTERNAL_SERVER_ERROR, "Simulated random failure");
        }
    }

    @Override
    public LakeProfile createLakeProfile(LakeProfile request) {
        maybeFail();
        UUID id = UUID.randomUUID();
        request.setId(id);
        store.put(id, request);
        return request;
    }

    @Override
    public Optional<LakeProfile> getLakeProfile(UUID id) {
        maybeFail();
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<LakeProfile> getAllProfiles() {
        maybeFail();
        return new ArrayList<>(store.values());
    }

    @Override
    public void deleteProfile(UUID id) {
        maybeFail();
        store.remove(id);
    }

    @Override
    public LakeProfile updateProfile(UUID id, LakeProfile updated) {
        maybeFail();
        updated.setId(id);
        store.put(id, updated);
        return updated;
    }
}

