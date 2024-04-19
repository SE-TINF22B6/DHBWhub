package de.tinf22b6.dhbwhub.repository.views;

import de.tinf22b6.dhbwhub.model.views.View_fac_gesundheit_posts;
import de.tinf22b6.dhbwhub.model.views.View_fac_technik_posts;
import de.tinf22b6.dhbwhub.repository.interfaces_views.SpringViewFacGesundheitPostsRepository;
import de.tinf22b6.dhbwhub.repository.interfaces_views.SpringViewFacTechnikPostsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ViewFacTechnikPostsRepository {
    private final SpringViewFacTechnikPostsRepository repository;

    public ViewFacTechnikPostsRepository(@Autowired SpringViewFacTechnikPostsRepository repository) {
        this.repository = repository;
    }

    public List<View_fac_technik_posts> findAll() {
        return repository.findAll();
    }

    public View_fac_technik_posts find(Long id) {
        return repository.findById(id).orElse(null);
    }

}
