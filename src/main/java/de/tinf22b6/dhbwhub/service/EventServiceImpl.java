package de.tinf22b6.dhbwhub.service;

import de.tinf22b6.dhbwhub.exception.NoSuchEntryException;
import de.tinf22b6.dhbwhub.mapper.*;
import de.tinf22b6.dhbwhub.model.*;
import de.tinf22b6.dhbwhub.proposal.simplified_models.*;
import de.tinf22b6.dhbwhub.repository.EventRepository;
import de.tinf22b6.dhbwhub.repository.PictureRepository;
import de.tinf22b6.dhbwhub.repository.UserRepository;
import de.tinf22b6.dhbwhub.service.interfaces.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class EventServiceImpl implements EventService {
    private final EventRepository repository;
    private final UserRepository userRepository;
    private final PictureRepository pictureRepository;
    private final EventRepository eventRepository;

    public EventServiceImpl(@Autowired EventRepository repository,
                            @Autowired UserRepository userRepository,
                            @Autowired PictureRepository pictureRepository, EventRepository eventRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.pictureRepository = pictureRepository;
        this.eventRepository = eventRepository;
    }

    @Override
    public EventPost getEventPost(Long id) {
        EventPost eventPost = repository.findEventPost(id);
        if (eventPost == null) {
            throw new NoSuchEntryException(String.format("%s with ID %d does not exist", EventPost.class.getSimpleName(), id));
        }
        return eventPost;
    }

    @Override
    public EventComment getEventComment(Long id) {
        EventComment eventComment = repository.findEventComment(id);
        if (eventComment == null) {
            throw new NoSuchEntryException(String.format("%s with ID %d does not exist", EventComment.class.getSimpleName(), id));
        }
        return eventComment;
    }

    @Override
    public EventTag getEventTag(Long id) {
        EventTag eventTag = repository.findEventTag(id);
        if (eventTag == null) {
            throw new NoSuchEntryException(String.format("%s with ID %d does not exist", EventTag.class.getSimpleName(), id));
        }
        return eventTag;
    }

    @Override
    public List<HomepageEventPreviewProposal> getHomepageEvents() {
        List<HomepageEventPreviewProposal> homepageEventPreviewProposals = repository.findAllEventPosts().stream().map(EventMapper::mapToHomepagePreviewProposal).toList();
        homepageEventPreviewProposals.forEach(p -> p.setTags(getEventTags(p.getId())));
        return homepageEventPreviewProposals;
    }

    @Override
    public EventThreadViewProposal create(CreateEventPostProposal proposal) {
        // Creating the Post itself
        User user = userRepository.findByAccountId(proposal.getAccountId());
        Picture picture = proposal.getPostImage().length != 0 ?
                pictureRepository.save(PictureMapper.mapToModelPost(proposal.getPostImage())): null;

        EventPost post = repository.save(EventMapper.mapToModel(proposal,user,picture));

        // Creating Tags after the Post is created
        Arrays.stream(proposal.getTags()).forEach(t -> {
            EventTag eventTag = new EventTag(post, t);
            repository.save(eventTag);
        } );

        return getEventThreadView(post.getId());
    }

    @Override
    public EventThreadViewProposal getEventThreadView(Long id) {
        EventThreadViewProposal eventThreadViewProposal = EventMapper.mapToPostThreadViewProposal(getEventPost(id));

        eventThreadViewProposal.setTags(getEventTags(id));
        eventThreadViewProposal.setCommentAmount(getAmountOfComments(id));
        eventThreadViewProposal.setComments(getEventComments(id));

        return eventThreadViewProposal;
    }

    @Override
    public int getAmountOfComments(Long id) {
        return repository.getAmountOfComments(id);
    }

    @Override
    public EventThreadViewProposal update(Long id, UpdateEventPostProposal proposal) {
        EventPost initialPost = getEventPost(id);
        Picture picture;

        byte[] proposalImageData = proposal.getPostImage() != null? proposal.getPostImage() : new byte[0];
        byte[] initialImageData = initialPost.getPicture() != null? initialPost.getPicture().getImageData() : new byte[0];
        // Check if Picture has changed
        if (proposalImageData.length == 0 && initialImageData.length != 0) {
            pictureRepository.delete(initialPost.getPicture().getId());
            picture = null;
        }
        else if (!Arrays.equals(initialImageData, proposalImageData)) {
            pictureRepository.delete(initialPost.getPicture().getId());
            picture = pictureRepository.save(PictureMapper.mapToModelPost(proposalImageData));
        } else {
            picture = initialPost.getPicture();
        }

        // Update post
        EventPost updatedPost = EventMapper.mapToModel(proposal, initialPost, picture);
        updatedPost.setId(id);

        EventPost post = repository.save(updatedPost);

        /* Check if Tags changed
            formerTags = Tags in the database
            proposedTags = Tags proposed
        * */
        List<EventTag> formerTags = eventRepository.findTagsByPostId(id);
        List<String> proposedTags = new ArrayList<>(Arrays.stream(proposal.getTags()).toList());

        for (EventTag eventTag : formerTags) {
            if (proposedTags.contains(eventTag.getTag())) {
                proposedTags.remove(eventTag.getTag());
            } else {
                eventRepository.deleteEventTag(eventTag.getId());
            }
        }
        proposedTags.forEach(t -> {
            if( t != null) repository.save(EventTagMapper.mapToModel(post, t));
        });

        return getEventThreadView(updatedPost.getId());
    }

    @Override
    public void deletePost(Long id) {
        getEventPost(id);
        repository.deleteEventPost(id);
    }

    @Override
    public void deleteComment(Long id) {
        getEventComment(id);
        repository.deleteEventComment(id);
    }

    @Override
    public void deleteTag(Long id) {
        getEventTag(id);
        repository.deleteEventTag(id);
    }


    @Override
    public int increaseLikes(Long id, int mode) {
        int likes;
        // mode 0 - Post; mode 1 - Comment
        if (mode == 0){
            EventPost eventPost = getEventPost(id);
            likes = eventPost.getLikes() + 1;
            EventPost updatedPost = EventMapper.mapToModel(eventPost,likes);
            updatedPost.setId(id);
            likes = repository.save(updatedPost).getLikes();
        } else {
            EventComment eventComment = getEventComment(id);
            likes = eventComment.getLikes() + 1;
            EventComment updatedComment = EventMapper.mapToModel(eventComment, likes);
            updatedComment.setId(id);
            likes = repository.save(updatedComment).getLikes();
        }
        return likes;
    }

    @Override
    public int decreaseLikes(Long id, int mode) {
        int likes;
        // mode 0 - Post; mode 1 - Comment
        if (mode == 0){
            EventPost eventPost = getEventPost(id);
            likes = eventPost.getLikes() - 1;
            EventPost updatedPost = EventMapper.mapToModel(eventPost,likes);
            updatedPost.setId(id);
            likes = repository.save(updatedPost).getLikes();
        } else {
            EventComment eventComment = getEventComment(id);
            likes = eventComment.getLikes() - 1;
            EventComment updatedComment = EventMapper.mapToModel(eventComment, likes);
            updatedComment.setId(id);
            likes = repository.save(updatedComment).getLikes();
        }
        return likes;
    }

    @Override
    public List<EventCommentThreadViewProposal> getEventComments(Long id) {
        return repository.getEventComments(id);
    }

    @Override
    public List<String> getEventTags(Long id) {
        return repository.getEventTags(id);
    }
}
