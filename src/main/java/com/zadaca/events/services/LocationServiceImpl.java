package com.zadaca.events.services;

import com.zadaca.events.domains.Location;
import com.zadaca.events.payload.request.LocationRequest;
import com.zadaca.events.payload.response.MessageResponse;
import com.zadaca.events.repository.LocationRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationServiceImpl implements LocationService{
    private final LocationRepository locationRepository;

    public LocationServiceImpl(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @Override
    public ResponseEntity<?> addLocation(LocationRequest locationRequest) {
        Location newLocation = new Location();
        newLocation.setName(locationRequest.getName());
        newLocation.setDescription(locationRequest.getDescription());
        newLocation.setPictureUrl(locationRequest.getPictureUrl());

        if(newLocation.getDescription() == null || newLocation.getName() == null || newLocation.getPictureUrl() == null){
            return ResponseEntity.badRequest()
                    .body(new MessageResponse("Error: All fields must be entered!"));
        }

        this.locationRepository.save(newLocation);
        return ResponseEntity.ok()
                .body(new MessageResponse("Location saved successfully!"));
    }

    @Override
    public ResponseEntity<?> editLocation(Long id, LocationRequest locationRequest) {
        if (!this.locationRepository.existsById(id)){
            return ResponseEntity.badRequest()
                    .body(new MessageResponse("Error: Location with entered ID does not exist!"));
        }

        Location newLocation = this.locationRepository.findById(id).orElseThrow();

        if (locationRequest.getName() != null){
            newLocation.setName(locationRequest.getName());
        }
        if (locationRequest.getDescription() != null){
            newLocation.setDescription(locationRequest.getDescription());
        }
        if (locationRequest.getPictureUrl() != null){
            newLocation.setPictureUrl(locationRequest.getPictureUrl());
        }

        this.locationRepository.save(newLocation);
        return ResponseEntity.ok()
                .body(new MessageResponse("Location saved successfully!"));
    }

    @Override
    public List<Location> getLocations() {
        return this.locationRepository.findAll();
    }

    @Override
    public Location getLocation(Long id) {
        return this.locationRepository.findById(id).orElseThrow();
    }
}
