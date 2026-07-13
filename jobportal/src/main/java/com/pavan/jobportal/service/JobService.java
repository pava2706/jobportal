package com.pavan.jobportal.service;

import com.pavan.jobportal.dto.JobRequest;
import com.pavan.jobportal.dto.JobResponse;

public interface JobService {

	JobResponse createJob(JobRequest jobRequest, String recruiterEmail);
	
	JobResponse getJobById(Long id);
	
	JobResponse getAllJob();

}
