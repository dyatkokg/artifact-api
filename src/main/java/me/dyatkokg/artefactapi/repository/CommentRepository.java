package me.dyatkokg.artefactapi.repository;

import me.dyatkokg.artefactapi.entity.Comment;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface CommentRepository extends PagingAndSortingRepository<Comment, UUID> {
}
