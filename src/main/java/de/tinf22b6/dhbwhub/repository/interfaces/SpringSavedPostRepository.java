package de.tinf22b6.dhbwhub.repository.interfaces;

import de.tinf22b6.dhbwhub.model.SavedPost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpringSavedPostRepository extends JpaRepository<SavedPost, Long> {
    List<SavedPost> findByUserId(Long id);
    List<SavedPost> findByUserIdAndPostId(Long userId, Long postId);
}