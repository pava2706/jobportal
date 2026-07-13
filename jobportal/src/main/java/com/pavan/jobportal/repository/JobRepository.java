package com.pavan.jobportal.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pavan.jobportal.entity.Job;


public interface JobRepository extends JpaRepository<Job, Long> {
      
	List<Job> findAllByOrderByCreatedAtDesc();
	
      }
