package com.zadaca.events.controllers;

import com.zadaca.events.domains.Location;
import com.zadaca.events.payload.request.LocationRequest;
import com.zadaca.events.services.LocationService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Locations")
@RequestMapping("/api/v1")
@SecurityRequirement(name = "Bearer Authentication")
@CrossOrigin(origins = "*", maxAge = 3600)
public class LocationController {
    private final LocationService locationService;
    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }
    @GetMapping("/locations")
    public List<Location> getLocations(){
        return this.locationService.getLocations();
    }
    @GetMapping("/locations/{id}")
    public Location getLocation(@PathVariable("id") Long id){
        return this.locationService.getLocation(id);
    }
    @PutMapping("/admin/locations/{id}")
    public ResponseEntity<?> editLocation(@PathVariable("id") Long id, @RequestBody LocationRequest locationRequest){
        return this.locationService.editLocation(id, locationRequest);
    }
    @PostMapping("/admin/locations")
    public ResponseEntity<?> addLocation(@RequestBody LocationRequest locationRequest){
        return this.locationService.addLocation(locationRequest);
    }

}
