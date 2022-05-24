package me.dyatkokg.artefactapi.service.implementation;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import me.dyatkokg.artefactapi.dto.ArtifactDTO;
import me.dyatkokg.artefactapi.dto.ArtifactMetadataDTO;
import me.dyatkokg.artefactapi.dto.ArtifactSearchDTO;
import me.dyatkokg.artefactapi.entity.Artifact;
import me.dyatkokg.artefactapi.exception.ArtifactNotFoundException;
import me.dyatkokg.artefactapi.mapper.ArtifactMapper;
import me.dyatkokg.artefactapi.repository.ArtifactRepository;
import me.dyatkokg.artefactapi.service.ArtefactService;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
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
public class ArtifactServiceImplementation implements ArtefactService {

    private final ArtifactRepository repository;
    private final ArtifactMapper mapper;

    @Override
    @SneakyThrows
    public ArtifactDTO update(MultipartFile file, ArtifactMetadataDTO metadataDTO) {
        byte[] bytes = file.getBytes();
        Artifact artifact = mapper.toEntityFromMetadata(metadataDTO);
        artifact.setArtefact(bytes);
        artifact.setCreated(LocalDateTime.now());
        artifact = repository.save(artifact);
        return mapper.toDTO(artifact);
    }

    @Override
    public ArtifactDTO getById(UUID id) {
        return repository.findById(id).map(mapper::toDTO).orElseThrow(ArtifactNotFoundException::new);
    }

    @Override
    public void deleteById(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public List<ArtifactDTO> searchByField(ArtifactSearchDTO searchDTO) {
        if (Objects.nonNull(searchDTO.getCategory())) {
            return repository.findByCategory(searchDTO.getCategory()).stream().map(mapper::toDTO).collect(Collectors.toList());
        } else if (Objects.nonNull(searchDTO.getUserId())) {
            return repository.findByUserId(searchDTO.getUserId().getId()).stream().map(mapper::toDTO).collect(Collectors.toList());
        } else if (Objects.nonNull(searchDTO.getDescription())) {
            return repository.findByDescriptionContains(searchDTO.getDescription()).stream().map(mapper::toDTO).collect(Collectors.toList());
        } else return new ArrayList<>();
    }

//    @EventListener
//    public void onApplicationReady(ApplicationReadyEvent event){
//       Artifact artifact =new Artifact();
//       artifact.setCategory("пизда");
//       artifact.setDescription("что то на белоруском");
//       artifact.setCreated(LocalDateTime.now());
//       repository.save(artifact);
//    }
}
