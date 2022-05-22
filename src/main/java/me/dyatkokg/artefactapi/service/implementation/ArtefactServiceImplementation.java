package me.dyatkokg.artefactapi.service.implementation;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import me.dyatkokg.artefactapi.dto.ArtefactDTO;
import me.dyatkokg.artefactapi.dto.ArtefactMetadataDTO;
import me.dyatkokg.artefactapi.dto.ArtefactSearchDTO;
import me.dyatkokg.artefactapi.entity.Artefact;
import me.dyatkokg.artefactapi.mapper.ArtefactMapper;
import me.dyatkokg.artefactapi.repository.ArtefactRepository;
import me.dyatkokg.artefactapi.service.ArtefactService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ArtefactServiceImplementation implements ArtefactService {

    private final ArtefactRepository repository;
    private final ArtefactMapper mapper;

    @Override
    @SneakyThrows
    public ArtefactDTO update(MultipartFile file, ArtefactMetadataDTO metadataDTO) {
        byte[] bytes = file.getBytes();
        Artefact artefact = mapper.toEntityFromMetadata(metadataDTO);
        artefact.setArtefact(bytes);
        artefact.setCreated(LocalDateTime.now());
        artefact = repository.save(artefact);
        return mapper.toDTO(artefact);
    }

    @Override
    public ArtefactDTO getById(UUID id) {
        return repository.findById(id).map(mapper::toDTO).orElseThrow();
    }

    @Override
    public void deleteById(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public List<ArtefactDTO> searchByField(ArtefactSearchDTO searchDTO) {
        if (Objects.nonNull(searchDTO.getCategory())) {
            return repository.findByCategory(searchDTO.getCategory()).stream().map(mapper::toDTO).collect(Collectors.toList());
        } else if (Objects.nonNull(searchDTO.getUserId())) {
            return repository.findByUserId(searchDTO.getUserId().getId()).stream().map(mapper::toDTO).collect(Collectors.toList());
        } else if (Objects.nonNull(searchDTO.getDescription())) {
            return repository.findByDescriptionContains(searchDTO.getDescription()).stream().map(mapper::toDTO).collect(Collectors.toList());
        } else return new ArrayList<>();
    }
}
