package me.dyatkokg.artefactapi.repository;

import me.dyatkokg.artefactapi.entity.Artifact;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.UUID;

public interface ArtifactRepository extends PagingAndSortingRepository<Artifact, UUID> {

    List<Artifact> findByCategory(String category);

    List<Artifact> findByUserId(UUID id);

    List<Artifact> findByDescriptionContains(String description);

}
