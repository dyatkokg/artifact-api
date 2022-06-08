package me.dyatkokg.artefactapi.mapper;

import me.dyatkokg.artefactapi.dto.CommentDTO;
import me.dyatkokg.artefactapi.entity.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Objects;
import java.util.UUID;

@Mapper(componentModel = "spring")
public interface CommentMapper {


    @Mapping(source = "content", target = "content")
    @Mapping(source = "user.id", target = "user.id")
    @Mapping(target = "artifact.id", expression = "java(stringToUUID(commentDTO.getArtifactId()))")
    Comment toEntity(CommentDTO dto);


    @Mapping(source = "content", target = "content")
    @Mapping(source = "user.id", target = "user.id")
    @Mapping(target = "artifactId", expression = "java(uuidToString(comment.getArtifact().getId()))")
    CommentDTO toDTO(Comment comment);

    default String uuidToString(UUID uuid) {
        return Objects.nonNull(uuid) ? uuid.toString() : null;
    }

    default UUID stringToUUID(String s) {
        return Objects.nonNull(s) ? UUID.fromString(s) : null;
    }
}
