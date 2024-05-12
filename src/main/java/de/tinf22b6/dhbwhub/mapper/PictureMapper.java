package de.tinf22b6.dhbwhub.mapper;

import de.tinf22b6.dhbwhub.model.Picture;
import de.tinf22b6.dhbwhub.proposal.PictureProposal;

public class PictureMapper {
    public static Picture mapToModel(PictureProposal proposal) {
        return new Picture(
                proposal.getName(),
                proposal.getImageData()
        );
    }
    public static Picture mapToModelPost(byte[] imageData) {
        return new Picture(
                "post.png",
                imageData
        );
    }
    public static Picture mapToModelComment(byte[] imageData) {
        return new Picture(
                "comment.png",
                imageData
        );
    }

    public static Picture mapToModelEventPost(byte[] imageData) {
        return new Picture(
                "event_post.png",
                imageData
        );
    }
    public static Picture mapToModelEventComment(byte[] imageData) {
        return new Picture(
                "event_comment.png",
                imageData
        );
    }

}
