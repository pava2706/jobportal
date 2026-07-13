package com.pavan.jobportal.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pavan.jobportal.dto.JobRequest;
import com.pavan.jobportal.dto.JobResponse;
import com.pavan.jobportal.entity.Job;
import com.pavan.jobportal.entity.User;
import com.pavan.jobportal.exception.JobNotFoundException;
import com.pavan.jobportal.exception.UnknownUserException;
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

	@Override
	public JobResponse getJobById(Long id) {
		
		Job res = jobRepository.findById(id).orElseThrow(() -> new JobNotFoundException("Requested job doesn't exist"));

		return mapToResponse(res);
	}

	@Override
	public List<JobResponse> getAllJob() {
		
	List<Job> jobs	=jobRepository.findAllByOrderByCreatedAtDesc();
	
	if (jobs.isEmpty()) {
		throw new JobNotFoundException("No Jobs Available");
	}
	
	return jobs.stream()
			.map(this::mapToResponse)
			.toList();
	}

	
	@Override
	public JobResponse updateJob(JobRequest jobRequest, String recruiterEmail, Long id) {
		
		User user = userRepository.findByEmail(recruiterEmail)
				.orElseThrow(() -> new UserNotFoundException("User not found"));
		
		Job res = jobRepository.findById(id).orElseThrow(() -> new JobNotFoundException("Requested job doesn't exist"));

          if(res.getRecruiter().getEmail()!= user.getEmail()) {
        	  throw new UnknownUserException("Unauthorized User");
          }
          else {
		
		res.setCompany(jobRequest.getCompany());
		res.setDescription(jobRequest.getDescription());
		res.setLocation(jobRequest.getLocation());
		res.setTitle(jobRequest.getTitle());
		res.setSalary(jobRequest.getSalary());
		res.setRecruiter(user);

		Job savedJob = jobRepository.save(res);

		return mapToResponse(savedJob);
		
	}
	}

}
