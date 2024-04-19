package de.tinf22b6.dhbwhub.repository.interfaces_views;

import de.tinf22b6.dhbwhub.model.views.View_fac_technik_posts;
import de.tinf22b6.dhbwhub.model.views.View_fac_wirtschaft_posts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringViewFacWirtschaftPostsRepository extends JpaRepository<View_fac_wirtschaft_posts, Long> {
}
