package com.prk.jdbcdemo;

import com.prk.jdbcdemo.model.Speaker;
import org.junit.jupiter.api.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

// run these tests while the app (server) is running
class JdbcdemoApplicationTests {

	@Test
	public void testCreateSpeaker(){
		RestTemplate restTemplate = new RestTemplate();

		Speaker speaker = new Speaker();
		speaker.setName("Corn Pop");

		restTemplate.put("http://localhost:8080/speaker", speaker);
	}

	@Test
	void testGetSpeakers() {
		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<List<Speaker>> speakersResponse = restTemplate.exchange(
				"http://localhost:8080/speaker", HttpMethod.GET,
				null, new ParameterizedTypeReference<List<Speaker>>() {
				});

        assertNotNull(speakersResponse.getBody(), "Body is null");

		List<Speaker> speakers = speakersResponse.getBody();

		for (Speaker speaker : speakers) {
			System.out.println("Speaker name: " + speaker.getName());
		}
	}
}
