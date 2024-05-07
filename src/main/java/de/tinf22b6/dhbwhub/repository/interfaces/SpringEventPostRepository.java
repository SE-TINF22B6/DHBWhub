package de.tinf22b6.dhbwhub.repository.interfaces;

import de.tinf22b6.dhbwhub.model.EventPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SpringEventPostRepository extends JpaRepository<EventPost, Long> {

    @Query(value = "SELECT v.comment_amount FROM view_event_comment_amount v WHERE v.event_post_id = ?1", nativeQuery = true)
    Integer getCommentAmount(Long id);
}
