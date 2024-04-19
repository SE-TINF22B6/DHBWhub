package de.tinf22b6.dhbwhub.repository.interfaces_views;

import de.tinf22b6.dhbwhub.model.views.View_comment_amount;
import de.tinf22b6.dhbwhub.model.views.View_homepage_posts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringViewCommentAmountRepository extends JpaRepository<View_comment_amount, Long> {
}
