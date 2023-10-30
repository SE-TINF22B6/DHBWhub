package de.tinf22b6.dhbwhub.service;

import de.tinf22b6.dhbwhub.model.Picture;
import de.tinf22b6.dhbwhub.proposal.PictureProposal;
import de.tinf22b6.dhbwhub.repository.PictureRepository;
import de.tinf22b6.dhbwhub.service.interfaces.PictureService;
import de.tinf22b6.dhbwhub.utils.mapper.PictureMapper;

import java.util.List;

public class PictureServiceImpl implements PictureService {
    private final PictureRepository repository;

    public PictureServiceImpl(PictureRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Picture> getAll() {
        return repository.findAll();
    }

    @Override
    public Picture create(PictureProposal proposal) {
        Picture picture = PictureMapper.mapToModel(proposal);
        return repository.save(picture);
    }

    @Override
    public Picture get(Long id) {
        Picture picture = repository.find(id);
        if (picture == null) {
            throw new IllegalArgumentException(String.format("Picture with id %d doesn't exists", id)); // TODO: Replace with custom exception
        }
        return picture;
    }

    @Override
    public Picture update(Long id, PictureProposal proposal) {
        // Check if picture exists
        get(id);

        Picture picture = PictureMapper.mapToModel(proposal);
        picture.setPictureId(id);
        return repository.save(picture);
    }

    @Override
    public void delete(Long id) {
        // Check if picture exists
        get(id);

        repository.delete(id);
    }
}
