package de.tinf22b6.dhbwhub.mapper.views;

import de.tinf22b6.dhbwhub.model.Post;
import de.tinf22b6.dhbwhub.model.views.View_fac_gesundheit_posts;
import de.tinf22b6.dhbwhub.model.views.View_fac_technik_posts;
import de.tinf22b6.dhbwhub.model.views.View_fac_wirtschaft_posts;
import de.tinf22b6.dhbwhub.model.views.View_homepage_posts;

public class ViewPostMapper {

    public static Post mapToModel(View_fac_wirtschaft_posts proposal) {
        return new Post(
                proposal.getId(),
                proposal.getTitle(),
                proposal.getDescription(),
                proposal.getTimestamp(),
                proposal.getLikes(),
                proposal.getTags(),
                proposal.getPicture(),
                proposal.getUser(),
                proposal.getCourse()
        );
    }

    public static Post mapToModel(View_fac_gesundheit_posts proposal) {
        return new Post(
                proposal.getId(),
                proposal.getTitle(),
                proposal.getDescription(),
                proposal.getTimestamp(),
                proposal.getLikes(),
                proposal.getTags(),
                proposal.getPicture(),
                proposal.getUser(),
                proposal.getCourse()
        );
    }

    public static Post mapToModel(View_fac_technik_posts proposal) {
        return new Post(
                proposal.getId(),
                proposal.getTitle(),
                proposal.getDescription(),
                proposal.getTimestamp(),
                proposal.getLikes(),
                proposal.getTags(),
                proposal.getPicture(),
                proposal.getUser(),
                proposal.getCourse()
        );
    }

    public static Post mapToModel(View_homepage_posts proposal) {
        return new Post(
                proposal.getId(),
                proposal.getTitle(),
                proposal.getDescription(),
                proposal.getTimestamp(),
                proposal.getLikes(),
                proposal.getTags(),
                proposal.getPicture(),
                proposal.getUser(),
                proposal.getCourse()
        );
    }
}
