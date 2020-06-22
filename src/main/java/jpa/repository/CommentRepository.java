package jpa.repository;

import jpa.model.Comments;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comments,Long> {

    Page<Comments> findByPostId(Long postId, Pageable pageable);

    Optional<Comments> findByIdAndPostId( Long id, Long postId);
}
