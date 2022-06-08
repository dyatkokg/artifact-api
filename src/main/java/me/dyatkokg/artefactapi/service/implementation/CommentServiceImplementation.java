package me.dyatkokg.artefactapi.service.implementation;

import lombok.RequiredArgsConstructor;
import me.dyatkokg.artefactapi.configuration.filter.FilterUtils;
import me.dyatkokg.artefactapi.dto.CommentDTO;
import me.dyatkokg.artefactapi.entity.Comment;
import me.dyatkokg.artefactapi.exception.ArtifactNotFoundException;
import me.dyatkokg.artefactapi.exception.CommentNotFoundException;
import me.dyatkokg.artefactapi.mapper.CommentMapper;
import me.dyatkokg.artefactapi.repository.ArtifactRepository;
import me.dyatkokg.artefactapi.repository.CommentRepository;
import me.dyatkokg.artefactapi.repository.UserRepository;
import me.dyatkokg.artefactapi.service.CommentService;
import me.dyatkokg.artefactapi.service.TokenProvider;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CommentServiceImplementation implements CommentService {

    private final CommentRepository repository;
    private final ArtifactRepository artifactRepository;
    private final UserRepository userRepository;
    private final CommentMapper mapper;

    private final TokenProvider provider;

    @Override
    public CommentDTO addComment(CommentDTO commentDTO) {
        Comment comment;
        if (Objects.nonNull(commentDTO.getId())) {
            comment = repository.findById(commentDTO.getId()).orElseThrow(CommentNotFoundException::new);
            comment.setContent(commentDTO.getContent());
        } else {
            comment = mapper.toEntity(commentDTO);
        }
        comment.setArtifact(artifactRepository.findById(UUID.fromString(commentDTO.getArtifactId())).orElseThrow(ArtifactNotFoundException::new));
        comment.setUser(userRepository.findById(UUID.fromString(provider.getSubject(FilterUtils.getTokenFromSecurityContext()))).orElse(null));
        return mapper.toDTO(repository.save(comment));
    }
}
