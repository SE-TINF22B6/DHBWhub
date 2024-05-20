package de.tinf22b6.dhbwhub.repository;

import de.tinf22b6.dhbwhub.model.log_tables.LikeLogtableEventComment;
import de.tinf22b6.dhbwhub.model.log_tables.LikeLogtableEventPost;
import de.tinf22b6.dhbwhub.model.log_tables.LikeLogtablePost;
import de.tinf22b6.dhbwhub.model.log_tables.LikeLogtablePostComment;
import de.tinf22b6.dhbwhub.repository.interfaces.SpringLogtableCommentRepository;
import de.tinf22b6.dhbwhub.repository.interfaces.SpringLogtableEventCommentRepository;
import de.tinf22b6.dhbwhub.repository.interfaces.SpringLogtableEventPostRepository;
import de.tinf22b6.dhbwhub.repository.interfaces.SpringLogtablePostRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LogtableRepository {
    private final SpringLogtablePostRepository postRepository;
    private final SpringLogtableCommentRepository commentRepository;
    private final SpringLogtableEventPostRepository eventPostRepository;
    private final SpringLogtableEventCommentRepository eventCommentRepository;
    private static final String ENTRY_ALREADY_LOGGED = "Entry already logged!";
    private static final String ENTRY_DOESNT_EXIST = "Entry does not exist!";

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

    public List<LikeLogtableEventComment> findAllEventComments() {
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

    public void savePost(LikeLogtablePost post) {
        if (checkIfPostExists(post.getPost().getId(), post.getUser().getId())){
            throw new EntityExistsException(ENTRY_ALREADY_LOGGED);
        }
        postRepository.save(post);
    }

    public void saveComment(LikeLogtablePostComment comment) {
        if (checkIfCommentExists(comment.getComment().getId(), comment.getUser().getId())){
            throw new EntityExistsException(ENTRY_ALREADY_LOGGED);
        }
        commentRepository.save(comment);
    }

    public void saveEvent(LikeLogtableEventPost eventPost) {
        if (checkIfEventPostExists(eventPost.getEventPost().getId(), eventPost.getUser().getId())){
            throw new EntityExistsException(ENTRY_ALREADY_LOGGED);
        }
        eventPostRepository.save(eventPost);
    }

    public void saveEventComment(LikeLogtableEventComment eventComment) {
        if (checkIfEventCommentExists(eventComment.getEventComment().getId(), eventComment.getUser().getId())){
            throw new EntityExistsException(ENTRY_ALREADY_LOGGED);
        }
        eventCommentRepository.save(eventComment);
    }

    public void deletePost(Long id) {
        if (!postRepository.existsById(id)){
            throw new EntityNotFoundException(ENTRY_DOESNT_EXIST);
        }
        postRepository.deleteById(id);
    }

    public void deleteComment(Long id) {
        if (!commentRepository.existsById(id)){
            throw new EntityNotFoundException(ENTRY_DOESNT_EXIST);
        }
        commentRepository.deleteById(id);
    }

    public void deleteEventPost(Long id) {
        if (!eventPostRepository.existsById(id)){
            throw new EntityNotFoundException(ENTRY_DOESNT_EXIST);
        }
        eventPostRepository.deleteById(id);
    }

    public void deleteEventComment(Long id) {
        if (!eventCommentRepository.existsById(id)){
            throw new EntityNotFoundException(ENTRY_DOESNT_EXIST);
        }
        eventCommentRepository.deleteById(id);
    }

    public boolean checkIfPostExists(Long postId, Long userId){
        return findPost(postId, userId) != null;
    }

    public boolean checkIfCommentExists(Long commentId, Long userId){
        return findComment(commentId, userId) != null;
    }

    public boolean checkIfEventPostExists(Long eventId, Long userId){
        return findEventPost(eventId, userId) != null;
    }

    public boolean checkIfEventCommentExists(Long eventCommentId, Long userId){
        return findEventComment(eventCommentId, userId) != null;
    }
}
