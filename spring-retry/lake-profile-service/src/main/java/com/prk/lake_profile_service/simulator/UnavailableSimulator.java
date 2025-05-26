package com.prk.lake_profile_service.simulator;

import com.prk.lake_profile_service.model.LakeProfile;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

import static org.springframework.http.HttpStatus.SERVICE_UNAVAILABLE;

@Service
@Profile("unavailable")
public class UnavailableSimulator implements LakeProfileSimulator {

    private void alwaysFail() {
        throw new ResponseStatusException(SERVICE_UNAVAILABLE, "Service is currently unavailable");
    }

    @Override
    public LakeProfile createLakeProfile(LakeProfile request) {
        alwaysFail();
        return null;
    }

    @Override
    public Optional<LakeProfile> getLakeProfile(UUID id) {
        alwaysFail();
        return Optional.empty();
    }

    @Override
    public List<LakeProfile> getAllProfiles() {
        alwaysFail();
        return Collections.emptyList();
    }

    @Override
    public void deleteProfile(UUID id) {
        alwaysFail();
    }

    @Override
    public LakeProfile updateProfile(UUID id, LakeProfile updated) {
        alwaysFail();
        return null;
    }
}

