package me.dyatkokg.artefactapi.mapper;

import me.dyatkokg.artefactapi.dto.ArtifactDTO;
import me.dyatkokg.artefactapi.dto.ArtifactMetadataDTO;
import me.dyatkokg.artefactapi.entity.Artifact;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ArtifactMapper {

    @Mapping(source = "category" ,target = "category")
    @Mapping(source = "description" ,target = "description")
    @Mapping(target = "id" ,ignore = true)
    @Mapping(target = "created" ,ignore = true)
    @Mapping(target = "userId" ,ignore = true)
    @Mapping(target = "artefact" ,ignore = true)
    Artifact toEntityFromMetadata(ArtifactMetadataDTO metadataDTO);

    ArtifactDTO toDTO(Artifact artefact);
}
