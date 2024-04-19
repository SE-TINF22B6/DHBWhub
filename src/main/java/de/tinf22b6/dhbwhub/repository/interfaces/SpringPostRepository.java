package de.tinf22b6.dhbwhub.repository.interfaces;

import de.tinf22b6.dhbwhub.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpringPostRepository extends JpaRepository<Post, Long> {
}
