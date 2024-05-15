package de.tinf22b6.dhbwhub.repository;

import de.tinf22b6.dhbwhub.model.logtables.LikeLogtableEventComment;
import de.tinf22b6.dhbwhub.model.logtables.LikeLogtableEventPost;
import de.tinf22b6.dhbwhub.model.logtables.LikeLogtablePost;
import de.tinf22b6.dhbwhub.model.logtables.LikeLogtablePostComment;
import de.tinf22b6.dhbwhub.repository.interfaces.SpringLogtableCommentRepository;
import de.tinf22b6.dhbwhub.repository.interfaces.SpringLogtableEventCommentRepository;
import de.tinf22b6.dhbwhub.repository.interfaces.SpringLogtableEventPostRepository;
import de.tinf22b6.dhbwhub.repository.interfaces.SpringLogtablePostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LogtableRepository {
    private final SpringLogtablePostRepository postRepository;
    private final SpringLogtableCommentRepository commentRepository;
    private final SpringLogtableEventPostRepository eventPostRepository;
    private final SpringLogtableEventCommentRepository eventCommentRepository;

    public LogtableRepository(@Autowired SpringLogtablePostRepository postRepository,
                              @Autowired SpringLogtableCommentRepository commentRepository,
                              @Autowired SpringLogtableEventPostRepository eventPostRepository,
                              @Autowired SpringLogtableEventCommentRepository eventCommentRepository) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
        this.eventPostRepository = eventPostRepository;
        this.eventCommentRepository = eventCommentRepository;
    }

    public List<LikeLogtablePost> findAllPosts() {
        return postRepository.findAll();
    }

    public List<LikeLogtablePostComment> findAllComments() {
        return commentRepository.findAll();
    }

    public List<LikeLogtableEventPost> findAllEvents() { return eventPostRepository.findAll(); }

    public List<LikeLogtableEventComment> findAll() {
        return eventCommentRepository.findAll();
    }

    public List<LikeLogtablePost> findPostsByUser(Long id) {
        return postRepository.findByUserId(id);
    }

    public List<LikeLogtablePostComment> findCommentsByUser(Long id) {
        return commentRepository.findByUserId(id);
    }

    public List<LikeLogtableEventPost> findEventPostsByUser(Long id) {
        return eventPostRepository.findByUserId(id);
    }

    public List<LikeLogtableEventComment> findEventCommentsByUser(Long id) {
        return eventCommentRepository.findByUserId(id);
    }

    public LikeLogtablePost findPost(Long postId, Long userId) {
        return postRepository.findByPostIdAndUserId(postId,userId).orElse(null);
    }

    public LikeLogtablePostComment findComment(Long commentId, Long userId) {
        return commentRepository.findByCommentIdAndUserId(commentId,userId).orElse(null);
    }

    public LikeLogtableEventPost findEventPost(Long eventId, Long userId) {
        return eventPostRepository.findByEventPostIdAndUserId(eventId,userId).orElse(null);
    }

    public LikeLogtableEventComment findEventComment(Long eventCommentId, Long userId) {
        return eventCommentRepository.findByEventCommentIdAndUserId(eventCommentId,userId).orElse(null);
    }

    public LikeLogtablePost savePost(LikeLogtablePost post) {
        return postRepository.save(post);
    }

    public LikeLogtablePostComment saveComment(LikeLogtablePostComment comment) {
        return commentRepository.save(comment);
    }

    public LikeLogtableEventPost saveEvent(LikeLogtableEventPost eventPost) {
        return eventPostRepository.save(eventPost);
    }

    public LikeLogtableEventComment saveEventComment(LikeLogtableEventComment eventComment) {
        return eventCommentRepository.save(eventComment);
    }

    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }

    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }

    public void deleteEventPost(Long id) {
        eventPostRepository.deleteById(id);
    }

    public void deleteEventComment(Long id) {
        eventCommentRepository.deleteById(id);
    }

}
