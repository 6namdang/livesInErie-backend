package com.pa.livesinerie.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pa.livesinerie.exceptions.DomainException;
import com.pa.livesinerie.models.Event;
import com.pa.livesinerie.repository.EventRepository;
import com.pa.livesinerie.service.EventServiceInterface;

@Service
@Transactional
public class EventServiceImpl implements EventServiceInterface {
    private final EventRepository eventRepository;
    
    public EventServiceImpl(EventRepository eventsRepository) {
        this.eventRepository = eventsRepository;
    }

    @Override
    public Event createEvent(Event newEvent) 
    {
        Event event = new Event(newEvent.getEventName(), newEvent.getHostName(), newEvent.getDescription(), newEvent.getPostedAt());
        return eventRepository.save(event);
    }

    @Override
    public Event findEventById(int id) {
        return eventRepository.findById(id).orElseThrow(() -> new DomainException("The event does not exist"));
    }

    @Override
    public List<Event> findAllEvents() {
        return eventRepository.findAll();
    }

    @Override
    public Event updateEventById(int id, Event updatedEvent) {
        Event event = findEventById(id);
        if (event != null) {
            event.setEventName(updatedEvent.getEventName());
            event.setHostName(updatedEvent.getHostName());
            event.setDescription(updatedEvent.getDescription());
            event.setPostedAt(updatedEvent.getPostedAt());
            return eventRepository.save(event);
            
        }
        return null;
    }

    @Override
    public boolean deleteEventById(int id) {
        if (eventRepository.existsById(id)) {
            eventRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
