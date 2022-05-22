package me.dyatkokg.artefactapi.controller;

import lombok.RequiredArgsConstructor;
import me.dyatkokg.artefactapi.dto.ArtefactDTO;
import me.dyatkokg.artefactapi.dto.ArtefactMetadataDTO;
import me.dyatkokg.artefactapi.dto.ArtefactSearchDTO;
import me.dyatkokg.artefactapi.entity.Artefact;
import me.dyatkokg.artefactapi.service.ArtefactService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("artefact")
@RequiredArgsConstructor
public class ArtefactController {

    private final ArtefactService service;

    @PostMapping("update")
    public ResponseEntity<ArtefactDTO> addArtefact(@RequestParam("file") MultipartFile file, @RequestParam("metadata") ArtefactMetadataDTO metadataDTO) {
        return ResponseEntity.ok(service.update(file, metadataDTO));
    }

    @GetMapping("{id}")
    public ResponseEntity<ArtefactDTO> findById(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @DeleteMapping("delete")
    public void deleteById(@PathVariable("id") UUID id) {
        service.deleteById(id);
    }

    @PostMapping("find")
    public ResponseEntity<List<ArtefactDTO>> findBy(@RequestBody ArtefactSearchDTO searchDTO) {
        return ResponseEntity.ok(service.searchByField(searchDTO));
    }

}
