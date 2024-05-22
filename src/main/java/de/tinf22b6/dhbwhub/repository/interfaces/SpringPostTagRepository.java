package de.tinf22b6.dhbwhub.repository.interfaces;

import de.tinf22b6.dhbwhub.model.PostTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SpringPostTagRepository extends JpaRepository<PostTag, Long> {

    List<PostTag> findByPostId(Long postId);

    @Query(value = "SELECT * FROM post_tag WHERE post_id = ?1 AND tag = ?2", nativeQuery = true)
    PostTag findByPostIdAndTag(Long postId, String tag);

    List<PostTag> findByTag(String tag);

    @Query(value = "SELECT * FROM view_popular_tags ", nativeQuery = true)
    List<PostTag> findPopularTags();

    @Query(value = "SELECT * FROM post_tag WHERE tag LIKE '%?1%'", nativeQuery = true)
    List<PostTag> findTagsWithKeyword(String keyword);
}
