package com.pavan.jobportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pavan.jobportal.dto.JobRequest;
import com.pavan.jobportal.dto.JobResponse;
import com.pavan.jobportal.response.ApiResponse;
import com.pavan.jobportal.service.JobService;

@RestController

@RequestMapping("/api/recruiter")
public class JobController {

	@Autowired
	private JobService jobService;

	@PostMapping("/createjobs")
	public ResponseEntity<ApiResponse<JobResponse>> createJob(@RequestBody JobRequest jobRequest,
			Authentication authentication) {

		String recruiterEmail = authentication.getName();

		JobResponse job = jobService.createJob(jobRequest, recruiterEmail);

		ApiResponse<JobResponse> apiResponse = new ApiResponse<JobResponse>("Job created sucessfully", job,
				HttpStatus.CREATED.value());

		return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);

	}
}
