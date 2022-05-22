package me.dyatkokg.artefactapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArtefactMetadataDTO {

    private String category;

    private String description;
}
