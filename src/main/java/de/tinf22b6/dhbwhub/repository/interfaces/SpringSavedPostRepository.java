package de.tinf22b6.dhbwhub.repository.interfaces;

import de.tinf22b6.dhbwhub.model.SavedPost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SpringSavedPostRepository extends JpaRepository<SavedPost, Long> {
    List<SavedPost> findByUserId(Long id);
}
