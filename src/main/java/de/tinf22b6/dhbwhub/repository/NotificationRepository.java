package de.tinf22b6.dhbwhub.repository;

import de.tinf22b6.dhbwhub.model.notification_tables.*;
import de.tinf22b6.dhbwhub.repository.interfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class NotificationRepository {
    private final SpringCommentLikeNotificationRepository commentLikeNotificationRepository;
    private final SpringEventCommentLikeNotificationRepository eventCommentLikeNotificationRepository;
    private final SpringFollowNotificationRepository followNotificationRepository;
    private final SpringPostCommentNotificationRepository postCommentNotificationRepository;
    private final SpringPostLikeNotificationRepository postLikeNotificationRepository;

    public NotificationRepository(@Autowired SpringCommentLikeNotificationRepository commentLikeNotificationRepository,
                                  @Autowired SpringEventCommentLikeNotificationRepository eventCommentLikeNotificationRepository,
                                  @Autowired SpringFollowNotificationRepository followNotificationRepository,
                                  @Autowired SpringPostCommentNotificationRepository postCommentNotificationRepository,
                                  @Autowired SpringPostLikeNotificationRepository postLikeNotificationRepository) {
        this.commentLikeNotificationRepository = commentLikeNotificationRepository;
        this.eventCommentLikeNotificationRepository = eventCommentLikeNotificationRepository;
        this.followNotificationRepository = followNotificationRepository;
        this.postCommentNotificationRepository = postCommentNotificationRepository;
        this.postLikeNotificationRepository = postLikeNotificationRepository;
    }

    public List<CommentLikeNotification> getAllCommentLikeNotifications() {
        return commentLikeNotificationRepository.findAll();
    }

    public List<EventCommentLikeNotification> getAllEventCommentLikeNotifications() {
        return eventCommentLikeNotificationRepository.findAll();
    }

    public List<FollowNotification> getAllFollowNotifications() {
        return followNotificationRepository.findAll();
    }

    public List<PostCommentNotification> getAllPostCommentNotifications() {
        return postCommentNotificationRepository.findAll();
    }

    public List<PostLikeNotification> getAllPostLikeNotifications() {
        return postLikeNotificationRepository.findAll();
    }

    public List<CommentLikeNotification> findCommentLikeNotificationsByUser(Long id) {
        return commentLikeNotificationRepository.findUnseenNotifications(id);
    }

    public List<EventCommentLikeNotification> findEventCommentLikeNotificationsByUser(Long id) {
        return eventCommentLikeNotificationRepository.findUnseenNotifications(id);
    }

    public List<FollowNotification> findFollowNotificationsByUser(Long id) {
        return followNotificationRepository.findUnseenNotifications(id);
    }

    public List<PostCommentNotification> findPostCommentNotificationsByUser(Long id) {
        return postCommentNotificationRepository.findUnseenNotifications(id);
    }

    public List<PostLikeNotification> findPostLikeNotificationsByUser(Long id) {
        return postLikeNotificationRepository.findUnseenNotifications(id);
    }

    public List<CommentLikeNotification> findCommentLikeNotificationsByGroup(Long id) {
        return commentLikeNotificationRepository.findByAccumulatedId(id);
    }

    public List<EventCommentLikeNotification> findEventCommentLikeNotificationsByGroup(Long id) {
        return eventCommentLikeNotificationRepository.findByAccumulatedId(id);
    }

    public List<FollowNotification> findFollowNotificationsByGroup(Long id) {
        return followNotificationRepository.findByAccumulatedId(id);
    }

    public List<PostCommentNotification> findPostCommentNotificationsByGroup(Long id) {
        return postCommentNotificationRepository.findByAccumulatedId(id);
    }

    public List<PostLikeNotification> findPostLikeNotificationsByGroup(Long id) {
        return postLikeNotificationRepository.findByAccumulatedId(id);
    }
    public CommentLikeNotification findCommentLikeNotificationById(Long id) {
        return commentLikeNotificationRepository.findById(id).orElse(null);
    }

    public EventCommentLikeNotification findEventCommentLikeNotificationById(Long id) {
        return eventCommentLikeNotificationRepository.findById(id).orElse(null);
    }

    public FollowNotification findFollowNotificationById(Long id) {
        return followNotificationRepository.findById(id).orElse(null);
    }

    public PostCommentNotification findPostCommentNotificationById(Long id) {
        return postCommentNotificationRepository.findById(id).orElse(null);
    }

    public PostLikeNotification findPostLikeNotificationById(Long id) {
        return postLikeNotificationRepository.findById(id).orElse(null);
    }

    public void saveCommentLikeNotification(CommentLikeNotification notification) {
        commentLikeNotificationRepository.save(notification);
    }

    public void saveEventCommentLikeNotification(EventCommentLikeNotification notification) {
        eventCommentLikeNotificationRepository.save(notification);
    }

    public void saveFollowNotification(FollowNotification notification) {
        followNotificationRepository.save(notification);
    }

    public void savePostCommentNotification(PostCommentNotification notification) {
        postCommentNotificationRepository.save(notification);
    }

    public void savePostLikeNotification(PostLikeNotification notification) {
        postLikeNotificationRepository.save(notification);
    }

    public void deleteCommentLikeNotification(CommentLikeNotification notification) {
        commentLikeNotificationRepository.delete(notification);
    }
    public void deleteEventCommentLikeNotification(EventCommentLikeNotification notification) {
        eventCommentLikeNotificationRepository.delete(notification);
    }

    public void deleteFollowNotification(FollowNotification notification) {
        followNotificationRepository.delete(notification);
    }

    public void deletePostCommentNotification(PostCommentNotification notification) {
        postCommentNotificationRepository.delete(notification);
    }

    public void deletePostLikeNotification(PostLikeNotification notification) {
        postLikeNotificationRepository.delete(notification);
    }
}
