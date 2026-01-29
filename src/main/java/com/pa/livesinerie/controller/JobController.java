package com.pa.livesinerie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.pa.livesinerie.models.Job;
import com.pa.livesinerie.service.impl.JobServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
@CrossOrigin
public class JobController {
    private final JobServiceImpl jobService;

    @Autowired
    public JobController(JobServiceImpl jobService) {
        this.jobService = jobService;
    }
    //get all methods
    @GetMapping
    public ResponseEntity<List<Job>> getAllJobs(
    @RequestParam(name = "page", defaultValue = "1") int page,
    @RequestParam(name = "size", defaultValue = "10") int size) {
    return ResponseEntity.ok(
        jobService.getAllJobs(PageRequest.of(page - 1, size))
    );
}


    @GetMapping("/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable int id) {
        Job job = jobService.getJobById(id);
        
        if (job == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(job);
    }


    @PostMapping
    public ResponseEntity<Job> createJob(@RequestBody Job job) {
        if (job == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(job);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Job> updateJobById(@PathVariable Integer id, @RequestParam Job updatedJob) {
        Job job = jobService.updateJob(id, updatedJob);
        if (job == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(job);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJob(@PathVariable int id) {
        if (jobService.deleteJob(id)) {
            return ResponseEntity.ok("Deleted");
        }
        return ResponseEntity.notFound().build();
    }
}
