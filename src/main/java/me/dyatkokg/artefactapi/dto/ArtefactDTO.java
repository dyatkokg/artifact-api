package me.dyatkokg.artefactapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArtefactDTO {

    private String category;

    private String description;

    private byte[] artefact;

}
