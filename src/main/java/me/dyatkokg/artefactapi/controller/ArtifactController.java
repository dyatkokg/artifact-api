package me.dyatkokg.artefactapi.controller;

import lombok.RequiredArgsConstructor;
import me.dyatkokg.artefactapi.dto.ArtifactDTO;
import me.dyatkokg.artefactapi.dto.ArtifactMetadataDTO;
import me.dyatkokg.artefactapi.dto.ArtifactSearchDTO;
import me.dyatkokg.artefactapi.service.ArtefactService;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("artifact")
@RequiredArgsConstructor
public class ArtifactController {

    private final ArtefactService service;

    @PostMapping(value = "update",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ArtifactDTO> addArtefact(@RequestPart("file") MultipartFile file, @RequestPart("metadata") ArtifactMetadataDTO metadataDTO) {
        return ResponseEntity.ok(service.update(file, metadataDTO));
    }

    @GetMapping("{id}")
    public ResponseEntity<ArtifactDTO> findById(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable("id") UUID id) {
        service.deleteById(id);
    }

    @PostMapping("find")
    public ResponseEntity<Page<ArtifactDTO>> findBy(@RequestBody ArtifactSearchDTO searchDTO) {
        return ResponseEntity.ok(service.searchByField(searchDTO));
    }


}
