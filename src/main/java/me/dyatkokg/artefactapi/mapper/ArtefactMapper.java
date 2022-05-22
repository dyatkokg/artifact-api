package me.dyatkokg.artefactapi.mapper;

import me.dyatkokg.artefactapi.dto.ArtefactDTO;
import me.dyatkokg.artefactapi.dto.ArtefactMetadataDTO;
import me.dyatkokg.artefactapi.entity.Artefact;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ArtefactMapper {

    @Mapping(source = "category" ,target = "category")
    @Mapping(source = "description" ,target = "description")
    @Mapping(target = "id" ,ignore = true)
    @Mapping(target = "created" ,ignore = true)
    @Mapping(target = "userId" ,ignore = true)
    @Mapping(target = "artefact" ,ignore = true)
    Artefact toEntityFromMetadata(ArtefactMetadataDTO metadataDTO);

    ArtefactDTO toDTO(Artefact artefact);
}
