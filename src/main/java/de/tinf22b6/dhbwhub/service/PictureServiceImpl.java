package de.tinf22b6.dhbwhub.service;

import de.tinf22b6.dhbwhub.exception.NoSuchEntryException;
import de.tinf22b6.dhbwhub.mapper.PictureMapper;
import de.tinf22b6.dhbwhub.model.Picture;
import de.tinf22b6.dhbwhub.proposal.PictureProposal;
import de.tinf22b6.dhbwhub.repository.PictureRepository;
import de.tinf22b6.dhbwhub.service.interfaces.PictureService;
import org.postgresql.shaded.com.ongres.scram.common.bouncycastle.base64.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.net.URL;
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
            throw new NoSuchEntryException(String.format("%s with ID %d does not exist", Picture.class.getSimpleName(), id));
        }
        return picture;
    }

    @Override
    public Picture findByUserId(Long id) {
        Picture picture = repository.findByUserId(id);
        if (picture == null) {
            throw new NoSuchEntryException(String.format("%s for user with ID %d does not exist", Picture.class.getSimpleName(), id));
        }
        return picture;
    }

    @Override
    public Picture update(Long id, PictureProposal proposal) {
        // Check if picture exists
        get(id);

        Picture picture = PictureMapper.mapToModel(proposal);
        picture.setId(id);
        return repository.save(picture);
    }

    public Picture getImageFromUrl(String imageUrl) {
        String imageBytes = null;
        try {
            URL url = new URL(imageUrl);
            BufferedImage image = ImageIO.read(url);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(image, "png", baos);
            imageBytes = "data:image/png;base64," + Base64.toBase64String(baos.toByteArray());
        }catch (Exception e) {
            System.out.println("Something went wrong during the image retrieval");
        }
        if (imageBytes == null) {
            return null;
        }
        return PictureMapper.mapToModelUser(imageBytes);
    }

    @Override
    public void delete(Long id) {
        // Check if picture exists
        get(id);

        repository.delete(id);
    }
}
