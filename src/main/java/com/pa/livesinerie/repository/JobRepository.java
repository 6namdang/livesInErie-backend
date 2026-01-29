package com.pa.livesinerie.repository;
import com.pa.livesinerie.models.Job;
import org.springframework.data.jpa.repository.JpaRepository;


public interface JobRepository extends JpaRepository<Job, Integer> {
    
}