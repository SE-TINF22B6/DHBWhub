package de.tinf22b6.dhbwhub.service;

import de.tinf22b6.dhbwhub.exception.NoSuchEntryException;
import de.tinf22b6.dhbwhub.mapper.CommentMapper;
import de.tinf22b6.dhbwhub.mapper.NotificationMapper;
import de.tinf22b6.dhbwhub.model.Comment;
import de.tinf22b6.dhbwhub.model.Post;
import de.tinf22b6.dhbwhub.model.User;
import de.tinf22b6.dhbwhub.model.log_tables.LikeLogtablePostComment;
import de.tinf22b6.dhbwhub.model.notification_tables.CommentLikeNotification;
import de.tinf22b6.dhbwhub.model.notification_tables.PostCommentNotification;
import de.tinf22b6.dhbwhub.proposal.CommentProposal;
import de.tinf22b6.dhbwhub.proposal.simplified_models.CommentThreadViewProposal;
import de.tinf22b6.dhbwhub.proposal.simplified_models.CreateCommentProposal;
import de.tinf22b6.dhbwhub.proposal.simplified_models.LikeCommentProposal;
import de.tinf22b6.dhbwhub.proposal.simplified_models.UpdateCommentProposal;
import de.tinf22b6.dhbwhub.repository.*;
import de.tinf22b6.dhbwhub.service.interfaces.CommentService;
import jakarta.persistence.EntityExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository repository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final LogtableRepository logtableRepository;
    private final NotificationRepository notificationRepository;

    public CommentServiceImpl(@Autowired CommentRepository repository,
                              @Autowired UserRepository userRepository,
                              @Autowired PostRepository postRepository,
                              @Autowired LogtableRepository logtableRepository,
                              @Autowired NotificationRepository notificationRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.logtableRepository = logtableRepository;
        this.notificationRepository = notificationRepository;
    }

    @Override
    public List<Comment> getAll() {
        return repository.findAll();
    }

    @Override
    public Comment create(CommentProposal proposal) {
        Comment comment = CommentMapper.mapToModel(proposal);
        return repository.save(comment);
    }

    @Override
    public CommentThreadViewProposal create(CreateCommentProposal proposal) {
        User user = userRepository.findByAccountId(proposal.getAccountId());
        Post post = postRepository.find(proposal.getPostId());

        Comment comment = repository.save(CommentMapper.mapToModel(proposal,user,post));


        // Notify Post-Author
        if(!Objects.equals(post.getUser().getId(), user.getId()) &&
                !notificationRepository.checkIfPostCommentEntryExists(user.getId(), post.getId())){
            PostCommentNotification notification = NotificationMapper.mapToPostCommentNotification(comment, user);
            notification.setAccumulatedId(null);
            notificationRepository.savePostCommentNotification(notification);
        }
        return CommentMapper.mapToThreadView(comment);
    }

    @Override
    public Comment get(Long id) {
        Comment comment = repository.find(id);
        if (comment == null) {
            throw new NoSuchEntryException(String.format("%s with ID %d does not exist", Comment.class.getSimpleName(), id));
        }
        return comment;
    }

    @Override
    public Comment update(Long id, CommentProposal proposal) {
        // Check if comment exists
        get(id);

        Comment comment = CommentMapper.mapToModel(proposal);
        comment.setId(id);
        return repository.save(comment);
    }

    @Override
    public CommentThreadViewProposal update(Long id, UpdateCommentProposal proposal) {
        Comment initialComment = get(id);
        Comment updatedComment = CommentMapper.mapToModel(proposal, initialComment);
        updatedComment.setId(id);

        repository.save(updatedComment);

        return CommentMapper.mapToThreadView(get(id));
    }

    @Override
    public void delete(Long id) {
        // Check if comment exists
        get(id);

        repository.delete(id);
    }

    @Override
    public List<Comment> findByPostId(Long postId) {
        return repository.findByPostId(postId);
    }

    @Override
    public int increaseLikes(LikeCommentProposal likeCommentProposal) {
        Comment comment = get(likeCommentProposal.getCommentId());
        int likes = comment.getLikes() + 1;
        Comment updatedComment = CommentMapper.mapToModel(comment,likes);
        updatedComment.setId(likeCommentProposal.getCommentId());

        // Log into Liketable
        User user = userRepository.find(likeCommentProposal.getUserId());
        LikeLogtablePostComment likeLogtableComment = new LikeLogtablePostComment(user, comment);
        if(logtableRepository.checkIfCommentExists(comment.getId(), user.getId())) {
            throw new EntityExistsException("Entity already exists!");
        }
        logtableRepository.saveComment(likeLogtableComment);

        // Notify User
        if(!Objects.equals(comment.getUser().getId(), user.getId()) &&
            !notificationRepository.checkIfCommentLikeEntryExists(user.getId(), comment.getPost().getId())){
            CommentLikeNotification notification = NotificationMapper.mapToCommentLikeNotification(comment, user);
            notification.setAccumulatedId(null);
            notificationRepository.saveCommentLikeNotification(notification);
        }

        return repository.save(updatedComment).getLikes();
    }

    @Override
    public int decreaseLikes(LikeCommentProposal likeCommentProposal) {
        Comment comment = get(likeCommentProposal.getCommentId());
        int likes = comment.getLikes() != 0 ? comment.getLikes() - 1 : 0;
        Comment updatedComment = CommentMapper.mapToModel(comment,likes);
        updatedComment.setId(likeCommentProposal.getCommentId());

        // Log into Liketable
        LikeLogtablePostComment likeLogtableComment = logtableRepository.findComment(likeCommentProposal.getCommentId(), likeCommentProposal.getUserId());
        if(likeLogtableComment == null){
            throw new NoSuchEntryException("Entity does not exist!");
        }
        logtableRepository.deleteComment(likeLogtableComment.getId());

        return repository.save(updatedComment).getLikes();
    }

    @Override
    public boolean isLiked(LikeCommentProposal likeCommentProposal) {
        return logtableRepository.findComment(likeCommentProposal.getCommentId(), likeCommentProposal.getUserId()) != null;
    }

    @Override
    public CommentThreadViewProposal getCommentThreadView(Long id) {
        return CommentMapper.mapToThreadView(get(id));
    }
}
