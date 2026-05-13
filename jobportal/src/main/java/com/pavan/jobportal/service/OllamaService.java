package com.pavan.jobportal.service;

import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class OllamaService {

	public String generateResponse(String prompt) throws Exception {

		String url = "http://localhost:11434/api/generate";

		RestTemplate restTemplate = new RestTemplate();

		// Request body
		Map<String, Object> requestBody = Map.of("model", "tinyllama", "prompt", prompt, "stream", false);

		// Headers
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		// HTTP Entity
		HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);

		// API Call
		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

		// Convert JSON response
		ObjectMapper mapper = new ObjectMapper();

		JsonNode jsonNode = mapper.readTree(response.getBody());

		// Return only AI response text
		return jsonNode.get("response").asText();
	}
}