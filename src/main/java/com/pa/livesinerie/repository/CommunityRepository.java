package com.pa.livesinerie.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.pa.livesinerie.models.Community;

public interface CommunityRepository extends JpaRepository<Community, Integer> {
    
}
