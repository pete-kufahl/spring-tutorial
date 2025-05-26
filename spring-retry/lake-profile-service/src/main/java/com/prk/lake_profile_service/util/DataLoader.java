package com.prk.lake_profile_service.util;

import com.prk.lake_profile_service.model.LakeProfile;
import com.prk.lake_profile_service.repository.LakeProfileRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    private final LakeProfileRepository repository;

    public DataLoader(LakeProfileRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) {
        List<LakeProfile> lakes = List.of(
                new LakeProfile(null, "Lake Guntersville", "Alabama", 280.0, 18.3, "Reservoir"),
                new LakeProfile(null, "Lake Okeechobee", "Florida", 1890.0, 3.0, "Natural"),
                new LakeProfile(null, "Lake Fork", "Texas", 110.0, 21.0, "Reservoir"),
                new LakeProfile(null, "Chickamauga Lake", "Tennessee", 147.0, 21.3, "Reservoir"),
                new LakeProfile(null, "Sam Rayburn Reservoir", "Texas", 463.0, 24.0, "Reservoir"),
                new LakeProfile(null, "Toledo Bend", "Texas/Louisiana", 749.0, 33.0, "Reservoir"),
                new LakeProfile(null, "Lake Erie", "Ohio/Pennsylvania/New York", 25760.0, 64.0, "Great Lake"),
                new LakeProfile(null, "Lake St. Clair", "Michigan", 1114.0, 3.4, "Natural"),
                new LakeProfile(null, "Clear Lake", "California", 180.0, 9.1, "Natural"),
                new LakeProfile(null, "Kissimmee Chain", "Florida", 1174.0, 3.5, "Natural"),
                new LakeProfile(null, "Lake Champlain", "New York/Vermont", 1_130.0, 122.0, "Natural"),
                new LakeProfile(null, "Bull Shoals Lake", "Arkansas/Missouri", 1836.0, 72.0, "Reservoir"),
                new LakeProfile(null, "Table Rock Lake", "Missouri", 170.0, 67.1, "Reservoir"),
                new LakeProfile(null, "Lake Hartwell", "Georgia/South Carolina", 222.0, 45.7, "Reservoir"),
                new LakeProfile(null, "Kentucky Lake", "Tennessee/Kentucky", 648.0, 18.9, "Reservoir"),
                new LakeProfile(null, "Pickwick Lake", "Alabama/Tennessee/Mississippi", 190.0, 18.6, "Reservoir"),
                new LakeProfile(null, "Lake Murray", "South Carolina", 200.0, 60.0, "Reservoir"),
                new LakeProfile(null, "Lake Lanier", "Georgia", 152.0, 50.0, "Reservoir"),
                new LakeProfile(null, "Potomac River", "Maryland/Virginia", 25.0, 6.1, "River"),
                new LakeProfile(null, "Lake Dardanelle", "Arkansas", 136.0, 21.3, "Reservoir")
        );

        repository.saveAll(lakes);
    }
}

