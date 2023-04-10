package com.zadaca.events.services;

import com.zadaca.events.domains.Location;
import com.zadaca.events.payload.request.LocationRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface LocationService {
    ResponseEntity<?> addLocation(LocationRequest locationRequest);
    ResponseEntity<?> editLocation(Long id, LocationRequest locationRequest);

    List<Location> getLocations();

    Location getLocation(Long id);
}
