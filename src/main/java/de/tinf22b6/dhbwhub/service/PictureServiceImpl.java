package de.tinf22b6.dhbwhub.service;

import de.tinf22b6.dhbwhub.exception.NoSuchEntryException;
import de.tinf22b6.dhbwhub.model.Account;
import de.tinf22b6.dhbwhub.model.Picture;
import de.tinf22b6.dhbwhub.proposal.PictureProposal;
import de.tinf22b6.dhbwhub.repository.PictureRepository;
import de.tinf22b6.dhbwhub.service.interfaces.PictureService;
import de.tinf22b6.dhbwhub.mapper.PictureMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PictureServiceImpl implements PictureService {
    private final PictureRepository repository;

    public PictureServiceImpl(@Autowired PictureRepository repository) {
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
            throw new NoSuchEntryException(Picture.class.getSimpleName(), id);
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
