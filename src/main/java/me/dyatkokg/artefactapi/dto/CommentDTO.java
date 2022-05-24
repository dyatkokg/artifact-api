package me.dyatkokg.artefactapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.dyatkokg.artefactapi.entity.Artifact;
import me.dyatkokg.artefactapi.entity.User;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO {

    private UUID id;

    private Artifact artifact;

    private User user;

    private String content;

}
