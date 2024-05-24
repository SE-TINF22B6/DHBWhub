package de.tinf22b6.dhbwhub.repository.interfaces;

import de.tinf22b6.dhbwhub.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SpringPostRepository extends JpaRepository<Post, Long> {

    @Query(value = "SELECT * FROM view_fac_gesundheit_posts", nativeQuery = true)
    List<Post> getFacGesundheitPosts();

    @Query(value = "SELECT * FROM view_fac_technik_posts", nativeQuery = true)
    List<Post> getFacTechnikPosts();

    @Query(value = "SELECT * FROM view_fac_wirtschaft_posts", nativeQuery = true)
    List<Post> getFacWirtschaftPosts();

    @Query(value = "SELECT * FROM view_homepage_posts", nativeQuery = true)
    List<Post> getHomepagePosts();

    @Query(value = "SELECT v.comment_amount FROM view_comment_amount v WHERE v.post_id = ?1", nativeQuery = true)
    Integer getCommentAmount(Long id);

    @Query(value = "SELECT * FROM post WHERE user_id IN (SELECT receiver_id FROM friendship WHERE requester_id = ?1)", nativeQuery = true)
    List<Post> getPostsFromFriends(Long id);

    List<Post> findByUserId(Long userId);

    @Query(value = "SELECT * FROM post WHERE user_id IN (SELECT receiver_id FROM friendship WHERE requester_id = ?1)" +
            " AND post_id IN (SELECT post_id FROM post_tag WHERE tag = ?2)", nativeQuery = true)
    List<Post> getPostsFromFriendsByTag(Long id, String tag);

    List<Post> findByTitleContainingIgnoreCase(String keyword);

}
