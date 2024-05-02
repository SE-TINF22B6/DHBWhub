package de.tinf22b6.dhbwhub.repository.interfaces;

import de.tinf22b6.dhbwhub.model.EventComment;
import org.springframework.data.jpa.repository.JpaRepository;



public interface SpringEventCommentRepository extends JpaRepository<EventComment, Long> {

}
