package de.tinf22b6.dhbwhub.repository.interfaces;

import de.tinf22b6.dhbwhub.model.EventTag;
import de.tinf22b6.dhbwhub.model.PostTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SpringEventTagRepository extends JpaRepository<EventTag, Long> {

    List<EventTag> findByEventPostId(Long eventPostId);

    @Query(value = "SELECT * FROM event_tag WHERE event_post_id = ?1 AND tag = ?2", nativeQuery = true)
    PostTag findByEventPostIdAndTag(Long eventPostId, String tag);

}
