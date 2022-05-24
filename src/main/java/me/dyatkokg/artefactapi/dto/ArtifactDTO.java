package me.dyatkokg.artefactapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.dyatkokg.artefactapi.entity.User;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArtifactDTO {

    private String category;

    private String description;

    private byte[] artefact;

}
