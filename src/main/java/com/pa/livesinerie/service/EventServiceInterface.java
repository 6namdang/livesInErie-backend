package com.pa.livesinerie.service;

import com.pa.livesinerie.models.Event;
import java.util.List;


public interface EventServiceInterface {
    public Event createEvent(Event newEvent);
    public List<Event> findAllEvents();
    public Event findEventById(int id);
    public Event updateEventById(int id, Event updatedEvent);
    public boolean deleteEventById(int id);
}
