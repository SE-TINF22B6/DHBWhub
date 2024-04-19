package de.tinf22b6.dhbwhub.repository.views;

import de.tinf22b6.dhbwhub.model.Post;
import de.tinf22b6.dhbwhub.model.views.View_fac_gesundheit_posts;
import de.tinf22b6.dhbwhub.repository.interfaces.SpringPostRepository;
import de.tinf22b6.dhbwhub.repository.interfaces_views.SpringViewFacGesundheitPostsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ViewFacGesundheitPostsRepository {
    private final SpringViewFacGesundheitPostsRepository repository;

    public ViewFacGesundheitPostsRepository(@Autowired SpringViewFacGesundheitPostsRepository repository) {
        this.repository = repository;
    }

    public List<View_fac_gesundheit_posts> findAll() {
        return repository.findAll();
    }

    public View_fac_gesundheit_posts find(Long id) {
        return repository.findById(id).orElse(null);
    }

}
