package de.tinf22b6.dhbwhub.repository.views;

import de.tinf22b6.dhbwhub.model.views.View_fac_wirtschaft_posts;
import de.tinf22b6.dhbwhub.model.views.View_homepage_posts;
import de.tinf22b6.dhbwhub.repository.interfaces_views.SpringViewFacWirtschaftPostsRepository;
import de.tinf22b6.dhbwhub.repository.interfaces_views.SpringViewHomepagePostsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ViewHomepagePostsRepository {
    private final SpringViewHomepagePostsRepository repository;

    public ViewHomepagePostsRepository(@Autowired SpringViewHomepagePostsRepository repository) {
        this.repository = repository;
    }

    public List<View_homepage_posts> findAll() {
        return repository.findAll();
    }

    public View_homepage_posts find(Long id) {
        return repository.findById(id).orElse(null);
    }

}
