package de.tinf22b6.dhbwhub.mapper;

import de.tinf22b6.dhbwhub.model.Post;
import de.tinf22b6.dhbwhub.model.PostTag;

public class PostTagMapper {

    public static PostTag mapToModel(Post post, String tag) {
        return new PostTag(post, tag);
    }

}
