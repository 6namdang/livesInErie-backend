package com.pa.livesinerie.service.impl;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import com.pa.livesinerie.models.Job;
import com.pa.livesinerie.repository.JobRepository;
import com.pa.livesinerie.service.JobServiceInterface;


@Service
@Transactional
public class JobServiceImpl implements JobServiceInterface {
    private final JobRepository jobRepository;

    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    
    public Job createJob(Job job) {
        return jobRepository.save(job);
    }

    @Override
    public Job getJobById(int id) {
        return jobRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Job> getAllJobs(Pageable pageable) {
        return jobRepository.findAll(pageable).getContent();
    }

    public Job updateJob(int id, Job updatedJob) {
        Job job = jobRepository.findById(id).orElse(null);
        if (job != null) {
            job.setJobTitle(updatedJob.getJobTitle());
            job.setCompanyName(updatedJob.getCompanyName());
            job.setCityName(updatedJob.getCityName());
            job.setJobUrl(updatedJob.getJobUrl());
            job.setPostedAt(updatedJob.getPostedAt());
            return jobRepository.save(job);
        }
        return null;
    }

    public boolean deleteJob(int id) {
        if (jobRepository.existsById(id)) {
            jobRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
