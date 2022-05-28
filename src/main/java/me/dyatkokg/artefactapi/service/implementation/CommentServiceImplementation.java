package me.dyatkokg.artefactapi.service.implementation;

import lombok.RequiredArgsConstructor;
import me.dyatkokg.artefactapi.configuration.filter.FilterUtils;
import me.dyatkokg.artefactapi.dto.CommentDTO;
import me.dyatkokg.artefactapi.entity.Comment;
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
            comment = repository.findById(commentDTO.getId()).orElseThrow(RuntimeException::new);
            comment.setContent(commentDTO.getContent());
            comment.getUser().setId(UUID.fromString(provider.getSubject(FilterUtils.getTokenFromSecurityContext())));
//            comment.getArtifact().setId(commentDTO.getArtifactId());
            artifactRepository.findById(UUID.fromString(commentDTO.getArtifactId())).ifPresent(comment::setArtifact);
            repository.save(comment);
        } else {
//            comment = mapper.toEntity(commentDTO);
            comment = new Comment();
            comment.setArtifact(artifactRepository.findById(UUID.fromString(commentDTO.getArtifactId())).orElse(null));
            comment.setUser(userRepository.findById(UUID.fromString(provider.getSubject(FilterUtils.getTokenFromSecurityContext()))).orElse(null));
            comment.setContent(commentDTO.getContent());
            comment = repository.save(comment);
        }
        CommentDTO commentDTO1 = new CommentDTO();
        commentDTO1.setContent(comment.getContent());
        commentDTO1.setUser(comment.getUser());
        commentDTO1.setArtifactId(comment.getArtifact().getId().toString());
        commentDTO1.setId(comment.getId());
      //        return mapper.toDTO(repository.save(comment));
        return commentDTO1;
    }

}
