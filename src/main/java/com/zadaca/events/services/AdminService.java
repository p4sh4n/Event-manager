package com.zadaca.events.services;

import com.zadaca.events.domains.User;
import org.springframework.http.ResponseEntity;

public interface AdminService {
    ResponseEntity<?> banUser(Long uuid);

    ResponseEntity<?> changePassword(Long id, String newPassword);

    void populateDB();
}
