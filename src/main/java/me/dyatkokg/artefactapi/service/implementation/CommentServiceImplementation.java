package me.dyatkokg.artefactapi.service.implementation;

import lombok.RequiredArgsConstructor;
import me.dyatkokg.artefactapi.configuration.filter.FilterUtils;
import me.dyatkokg.artefactapi.dto.CommentDTO;
import me.dyatkokg.artefactapi.entity.Comment;
import me.dyatkokg.artefactapi.mapper.CommentMapper;
import me.dyatkokg.artefactapi.repository.CommentRepository;
import me.dyatkokg.artefactapi.service.CommentService;
import me.dyatkokg.artefactapi.service.TokenProvider;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CommentServiceImplementation implements CommentService {

    private final CommentRepository repository;
    private final CommentMapper mapper;

    private final TokenProvider provider;

    @Override
    public CommentDTO addComment(CommentDTO commentDTO) {
        Comment comment;
        if (Objects.nonNull(commentDTO.getArtifact())) {
            comment = repository.findById(commentDTO.getId()).orElseThrow(RuntimeException::new);
            comment.setContent(commentDTO.getContent());
            comment.getUser().setId(UUID.fromString(provider.getSubject(FilterUtils.getTokenFromSecurityContext())));
            comment.getArtifact().setId(commentDTO.getArtifact().getId());
        } else {
            comment = mapper.toEntity(commentDTO);
        }
        return mapper.toDTO(repository.save(comment));
    }

}
