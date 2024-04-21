package de.tinf22b6.dhbwhub.mapper;

import de.tinf22b6.dhbwhub.model.Post;
import de.tinf22b6.dhbwhub.proposal.PostProposal;
import de.tinf22b6.dhbwhub.proposal.simplifiedModels.HomepagePostPreviewProposal;
import de.tinf22b6.dhbwhub.proposal.simplifiedModels.PostThreadViewProposal;
import de.tinf22b6.dhbwhub.service.CommentServiceImpl;
import de.tinf22b6.dhbwhub.service.interfaces.CommentService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class PostMapper {
    public static Post mapToModel(PostProposal proposal) {
        return new Post(
                proposal.getTitle(),
                proposal.getDescription(),
                proposal.getTimestamp(),
                proposal.getLikes(),
                proposal.getTags() != null ? proposal.getTags().toArray(String[]::new) : null,
                proposal.getPicture() != null ? PictureMapper.mapToModel(proposal.getPicture()) : null,
                proposal.getUser() != null ? UserMapper.mapToModel(proposal.getUser()) : null,
                proposal.getCourse() != null ? CourseMapper.mapToModel(proposal.getCourse()) : null
        );
    }

    public static HomepagePostPreviewProposal mapToHomepagePreviewProposal(Post post) {
        return new HomepagePostPreviewProposal(
                post.getId(),
                post.getTitle(),
                (post.getDescription().length() > 200) ? post.getDescription().substring(0,201) + "..." : post.getDescription(),
                List.of(post.getTags()),
                post.getLikes(),
                0,
                (int) TimeUnit.MILLISECONDS.toDays(new Date().getTime() - post.getTimestamp().getTime()),
                post.getPicture() != null ? post.getPicture().getImageData() : null,
                post.getUser() != null ? post.getUser().getAccount().getId() : null,
                post.getUser() != null ? post.getUser().getAccount().getUsername(): null
        );
    }

    public static PostThreadViewProposal mapToPostThreadViewProposal(Post post) {
        return new PostThreadViewProposal(
                post.getId(),
                post.getTitle(),
                post.getDescription(),
                List.of(post.getTags()),
                post.getLikes(),
                0,
                (int) TimeUnit.MILLISECONDS.toDays(new Date().getTime() - post.getTimestamp().getTime()),
                post.getPicture() != null ? post.getPicture().getImageData() : null,
                post.getUser() != null ? post.getUser().getAccount().getId() : null,
                post.getUser() != null ? post.getUser().getAccount().getUsername(): null,
                null
        );
    }
}
