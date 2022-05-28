package me.dyatkokg.artefactapi.mapper;

import me.dyatkokg.artefactapi.dto.CommentDTO;
import me.dyatkokg.artefactapi.entity.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface CommentMapper {


    @Mapping(source = "content", target = "content")
    @Mapping(source = "user.id", target = "user.id")
//    @Mapping(source = "artifactId", target = "artifact.id", qualifiedByName = "uuidToString")
    Comment toEntity(CommentDTO dto);


    @Mapping(source = "content", target = "content")
    @Mapping(source = "user.id", target = "user.id")
//    @Mapping(source = "artifact.id", target = "artifactId")
    CommentDTO toDTO(Comment comment);

    @Named("uuidToString")
    static String uuidToString(UUID uuid) {
        return uuid.toString();
    }

}
