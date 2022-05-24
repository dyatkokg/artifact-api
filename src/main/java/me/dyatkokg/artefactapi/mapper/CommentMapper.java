package me.dyatkokg.artefactapi.mapper;

import me.dyatkokg.artefactapi.dto.CommentDTO;
import me.dyatkokg.artefactapi.entity.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CommentMapper {



    @Mapping(source = "content", target = "content")
    @Mapping(source = "user.id", target = "user.id")
    @Mapping(source = "artifact.id", target = "artifact.id")
    Comment toEntity(CommentDTO dto);


    @Mapping(source = "content", target = "content")
    @Mapping(source = "user.id", target = "user.id")
    @Mapping(source = "artifact.id", target = "artifact.id")
    CommentDTO toDTO(Comment comment);


}
