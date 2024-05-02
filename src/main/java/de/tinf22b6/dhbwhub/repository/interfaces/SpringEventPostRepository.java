package de.tinf22b6.dhbwhub.repository.interfaces;

import de.tinf22b6.dhbwhub.model.EventPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringEventPostRepository extends JpaRepository<EventPost, Long> {

}
