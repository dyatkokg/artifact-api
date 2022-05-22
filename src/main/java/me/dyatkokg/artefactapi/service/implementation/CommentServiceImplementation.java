package me.dyatkokg.artefactapi.service.implementation;

import lombok.RequiredArgsConstructor;
import me.dyatkokg.artefactapi.entity.Comment;
import me.dyatkokg.artefactapi.service.CommentService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentServiceImplementation implements CommentService {

    @Override
    public Comment addComment(Comment comment) {
        return null;
    }
}
