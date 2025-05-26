package com.prk.lake_profile_service.simulator;

import com.prk.lake_profile_service.model.LakeProfile;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface LakeProfileSimulator {
    LakeProfile createLakeProfile(LakeProfile request);

    Optional<LakeProfile> getLakeProfile(UUID id);

    List<LakeProfile> getAllProfiles();

    void deleteProfile(UUID id);

    LakeProfile updateProfile(UUID id, LakeProfile updated);
}

