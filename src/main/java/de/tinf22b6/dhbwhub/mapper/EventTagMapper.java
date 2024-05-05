package de.tinf22b6.dhbwhub.mapper;

import de.tinf22b6.dhbwhub.model.*;

public class EventTagMapper {

    public static EventTag mapToModel(EventPost post, String tag) {
        return new EventTag(post, tag);
    }

}
