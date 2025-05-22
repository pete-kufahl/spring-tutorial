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
		speaker.setName("Bog Wallop");

		//restTemplate.put("http://localhost:8080/speaker", speaker);
		restTemplate.postForObject("http://localhost:8080/speaker", speaker, Speaker.class);
		System.out.println(speaker.getName());
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

	@Test
	void testGetSpeaker() {
		RestTemplate restTemplate = new RestTemplate();
		Speaker speaker = restTemplate.getForObject("http://localhost:8080/speaker/{id}", Speaker.class, 9);
        assert speaker != null;
        System.out.println(speaker.getName());
	}

	@Test
	void testUpdateSpeaker() {
		RestTemplate restTemplate = new RestTemplate();
		Speaker speaker = restTemplate.getForObject("http://localhost:8080/speaker/{id}", Speaker.class, 9);
        assert speaker != null;
        speaker.setName(speaker.getName() + " Jr.");
		restTemplate.put("http://localhost:8080/speaker", speaker);
		System.out.println(speaker.getName());
	}
}
