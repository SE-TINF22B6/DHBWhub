package de.tinf22b6.dhbwhub.repository.interfaces;

import de.tinf22b6.dhbwhub.model.PostTag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpringPostTagRepository extends JpaRepository<PostTag, Long> {

    List<PostTag> findByPostId(Long postId);
}
