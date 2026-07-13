package com.pavan.jobportal.service;

import java.util.List;

import com.pavan.jobportal.dto.JobRequest;
import com.pavan.jobportal.dto.JobResponse;

public interface JobService {

	JobResponse createJob(JobRequest jobRequest, String recruiterEmail);
	
	JobResponse getJobById(Long id);
	
	List<JobResponse>getAllJob();
	
	JobResponse updateJob(JobRequest jobRequest, String recruiterEmail, Long id);

}
