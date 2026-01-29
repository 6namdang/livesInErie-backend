package com.pa.livesinerie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pa.livesinerie.models.Event;
import com.pa.livesinerie.service.impl.EventServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/api/events")
@CrossOrigin
public class EventController {
    private final EventServiceImpl eventService;

    @Autowired
    public EventController(EventServiceImpl eventsService) {
        this.eventService = eventsService;
    }

    @GetMapping
    public ResponseEntity<List<Event>> getAllEvent() {
        return ResponseEntity.ok(eventService.findAllEvents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Event> getEventById(int id) {
        Event event = eventService.findEventById(id);
        if (event == null) {

        }
        return ResponseEntity.ok(eventService.findEventById(id));
    }

    @PostMapping
    public ResponseEntity<Event> createEvent(Event event) {
        return ResponseEntity.ok(eventService.createEvent(event));
    }

    @PutMapping
    ("/{id}")
    public ResponseEntity<Event> updateEventById(int id, Event updatedEvent) {
        return ResponseEntity.ok(eventService.updateEventById(id, updatedEvent));
    }

    @DeleteMapping("/{id}")
    public boolean deleteEventById(int id) {
        return eventService.deleteEventById(id);
    }
}
