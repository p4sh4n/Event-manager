package com.zadaca.events.services;

import com.zadaca.events.payload.request.CommentRequest;
import org.springframework.http.ResponseEntity;

public interface CommentService {
    public ResponseEntity<?> addComment(Long id, Long userId, CommentRequest commentRequest);
}
