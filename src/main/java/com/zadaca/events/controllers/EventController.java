package com.zadaca.events.controllers;

import com.zadaca.events.domains.Category;
import com.zadaca.events.domains.Event;
import com.zadaca.events.payload.request.CategoryRequest;
import com.zadaca.events.payload.request.EventRequest;
import com.zadaca.events.services.EventService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Events")
@RequestMapping("/api/v1")
@SecurityRequirement(name = "Bearer Authentication")
@CrossOrigin(origins = "*", maxAge = 3600)
public class EventController {
    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/events")
    public List<Event> getEvents(@RequestParam(required = false) String name, @RequestParam(required = false) Long locationId, @RequestParam(required = false) Long categoryId){
        return this.eventService.getEvents(name, locationId, categoryId);
    }
    @GetMapping("/events/{id}")
    public Event getEvent(@PathVariable("id") Long id){
        return this.eventService.getEvent(id);
    }
    @PostMapping("/admin/events")
    public ResponseEntity<?> addEvent(@RequestBody EventRequest eventRequest){
        return this.eventService.addEvent(eventRequest);
    }
    @PutMapping("/admin/events/{id}")
    public ResponseEntity<?> editEvent(@PathVariable("id") Long id, @RequestBody EventRequest eventRequest){
        return this.eventService.editEvent(id, eventRequest);
    }

}
