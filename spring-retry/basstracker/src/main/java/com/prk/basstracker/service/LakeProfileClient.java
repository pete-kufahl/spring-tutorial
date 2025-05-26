package com.prk.basstracker.service;

import com.prk.basstracker.model.LakeProfile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class LakeProfileClient {

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${lake-profile-service.base-url}")
    private String lakeProfileServiceBaseUrl;

    public LakeProfile createLakeProfile(LakeProfile profile) {
        ResponseEntity<LakeProfile> response = restTemplate.postForEntity(
                lakeProfileServiceBaseUrl + "/lake-profiles",
                profile,
                LakeProfile.class
        );
        return response.getBody();
    }

    public LakeProfile getLakeProfile(Long id) {
        return restTemplate.getForObject(
                lakeProfileServiceBaseUrl + "/lake-profiles/" + id,
                LakeProfile.class
        );
    }
}
