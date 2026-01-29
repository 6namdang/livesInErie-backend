package com.pa.livesinerie.service;

import com.pa.livesinerie.models.Job;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface JobServiceInterface {
    List<Job> getAllJobs(Pageable pageable);
    Job getJobById(int id);
    Job createJob(Job job);
    Job updateJob(int id, Job job);
    boolean deleteJob(int id);
}