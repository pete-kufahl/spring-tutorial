package com.prk.lake_profile_service.repository;

import com.prk.lake_profile_service.model.LakeProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface LakeProfileRepository extends JpaRepository<LakeProfile, UUID> {
    List<LakeProfile> findByLocation(String location);
}
