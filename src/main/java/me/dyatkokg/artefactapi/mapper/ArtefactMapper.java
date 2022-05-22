package me.dyatkokg.artefactapi.mapper;

import me.dyatkokg.artefactapi.dto.ArtefactDTO;
import me.dyatkokg.artefactapi.dto.ArtefactMetadataDTO;
import me.dyatkokg.artefactapi.entity.Artefact;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ArtefactMapper {

    Artefact toEntityFromMetadata(ArtefactMetadataDTO metadataDTO);

    ArtefactDTO toDTO(Artefact artefact);
}
