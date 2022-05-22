package me.dyatkokg.artefactapi.controller;

import lombok.RequiredArgsConstructor;
import me.dyatkokg.artefactapi.dto.ArtifactDTO;
import me.dyatkokg.artefactapi.dto.ArtifactMetadataDTO;
import me.dyatkokg.artefactapi.dto.ArtifactSearchDTO;
import me.dyatkokg.artefactapi.service.ArtefactService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("artefact")
@RequiredArgsConstructor
public class ArtifactController {

    private final ArtefactService service;

    @PostMapping("update")
    public ResponseEntity<ArtifactDTO> addArtefact(@RequestParam("file") MultipartFile file, @RequestParam("metadata") ArtifactMetadataDTO metadataDTO) {
        return ResponseEntity.ok(service.update(file, metadataDTO));
    }

    @GetMapping("{id}")
    public ResponseEntity<ArtifactDTO> findById(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @DeleteMapping("delete")
    public void deleteById(@PathVariable("id") UUID id) {
        service.deleteById(id);
    }

    @PostMapping("find")
    public ResponseEntity<List<ArtifactDTO>> findBy(@RequestBody ArtifactSearchDTO searchDTO) {
        return ResponseEntity.ok(service.searchByField(searchDTO));
    }

}
