package me.dyatkokg.artefactapi.repository;

import me.dyatkokg.artefactapi.entity.Artifact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface ArtifactRepository extends PagingAndSortingRepository<Artifact, UUID> {

    Page<Artifact> findByCategory(String category, Pageable pageable);

    Page<Artifact> findByUserId(UUID id, Pageable pageable);

    Page<Artifact> findByDescriptionContains(String description, Pageable pageable);

    @Query(value = "select a.id, a.artefact, a.category, a.created_at, a.description, u.id as user_id, u.username\n" +
            "from comment c \n" +
            "left join artifact a on c.artifact_id = a.id \n" +
            "left join users u on a.user_id = u.id \n" +
            "where c.content like %:content% ", countQuery = "SELECT count(*)\n" +
            "           from comment c \n" +
            "            left join artifact a on c.artifact_id = a.id \n" +
            "            left join users u on a.user_id = u.id \n" +
            "            where c.content like %:content%", nativeQuery = true)
    Page<Artifact> findByCommentContains(@Param("content") String content, Pageable pageable);

}
