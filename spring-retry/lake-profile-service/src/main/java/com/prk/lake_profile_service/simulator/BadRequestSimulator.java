package com.prk.lake_profile_service.simulator;

import com.prk.lake_profile_service.model.LakeProfile;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Profile("badrequest")
public class BadRequestSimulator implements LakeProfileSimulator {

    @Override
    public LakeProfile createLakeProfile(LakeProfile request) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Simulated bad request");
    }

    @Override
    public Optional<LakeProfile> getLakeProfile(UUID id) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid ID format");
    }


    @Override
    public List<LakeProfile> getAllProfiles() {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Request malformed");
    }

    @Override
    public void deleteProfile(UUID id) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot delete invalid profile");
    }

    @Override
    public LakeProfile updateProfile(UUID id, LakeProfile updated) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Update failed due to bad input");
    }
}
