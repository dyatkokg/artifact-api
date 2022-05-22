package me.dyatkokg.artefactapi.service;

import me.dyatkokg.artefactapi.dto.ArtifactDTO;
import me.dyatkokg.artefactapi.dto.ArtifactMetadataDTO;
import me.dyatkokg.artefactapi.dto.ArtifactSearchDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface ArtefactService {

    ArtifactDTO update(MultipartFile file, ArtifactMetadataDTO metadataDTO);

    ArtifactDTO getById(UUID id);

    void deleteById(UUID id);

    List<ArtifactDTO> searchByField(ArtifactSearchDTO artefact);
}
