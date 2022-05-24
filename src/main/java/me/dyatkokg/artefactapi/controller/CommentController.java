package me.dyatkokg.artefactapi.controller;

import lombok.RequiredArgsConstructor;
import me.dyatkokg.artefactapi.dto.CommentDTO;
import me.dyatkokg.artefactapi.entity.Comment;
import me.dyatkokg.artefactapi.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService service;

    @PostMapping("add")
    public ResponseEntity<CommentDTO> addComment(@RequestBody CommentDTO comment){
        return ResponseEntity.ok(service.addComment(comment));
    }

}
