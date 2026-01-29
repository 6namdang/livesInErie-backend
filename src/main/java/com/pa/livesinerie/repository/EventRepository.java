package com.pa.livesinerie.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.pa.livesinerie.models.Event;

public interface EventRepository extends JpaRepository<Event, Integer>{
}