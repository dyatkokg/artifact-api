package me.dyatkokg.artefactapi.service.implementation;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import me.dyatkokg.artefactapi.configuration.filter.FilterUtils;
import me.dyatkokg.artefactapi.dto.ArtifactDTO;
import me.dyatkokg.artefactapi.dto.ArtifactMetadataDTO;
import me.dyatkokg.artefactapi.dto.ArtifactSearchDTO;
import me.dyatkokg.artefactapi.entity.Artifact;
import me.dyatkokg.artefactapi.entity.User;
import me.dyatkokg.artefactapi.exception.ArtifactNotFoundException;
import me.dyatkokg.artefactapi.exception.UserNotFoundException;
import me.dyatkokg.artefactapi.mapper.ArtifactMapper;
import me.dyatkokg.artefactapi.repository.ArtifactRepository;
import me.dyatkokg.artefactapi.repository.UserRepository;
import me.dyatkokg.artefactapi.service.ArtefactService;
import me.dyatkokg.artefactapi.service.TokenProvider;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ArtifactServiceImplementation implements ArtefactService {

    private final ArtifactRepository repository;

    private final UserRepository userRepository;

    private final ArtifactMapper mapper;

    private final TokenProvider provider;

    @Override
    @SneakyThrows
    public ArtifactDTO update(MultipartFile file, ArtifactMetadataDTO metadataDTO) {
        byte[] bytes = file.getBytes();
        Artifact artifact;
        if (Objects.nonNull(metadataDTO.getUserId())) {
            artifact = repository.findById(metadataDTO.getUserId()).orElseThrow(ArtifactNotFoundException::new);
            artifact.setCategory(metadataDTO.getCategory());
            artifact.setDescription(metadataDTO.getDescription());
        } else {
            artifact = mapper.toEntityFromMetadata(metadataDTO);
            artifact.setCreated(LocalDateTime.now());
            artifact.setArtefact(bytes);
            Optional<User> byId = userRepository.findById(UUID.fromString(provider.getSubject(FilterUtils.getTokenFromSecurityContext())));
            artifact.setUser(byId.orElseThrow(UserNotFoundException::new));
        }
        return mapper.toDTO(repository.save(artifact));
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
            return repository.findByUserId(UUID.fromString(searchDTO.getUserId())).stream().map(mapper::toDTO).collect(Collectors.toList());
        } else if (Objects.nonNull(searchDTO.getDescription())) {
            return repository.findByDescriptionContains(searchDTO.getDescription()).stream().map(mapper::toDTO).collect(Collectors.toList());
        } else return new ArrayList<>();
    }

}
