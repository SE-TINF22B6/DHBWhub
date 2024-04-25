package de.tinf22b6.dhbwhub.mapper;

import de.tinf22b6.dhbwhub.model.Post;
import de.tinf22b6.dhbwhub.proposal.PostProposal;
import de.tinf22b6.dhbwhub.proposal.simplifiedModels.HomepagePostPreviewProposal;
import de.tinf22b6.dhbwhub.proposal.simplifiedModels.PostThreadViewProposal;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class PostMapper {
    public static Post mapToModel(PostProposal proposal) {
        return new Post(
                proposal.getTitle(),
                proposal.getDescription(),
                proposal.getTimestamp(),
                proposal.getLikes(),
                proposal.getPicture() != null ? PictureMapper.mapToModel(proposal.getPicture()) : null,
                proposal.getUser() != null ? UserMapper.mapToModel(proposal.getUser()) : null,
                proposal.getCourse() != null ? CourseMapper.mapToModel(proposal.getCourse()) : null
        );
    }

    public static HomepagePostPreviewProposal mapToHomepagePreviewProposal(Post post) {
        return new HomepagePostPreviewProposal(
                post.getId(),
                post.getTitle(),
                post.getDescription(),
                null,
                post.getLikes(),
                0,
                post.getTimestamp(),
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
                null,
                post.getLikes(),
                0,
                post.getTimestamp(),
                post.getPicture() != null ? post.getPicture().getImageData() : null,
                post.getUser() != null ? post.getUser().getAccount().getId() : null,
                post.getUser() != null ? post.getUser().getAccount().getUsername(): null,
                null
        );
    }
}
