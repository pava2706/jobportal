package com.pavan.jobportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pavan.jobportal.service.OllamaService;

@RestController
@RequestMapping("/ai")
public class OllamaController {

	@Autowired
	private OllamaService ollamaService;

	@GetMapping("/ask")
	public String askAI(@RequestParam String prompt) throws Exception {

		return ollamaService.generateResponse(prompt);
	}
}