package de.tinf22b6.dhbwhub.service;

import de.tinf22b6.dhbwhub.exception.NoSuchEntryException;
import de.tinf22b6.dhbwhub.mapper.CommentMapper;
import de.tinf22b6.dhbwhub.mapper.PictureMapper;
import de.tinf22b6.dhbwhub.mapper.PostMapper;
import de.tinf22b6.dhbwhub.model.*;
import de.tinf22b6.dhbwhub.proposal.CommentProposal;
import de.tinf22b6.dhbwhub.proposal.simplifiedModels.*;
import de.tinf22b6.dhbwhub.repository.*;
import de.tinf22b6.dhbwhub.service.interfaces.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository repository;
    private final UserRepository userRepository;
    private final PictureRepository pictureRepository;
    private final PostRepository postRepository;

    public CommentServiceImpl(@Autowired CommentRepository repository,
                              @Autowired UserRepository userRepository,
                              @Autowired PictureRepository pictureRepository,
                              @Autowired PostRepository postRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.pictureRepository = pictureRepository;
        this.postRepository = postRepository;
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
        Picture picture = proposal.getPostImage().length != 0 ?
                pictureRepository.save(PictureMapper.mapToModelPost(proposal.getPostImage())): null;

        Comment comment = repository.save(CommentMapper.mapToModel(proposal,user,picture,post));
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
        Picture picture;

        // Check if Picture has changed
        if(!Arrays.equals(initialComment.getPicture().getImageData(), proposal.getPostImage())){
            pictureRepository.delete(initialComment.getPicture().getId());
            picture = pictureRepository.save(PictureMapper.mapToModelComment(proposal.getPostImage()));
        } else {
            picture = PictureMapper.mapToModelComment(initialComment.getPicture().getImageData());
        }
        Comment updatedComment = CommentMapper.mapToModel(proposal, initialComment, picture);
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
    public int increaseLikes(Long id) {
        Comment comment = get(id);
        int likes = comment.getLikes() + 1;
        Comment updatedComment = repository.save(CommentMapper.mapToModel(comment,likes));
        return updatedComment.getLikes();
    }

    @Override
    public int decreaseLikes(Long id) {
        Comment comment = get(id);
        int likes = comment.getLikes() != 0 ? comment.getLikes() - 1 : 0;
        Comment updatedComment = repository.save(CommentMapper.mapToModel(comment,likes));
        return updatedComment.getLikes();
    }
}
