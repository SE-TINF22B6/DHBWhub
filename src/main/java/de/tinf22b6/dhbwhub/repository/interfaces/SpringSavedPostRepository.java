package de.tinf22b6.dhbwhub.repository.interfaces;

import de.tinf22b6.dhbwhub.model.SavedPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringSavedPostRepository extends JpaRepository<SavedPost, Long> {}
