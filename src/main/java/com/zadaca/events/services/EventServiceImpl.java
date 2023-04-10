package com.zadaca.events.services;

import com.zadaca.events.domains.Category;
import com.zadaca.events.domains.Event;
import com.zadaca.events.domains.Location;
import com.zadaca.events.payload.request.EventRequest;
import com.zadaca.events.payload.response.MessageResponse;
import com.zadaca.events.repository.CategoryRepository;
import com.zadaca.events.repository.EventRepository;
import com.zadaca.events.repository.LocationRepository;
import org.springframework.data.domain.Example;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EventServiceImpl implements EventService{
    private final EventRepository eventRepository;
    private final LocationRepository locationRepository;
    private final CategoryRepository categoryRepository;

    public EventServiceImpl(EventRepository eventRepository, LocationRepository locationRepository, CategoryRepository categoryRepository) {
        this.eventRepository = eventRepository;
        this.locationRepository = locationRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Event> getEvents(String name, Long locationId, Long categoryId) {
        if (name == null && locationId == null && categoryId != null){
            return this.eventRepository.findEventsByCategory(this.categoryRepository.findById(categoryId).orElseThrow());
        }
        if (name == null && locationId != null && categoryId == null){
            return this.eventRepository.findEventsByLocation(this.locationRepository.findById(locationId).orElseThrow());
        }
        if (name != null && locationId == null && categoryId == null){
            return this.eventRepository.findEventsByName(name);
        }
        if (name != null && locationId != null && categoryId == null){
            return this.eventRepository.findEventsByLocationAndName(this.locationRepository.findById(locationId).orElseThrow(), name);
        }
        if (name != null && locationId == null){
            return this.eventRepository.findEventsByCategoryAndName(this.categoryRepository.findById(categoryId).orElseThrow(), name);
        }
        if (name == null && locationId != null){
            return this.eventRepository.findEventsByCategoryAndLocation(this.categoryRepository.findById(categoryId).orElseThrow(), this.locationRepository.findById(locationId).orElseThrow());
        }
        if (name != null){
            return this.eventRepository.findEventsByNameAndLocationAndCategory(name, this.locationRepository.findById(locationId).orElseThrow(), this.categoryRepository.findById(categoryId).orElseThrow());
        }
        return this.eventRepository.findByDateAfterCurrentDate();
    }

    @Override
    public ResponseEntity<?> addEvent(EventRequest eventRequest) {
        if(eventRequest.getName() == null || eventRequest.getDescription() == null || eventRequest.getDate() == null || eventRequest.getLocationId() == null || eventRequest.getPictureUrl() == null || eventRequest.getCategoryId() == null){
            return ResponseEntity.badRequest()
                    .body(new MessageResponse("Error: All fields must be entered!"));
        }

        Location newLocation = this.locationRepository.findById(eventRequest.getLocationId()).orElseThrow();
        Category newCategory = this.categoryRepository.findById(eventRequest.getCategoryId()).orElseThrow();

        Event newEvent = new Event();
        newEvent.setName(eventRequest.getName());
        newEvent.setDate(eventRequest.getDate());
        newEvent.setPictureUrl(eventRequest.getPictureUrl());
        newEvent.setDescription(eventRequest.getDescription());
        newEvent.setLocation(newLocation);
        newEvent.setCategory(newCategory);

        this.eventRepository.save(newEvent);
        return ResponseEntity.ok()
                .body(new MessageResponse("Event added successfully!"));
    }

    @Override
    public Event getEvent(Long id) {
        return this.eventRepository.findById(id).orElseThrow();
    }

    @Override
    public ResponseEntity<?> editEvent(Long id, EventRequest eventRequest) {
        if (!this.eventRepository.existsById(id)){
            return ResponseEntity.badRequest()
                    .body(new MessageResponse("Error: Event with entered ID does not exist!"));
        }

        Event newEvent = this.eventRepository.findById(id).orElseThrow();

        if (eventRequest.getName() != null){
            newEvent.setName(eventRequest.getName());
        }
        if (eventRequest.getDescription() != null){
            newEvent.setDescription(eventRequest.getDescription());
        }
        if (eventRequest.getPictureUrl() != null){
            newEvent.setPictureUrl(eventRequest.getPictureUrl());
        }
        if (eventRequest.getDate() != null){
            newEvent.setDate(eventRequest.getDate());
        }
        if (eventRequest.getCategoryId() != null && !this.categoryRepository.existsById(eventRequest.getCategoryId())){
            newEvent.setCategory(this.categoryRepository.findById(eventRequest.getCategoryId()).orElseThrow());
        }
        if (eventRequest.getLocationId() != null && !this.locationRepository.existsById(eventRequest.getLocationId())){
            newEvent.setLocation(this.locationRepository.findById(eventRequest.getLocationId()).orElseThrow());
        }


        this.eventRepository.save(newEvent);
        return ResponseEntity.ok()
                .body(new MessageResponse("Event saved successfully!"));
    }
}
