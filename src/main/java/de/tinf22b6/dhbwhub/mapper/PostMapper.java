package de.tinf22b6.dhbwhub.mapper;

import de.tinf22b6.dhbwhub.model.Post;
import de.tinf22b6.dhbwhub.proposal.PostProposal;
import de.tinf22b6.dhbwhub.proposal.simplifiedModels.HomepagePostPreviewProposal;
import de.tinf22b6.dhbwhub.service.CommentServiceImpl;
import de.tinf22b6.dhbwhub.service.interfaces.CommentService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class PostMapper {
    public static Post mapToModel(PostProposal proposal) {
        return new Post(
                proposal.getTitle(),
                proposal.getDescription(),
                proposal.getTimestamp(),
                proposal.getLikes(),
                proposal.getTags() != null ? proposal.getTags() : null,
                proposal.getPicture() != null ? PictureMapper.mapToModel(proposal.getPicture()) : null,
                proposal.getUser() != null ? UserMapper.mapToModel(proposal.getUser()) : null,
                proposal.getCourse() != null ? CourseMapper.mapToModel(proposal.getCourse()) : null
        );
    }

    public static HomepagePostPreviewProposal mapToHomepagePreviewProposal(Post post) {
        return new HomepagePostPreviewProposal(
                post.getId(),
                post.getTitle(),
                post.getDescription().substring(0,201) + "...",
                post.getTags(),
                post.getLikes(),
                0,
                (int) TimeUnit.MILLISECONDS.toDays(new Date().getTime() - post.getTimestamp().getTime()),
                post.getPicture().getImageData(),
                post.getUser().getAccount().getUsername()
        );
    }
}
