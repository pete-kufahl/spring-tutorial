package com.prk.jdbcdemo;

import com.prk.jdbcdemo.model.Speaker;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class JdbcdemoApplicationTests {

	@Test
	void testGetSpeakers() {
		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<List<Speaker>> speakersResponse = restTemplate.exchange(
				"http://localhost:8080/", HttpMethod.GET,
				null, new ParameterizedTypeReference<List<Speaker>>() {
				});

        assertNotNull(speakersResponse.getBody(), "Body is null");

		List<Speaker> speakers = speakersResponse.getBody();

		for (Speaker speaker : speakers) {
			System.out.println("Speaker name: " + speaker.getName());
		}
	}
}
