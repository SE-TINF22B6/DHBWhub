package de.tinf22b6.dhbwhub.repository.views;

import de.tinf22b6.dhbwhub.model.views.View_comment_amount;
import de.tinf22b6.dhbwhub.model.views.View_fac_wirtschaft_posts;
import de.tinf22b6.dhbwhub.repository.interfaces_views.SpringViewCommentAmountRepository;
import de.tinf22b6.dhbwhub.repository.interfaces_views.SpringViewFacWirtschaftPostsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ViewCommentAmountRepository {
    private final SpringViewCommentAmountRepository repository;

    public ViewCommentAmountRepository(@Autowired SpringViewCommentAmountRepository repository) {
        this.repository = repository;
    }

    public List<View_comment_amount> findAll() {
        return repository.findAll();
    }

    public View_comment_amount find(Long id) {
        return repository.findById(id).orElse(null);
    }

}
