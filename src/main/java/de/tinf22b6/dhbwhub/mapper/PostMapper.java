package de.tinf22b6.dhbwhub.mapper;

import de.tinf22b6.dhbwhub.model.Picture;
import de.tinf22b6.dhbwhub.model.Post;
import de.tinf22b6.dhbwhub.model.User;
import de.tinf22b6.dhbwhub.proposal.PostProposal;
import de.tinf22b6.dhbwhub.proposal.simplifiedModels.CreatePostProposal;
import de.tinf22b6.dhbwhub.proposal.simplifiedModels.HomepagePostPreviewProposal;
import de.tinf22b6.dhbwhub.proposal.simplifiedModels.PostThreadViewProposal;
import de.tinf22b6.dhbwhub.proposal.simplifiedModels.UpdatePostProposal;

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

    public static Post mapToModel(CreatePostProposal proposal, User user, Picture picture) {
        return new Post(
                proposal.getTitle(),
                proposal.getDescription(),
                proposal.getTimestamp(),
                0,
                picture,
                user,
                user.getCourse()
        );
    }

    public static Post mapToModel(UpdatePostProposal proposal, Post post, Picture picture) {
        return new Post(
                proposal.getTitle(),
                proposal.getDescription(),
                post.getTimestamp(),
                post.getLikes(),
                picture,
                post.getUser(),
                post.getUser().getCourse()
        );
    }

    public static Post mapToModel(Post post, int likes) {
        return new Post(
                post.getTitle(),
                post.getDescription(),
                post.getTimestamp(),
                likes,
                post.getPicture(),
                post.getUser(),
                post.getUser().getCourse()
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
