package com.prk.lake_profile_service.simulator;

import com.prk.lake_profile_service.model.LakeProfile;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Profile("race")
public class RaceConditionSimulator implements LakeProfileSimulator {

    private final Map<UUID, LakeProfile> store = new ConcurrentHashMap<>();
    private final Random random = new Random();

    @Override
    public LakeProfile createLakeProfile(LakeProfile request) {
        // Simulate non-deterministic behavior: same ID used randomly
        UUID id = random.nextBoolean() ? UUID.randomUUID() : UUID.nameUUIDFromBytes("fixed".getBytes());
        LakeProfile profile = new LakeProfile(id, request.getName(), "n/a", 0.0, 0.0, request.getLocation());
        store.put(id, profile);
        return profile;
    }

    @Override
    public Optional<LakeProfile> getLakeProfile(UUID id) {
        // Simulate a race: randomly return null even if ID was stored
        if (random.nextBoolean()) {
            return Optional.empty();
        }
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<LakeProfile> getAllProfiles() {
        // Occasionally skip entries
        List<LakeProfile> all = new ArrayList<>(store.values());
        if (!all.isEmpty() && random.nextBoolean()) {
            all.remove(random.nextInt(all.size()));
        }
        return all;
    }

    @Override
    public void deleteProfile(UUID id) {
        store.remove(id);
    }

    @Override
    public LakeProfile updateProfile(UUID id, LakeProfile updated) {
        // Simulate an update being lost
        if (random.nextBoolean()) {
            return store.get(id); // update skipped
        }
        LakeProfile existing = store.get(id);
        if (existing != null) {
            LakeProfile newProfile = new LakeProfile(id, updated.getName(),
                    updated.getLocation(), updated.getAreaSqKm(), updated.getAreaSqKm(), updated.getType());
            store.put(id, newProfile);
            return newProfile;
        }
        return null;
    }
}

