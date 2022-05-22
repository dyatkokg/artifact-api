package me.dyatkokg.artefactapi.repository;

import me.dyatkokg.artefactapi.dto.ArtefactSearchDTO;
import me.dyatkokg.artefactapi.entity.Artefact;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ArtefactRepository extends PagingAndSortingRepository<Artefact, UUID> {

    List<Artefact> findByCategory(String category);

    List<Artefact> findByUserId(UUID id);

   List<Artefact> findByDescriptionContains(String description);

}
