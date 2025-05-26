package com.prk.lake_profile_service.simulator;

import com.prk.lake_profile_service.model.LakeProfile;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Profile("normal")
public class NormalSimulator implements LakeProfileSimulator {

    private final Map<UUID, LakeProfile> store = new HashMap<>();

    @Override
    public LakeProfile createLakeProfile(LakeProfile request) {
        UUID id = UUID.randomUUID();
        request.setId(id);
        store.put(id, request);
        return request;
    }

    @Override
    public Optional<LakeProfile> getLakeProfile(UUID id) {
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
