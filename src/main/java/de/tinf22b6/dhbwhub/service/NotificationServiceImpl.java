package de.tinf22b6.dhbwhub.service;

import de.tinf22b6.dhbwhub.mapper.NotificationMapper;
import de.tinf22b6.dhbwhub.model.NotificationType;
import de.tinf22b6.dhbwhub.model.notification_tables.*;
import de.tinf22b6.dhbwhub.proposal.simplified_models.DeleteNotificationProposal;
import de.tinf22b6.dhbwhub.proposal.simplified_models.HomepageNotificationProposal;
import de.tinf22b6.dhbwhub.repository.NotificationRepository;
import de.tinf22b6.dhbwhub.service.interfaces.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class NotificationServiceImpl implements NotificationService {
    private final NotificationRepository repository;

    public NotificationServiceImpl(@Autowired NotificationRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<HomepageNotificationProposal> getUserNotifications(Long id) {
        List<HomepageNotificationProposal> notificationList = new ArrayList<>();
        notificationList.addAll(parseCommentLikeNotifications(id));
        notificationList.addAll(parseEventCommentLikeNotifications(id));
        notificationList.addAll(parseFollowNotifications(id));
        notificationList.addAll(parsePostCommentNotifications(id));
        notificationList.addAll(parsePostLikeNotifications(id));

        return notificationList;
    }

    private List<HomepageNotificationProposal> parsePostLikeNotifications(Long id) {
        List<PostLikeNotification> postLikeNotifications = repository.findPostLikeNotificationsByUser(id);
        if(postLikeNotifications.isEmpty()) return new ArrayList<>();

        List<HomepageNotificationProposal> notificationList = new ArrayList<>();
        Map<Long, List<PostLikeNotification>> groupedList = postLikeNotifications.stream().collect(Collectors.groupingBy(PostLikeNotification::getPostId));

        for(Map.Entry<Long, List<PostLikeNotification>> entry : groupedList.entrySet()) {
            List<PostLikeNotification> postLikeNotificationList = entry.getValue();
            Long accumulatedId = postLikeNotificationList.get(0) != null ? postLikeNotificationList.get(0).getPostId() : null;
            if(postLikeNotificationList.size() > 1) {
                postLikeNotificationList.stream().filter(n -> n.getAccumulatedId() == null).forEach(n-> {
                    n.setAccumulatedId(accumulatedId);
                    repository.savePostLikeNotification(n);
                });
                notificationList.add(NotificationMapper.mapToGroupNotification(postLikeNotificationList.get(0), postLikeNotificationList.size()));
            } else if (postLikeNotificationList.size() == 1) {
                notificationList.add(NotificationMapper.mapToSingleNotification(postLikeNotificationList.get(0)));
            }
        }

        return notificationList;
    }

    private List<HomepageNotificationProposal> parsePostCommentNotifications(Long id) {
        List<PostCommentNotification> postCommentNotifications = repository.findPostCommentNotificationsByUser(id);
        if(postCommentNotifications.isEmpty()) return new ArrayList<>();

        List<HomepageNotificationProposal> notificationList = new ArrayList<>();
        Map<Long, List<PostCommentNotification>> groupedList = postCommentNotifications.stream().collect(Collectors.groupingBy(PostCommentNotification::getPostId));

        for(Map.Entry<Long, List<PostCommentNotification>> entry : groupedList.entrySet()) {
            List<PostCommentNotification> postCommentNotificationList = entry.getValue();
            Long accumulatedId = postCommentNotificationList.get(0) != null ? postCommentNotificationList.get(0).getId() : null;
            if(postCommentNotificationList.size() > 1) {
                postCommentNotificationList.stream().filter(n -> n.getAccumulatedId() == null).forEach(n-> {
                    n.setAccumulatedId(accumulatedId);
                    repository.savePostCommentNotification(n);
                });
                notificationList.add(NotificationMapper.mapToGroupNotification(postCommentNotificationList.get(0), postCommentNotificationList.size()));
            } else if (postCommentNotificationList.size() == 1) {
                notificationList.add(NotificationMapper.mapToSingleNotification(postCommentNotificationList.get(0)));
            }
        }

        return notificationList;
    }

    private List<HomepageNotificationProposal> parseFollowNotifications(Long id) {
        List<FollowNotification> followNotifications = repository.findFollowNotificationsByUser(id);
        if(followNotifications.isEmpty()) return new ArrayList<>();

        List<HomepageNotificationProposal> notificationList = new ArrayList<>();
        Long accumulatedId = followNotifications.get(0) != null ? followNotifications.get(0).getId() : null;
        if(followNotifications.size() > 1) {
                followNotifications.stream().filter(n -> n.getAccumulatedId() == null).forEach(n->{
                        n.setAccumulatedId(accumulatedId);
                        repository.saveFollowNotification(n);
                });
                notificationList.add(NotificationMapper.mapToGroupNotification(followNotifications.get(0), followNotifications.size()));
        } else if (followNotifications.size() == 1) {
                notificationList.add(NotificationMapper.mapToSingleNotification(followNotifications.get(0)));
        }
        return notificationList;
    }

    private List<HomepageNotificationProposal> parseEventCommentLikeNotifications(Long id) {
        List<EventCommentLikeNotification> eventCommentLikeNotifications = repository.findEventCommentLikeNotificationsByUser(id);
        if(eventCommentLikeNotifications.isEmpty()) return new ArrayList<>();

        List<HomepageNotificationProposal> notificationList = new ArrayList<>();
        Map<Long, List<EventCommentLikeNotification>> groupedList = eventCommentLikeNotifications.stream().collect(Collectors.groupingBy(EventCommentLikeNotification::getEventPostId));

        for(Map.Entry<Long, List<EventCommentLikeNotification>> entry : groupedList.entrySet()) {
            List<EventCommentLikeNotification> eventCommentLikeNotificationList = entry.getValue();
            Long accumulatedId = eventCommentLikeNotificationList.get(0) != null ? eventCommentLikeNotificationList.get(0).getId() : null;
            if(eventCommentLikeNotificationList.size() > 1) {
                eventCommentLikeNotificationList.stream().filter(n -> n.getAccumulatedId() == null).forEach(n-> {
                    n.setAccumulatedId(accumulatedId);
                    repository.saveEventCommentLikeNotification(n);
                });
                notificationList.add(NotificationMapper.mapToGroupNotification(eventCommentLikeNotificationList.get(0), eventCommentLikeNotificationList.size()));
            } else if (eventCommentLikeNotificationList.size() == 1) {
                notificationList.add(NotificationMapper.mapToSingleNotification(eventCommentLikeNotificationList.get(0)));
            }
        }

        return notificationList;
    }

    private List<HomepageNotificationProposal> parseCommentLikeNotifications(Long id) {
        List<CommentLikeNotification> commentLikeNotifications = repository.findCommentLikeNotificationsByUser(id);
        if(commentLikeNotifications.isEmpty()) return new ArrayList<>();

        List<HomepageNotificationProposal> notificationList = new ArrayList<>();
        Map<Long, List<CommentLikeNotification>> groupedList = commentLikeNotifications.stream().collect(Collectors.groupingBy(CommentLikeNotification::getPostId));

        for(Map.Entry<Long, List<CommentLikeNotification>> entry : groupedList.entrySet()) {
            List<CommentLikeNotification> commentLikeNotificationList = entry.getValue();
            Long accumulatedId = commentLikeNotificationList.get(0) != null ? commentLikeNotificationList.get(0).getId() : null;
            if(commentLikeNotificationList.size() > 1) {
                commentLikeNotificationList.stream().filter(n -> n.getAccumulatedId() == null).forEach(n-> {
                    n.setAccumulatedId(accumulatedId);
                    repository.saveCommentLikeNotification(n);
                });
                notificationList.add(NotificationMapper.mapToGroupNotification(commentLikeNotificationList.get(0), commentLikeNotificationList.size()));
            } else if (commentLikeNotificationList.size() == 1) {
                notificationList.add(NotificationMapper.mapToSingleNotification(commentLikeNotificationList.get(0)));
            }
        }
        return notificationList;
    }

    @Override
    public void deleteNotification(DeleteNotificationProposal deleteNotificationProposal) {
        Long groupId = deleteNotificationProposal.getGroupId();
        if(groupId != null ) {
            deleteGroupNotifications(deleteNotificationProposal);
        }else{
            deleteSingleNotifications(deleteNotificationProposal);
        }
    }

    private void deleteGroupNotifications(DeleteNotificationProposal deleteNotificationProposal) {
        String type = deleteNotificationProposal.getType();
        Long groupId = deleteNotificationProposal.getGroupId();

        if (type.equals(NotificationType.TYPE_COMMENT_LIKE.getType())){
            repository.findCommentLikeNotificationsByGroup(groupId).forEach(repository::deleteCommentLikeNotification);
        } else if (type.equals(NotificationType.TYPE_FOLLOW.getType())){
            repository.findFollowNotificationsByGroup(groupId).forEach(repository::deleteFollowNotification);
        } else if (type.equals(NotificationType.TYPE_POST_LIKE.getType())){
            repository.findPostLikeNotificationsByGroup(groupId).forEach(repository::deletePostLikeNotification);
        } else if (type.equals(NotificationType.TYPE_POST_COMMENT.getType())){
            repository.findPostCommentNotificationsByGroup(groupId).forEach(repository::deletePostCommentNotification);
        } else if (type.equals(NotificationType.TYPE_EVENT_COMMENT_LIKE.getType())){
            repository.findEventCommentLikeNotificationsByGroup(groupId).forEach(repository::deleteEventCommentLikeNotification);
        }
    }

    private void deleteSingleNotifications(DeleteNotificationProposal deleteNotificationProposal) {
        String type = deleteNotificationProposal.getType();
        Long notificationId = deleteNotificationProposal.getNotificationId();

        if (type.equals(NotificationType.TYPE_COMMENT_LIKE.getType())){
            repository.deleteCommentLikeNotification(repository.findCommentLikeNotificationById(notificationId));
        } else if (type.equals(NotificationType.TYPE_FOLLOW.getType())){
            repository.deleteFollowNotification(repository.findFollowNotificationById(notificationId));
        } else if (type.equals(NotificationType.TYPE_POST_LIKE.getType())){
            repository.deletePostLikeNotification(repository.findPostLikeNotificationById(notificationId));
        } else if (type.equals(NotificationType.TYPE_POST_COMMENT.getType())){
            repository.deletePostCommentNotification(repository.findPostCommentNotificationById(notificationId));
        } else if (type.equals(NotificationType.TYPE_EVENT_COMMENT_LIKE.getType())){
            repository.deleteEventCommentLikeNotification(repository.findEventCommentLikeNotificationById(notificationId));
        }
    }
}
