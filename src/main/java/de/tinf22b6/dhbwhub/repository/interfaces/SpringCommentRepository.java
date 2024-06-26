package de.tinf22b6.dhbwhub.repository.interfaces;

import de.tinf22b6.dhbwhub.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SpringCommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPostId(Long postId);
}
