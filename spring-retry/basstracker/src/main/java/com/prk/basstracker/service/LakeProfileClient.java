package com.prk.basstracker.service;

import com.prk.basstracker.model.LakeProfile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Service
public class LakeProfileClient {

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${lake-profile-service.base-url}")
    private String lakeProfileServiceBaseUrl;

    @Retryable(
            include = { ResourceAccessException.class, HttpServerErrorException.class },
            exclude = { HttpClientErrorException.BadRequest.class },
            maxAttempts = 4,
            backoff = @Backoff(delay = 2000, multiplier = 2)
    )
    public LakeProfile createLakeProfile(LakeProfile profile) {
        ResponseEntity<LakeProfile> response = restTemplate.postForEntity(
                lakeProfileServiceBaseUrl + "/lake-profiles",
                profile,
                LakeProfile.class
        );
        return response.getBody();
    }

    @Retryable(
            include = { ResourceAccessException.class, HttpServerErrorException.class, HttpClientErrorException.NotFound.class },
            exclude = { HttpClientErrorException.BadRequest.class },
            maxAttempts = 4,
            backoff = @Backoff(delay = 1000)
    )
    public LakeProfile getLakeProfile(UUID id) {
        return restTemplate.getForObject(
                lakeProfileServiceBaseUrl + "/lake-profiles/" + id,
                LakeProfile.class
        );
    }

    @Recover
    public LakeProfile recoverFromServiceUnavailable(HttpServerErrorException ex, LakeProfile request) {
        System.out.println("LakeProfileService is unavailable after retries. Falling back. " + ex.getMessage());
        return null;
    }
}
