package me.dyatkokg.artefactapi.service;

import me.dyatkokg.artefactapi.dto.ArtefactDTO;
import me.dyatkokg.artefactapi.dto.ArtefactMetadataDTO;
import me.dyatkokg.artefactapi.dto.ArtefactSearchDTO;
import me.dyatkokg.artefactapi.entity.Artefact;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface ArtefactService {

    ArtefactDTO update(MultipartFile file, ArtefactMetadataDTO metadataDTO);

    ArtefactDTO getById(UUID id);

    void deleteById(UUID id);

    List<ArtefactDTO> searchByField(ArtefactSearchDTO artefact);
}
