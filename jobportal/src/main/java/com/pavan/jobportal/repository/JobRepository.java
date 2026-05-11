package com.pavan.jobportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pavan.jobportal.entity.Job;

public interface JobRepository extends JpaRepository<Job, Long> {

}
