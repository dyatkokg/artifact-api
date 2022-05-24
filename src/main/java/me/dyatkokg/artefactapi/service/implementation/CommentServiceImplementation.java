package me.dyatkokg.artefactapi.service.implementation;

import lombok.RequiredArgsConstructor;
import me.dyatkokg.artefactapi.dto.CommentDTO;
import me.dyatkokg.artefactapi.entity.Comment;
import me.dyatkokg.artefactapi.mapper.CommentMapper;
import me.dyatkokg.artefactapi.repository.CommentRepository;
import me.dyatkokg.artefactapi.service.CommentService;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CommentServiceImplementation implements CommentService {

    private final CommentRepository repository;
    private final CommentMapper mapper;

    @Override
    public CommentDTO addComment(CommentDTO commentDTO) {
        Comment comment;
        if (Objects.nonNull(commentDTO.getArtifact())) {
            comment = repository.findById(commentDTO.getId()).orElseThrow(RuntimeException::new);
            comment.setContent(commentDTO.getContent());
            comment.setUser(commentDTO.getUser());
            comment.getArtifact().setId(commentDTO.getArtifact().getId());
        } else {
            comment = mapper.toEntity(commentDTO);
        }
        return mapper.toDTO(repository.save(comment));
    }

}
