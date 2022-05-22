package me.dyatkokg.artefactapi.service.implementation;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import me.dyatkokg.artefactapi.dto.ArtefactDTO;
import me.dyatkokg.artefactapi.dto.ArtefactMetadataDTO;
import me.dyatkokg.artefactapi.entity.Artefact;
import me.dyatkokg.artefactapi.mapper.ArtefactMapper;
import me.dyatkokg.artefactapi.repository.ArtefactRepository;
import me.dyatkokg.artefactapi.service.ArtefactService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

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

    }

    @Override
    public List<Artefact> searchByField(Artefact artefact) {
        return null;
    }
}
