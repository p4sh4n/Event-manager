package com.zadaca.events.services;

import com.zadaca.events.domains.Comment;
import com.zadaca.events.domains.User;
import com.zadaca.events.payload.request.CommentRequest;
import com.zadaca.events.payload.response.MessageResponse;
import com.zadaca.events.repository.CommentRepository;
import com.zadaca.events.repository.EventRepository;
import com.zadaca.events.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CommentServiceImpl implements CommentService{
    private final CommentRepository commentRepository;
    private final EventRepository eventRepository;
    private final UserRepository userRepository;

    public CommentServiceImpl(CommentRepository commentRepository, EventRepository eventRepository,
                              UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
    }

    @Override
    public ResponseEntity<?> addComment(Long id, Long userId, CommentRequest commentRequest) {

        if(!eventRepository.existsById(id)){
            return ResponseEntity.badRequest()
                    .body(new MessageResponse("Error: Post with entered ID does not exist!"));
        }
        if (commentRequest.getContent().isEmpty()){
            return ResponseEntity.badRequest()
                    .body(new MessageResponse("Error: Comment can not be empty!"));
        }
        Comment newComment = new Comment();
        newComment.setContent(commentRequest.getContent());
        newComment.setEvent(eventRepository.findById(id).orElseThrow());
        newComment.setDate(LocalDateTime.now());
        newComment.setUser(userRepository.findById(userId).orElseThrow());
        this.commentRepository.save(newComment);

        return ResponseEntity.ok()
                .body(new MessageResponse("Comment added successfully!"));
    }
}
