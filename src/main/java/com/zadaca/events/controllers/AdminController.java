package com.zadaca.events.controllers;


import com.zadaca.events.payload.request.LocationRequest;
import com.zadaca.events.services.AdminService;
import com.zadaca.events.services.LocationService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "Admin")
@RequestMapping("/api/v1/admin")
@SecurityRequirement(name = "Bearer Authentication")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService, LocationService locationService) {
        super();
        this.adminService = adminService;
    }

    @PutMapping("/banUser/{id}")
    public ResponseEntity<?> banUser(@PathVariable("id") Long id){
        return this.adminService.banUser(id);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> changePassword(@PathVariable("id") Long id, String newPassword){
        return this.adminService.changePassword(id, newPassword);
    }

    @GetMapping("/populateDB")
    public void populateDB(){
        this.adminService.populateDB();
    }
}
