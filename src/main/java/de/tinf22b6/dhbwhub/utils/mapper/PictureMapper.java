package de.tinf22b6.dhbwhub.utils.mapper;

import de.tinf22b6.dhbwhub.model.Picture;
import de.tinf22b6.dhbwhub.proposal.PictureProposal;

public class PictureMapper {
    public static Picture mapToModel(PictureProposal proposal) {
        return new Picture(
                proposal.getName(),
                proposal.getImageData()
        );
    }
}
