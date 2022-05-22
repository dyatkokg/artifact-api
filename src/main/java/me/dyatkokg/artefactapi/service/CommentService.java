package me.dyatkokg.artefactapi.service;

import me.dyatkokg.artefactapi.entity.Comment;
import org.springframework.http.ResponseEntity;

public interface CommentService {

    Comment addComment(Comment comment);
}
