package de.tinf22b6.dhbwhub.repository.interfaces_views;

import de.tinf22b6.dhbwhub.model.views.View_fac_technik_posts;
import de.tinf22b6.dhbwhub.model.views.View_homepage_posts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringViewHomepagePostsRepository extends JpaRepository<View_homepage_posts, Long> {
}
