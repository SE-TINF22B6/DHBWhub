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

    public static Picture mapToPicture(String image){
        return new Picture(
                "picture.png",
                image.getBytes()
        );
    }

    public static Picture mapToPicture(byte[] imageData){
        return new Picture(
                "profile.png",
                imageData
        );
    }
}
