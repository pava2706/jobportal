package com.pavan.jobportal.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pavan.jobportal.dto.JobRequest;
import com.pavan.jobportal.dto.JobResponse;
import com.pavan.jobportal.entity.Job;
import com.pavan.jobportal.entity.User;
import com.pavan.jobportal.exception.UserNotFoundException;
import com.pavan.jobportal.repository.JobRepository;
import com.pavan.jobportal.repository.UserRepository;
import com.pavan.jobportal.service.JobService;

@Service
public class JobServiceImpl implements JobService {

	@Autowired

	private JobRepository jobRepository;

	@Autowired
	private UserRepository userRepository;

	// To save the created job
	@Override
	public JobResponse createJob(JobRequest jobRequest, String recruiterEmail) {

		User user = userRepository.findByEmail(recruiterEmail)
				.orElseThrow(() -> new UserNotFoundException("User not found"));
		Job job = new Job();
		System.out.println("Recruiter Email: " + recruiterEmail);
		System.out.println("User ID: " + user.getId());
		System.out.println("User Email: " + user.getEmail());
		
			job.setCompany(jobRequest.getCompany());
			job.setDescription(jobRequest.getDescription());
			job.setLocation(jobRequest.getLocation());
			job.setTitle(jobRequest.getTitle());
			job.setSalary(jobRequest.getSalary());
			job.setRecruiter(user);
			
		Job savedJob = jobRepository.save(job);

		return mapToResponse(savedJob);
	}

	private JobResponse mapToResponse(Job job) {
		JobResponse res = new JobResponse();
		res.setId(job.getId());
		res.setTitle(job.getTitle());
		res.setDescription(job.getDescription());
		res.setCompany(job.getCompany());
		res.setLocation(job.getLocation());
		res.setSalary(job.getSalary());
		res.setCreatedAt(job.getCreatedAt());
		return res;

	}

}
