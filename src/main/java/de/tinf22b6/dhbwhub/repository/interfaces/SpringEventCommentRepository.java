package de.tinf22b6.dhbwhub.repository.interfaces;

import de.tinf22b6.dhbwhub.model.EventComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface SpringEventCommentRepository extends JpaRepository<EventComment, Long> {
    List<EventComment> findByEventPostId(Long id);
}
