package me.dyatkokg.artefactapi.repository;

import me.dyatkokg.artefactapi.entity.Artefact;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface ArtefactRepository extends PagingAndSortingRepository<Artefact, UUID> {


}
