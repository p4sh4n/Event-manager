package com.zadaca.events.controllers;

import com.zadaca.events.payload.request.CategoryRequest;
import com.zadaca.events.payload.request.CommentRequest;
import com.zadaca.events.services.CommentService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "Comments")
@RequestMapping("/api/v1/comments")
@SecurityRequirement(name = "Bearer Authentication")
@CrossOrigin(origins = "*", maxAge = 3600)
public class CommentController {
    public final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }
    @PostMapping("{userId}/{eventId}")
    public ResponseEntity<?> addComment(@PathVariable("userId") Long userId, @PathVariable("eventId") Long id,@RequestBody CommentRequest commentRequest){
        return this.commentService.addComment(id, userId, commentRequest);
    }
}
