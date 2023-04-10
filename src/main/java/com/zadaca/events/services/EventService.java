package com.zadaca.events.services;

import com.zadaca.events.domains.Event;
import com.zadaca.events.payload.request.EventRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface EventService {
    List<Event> getEvents(String name, Long locationId, Long categoryId);

    ResponseEntity<?> addEvent(EventRequest eventRequest);

    Event getEvent(Long id);

    ResponseEntity<?> editEvent(Long id, EventRequest eventRequest);
}
