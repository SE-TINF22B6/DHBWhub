package de.tinf22b6.dhbwhub.service.interfaces;

import de.tinf22b6.dhbwhub.model.Picture;
import de.tinf22b6.dhbwhub.proposal.PictureProposal;

import java.util.List;

public interface PictureService {
    List<Picture> getAll();
    Picture create(PictureProposal proposal);
    Picture get(Long id);
    Picture findByUserId(Long id);
    Picture update(Long id, PictureProposal proposal);
    void delete(Long id);
}
