package de.tinf22b6.dhbwhub.mapper;

import de.tinf22b6.dhbwhub.model.Event;
import de.tinf22b6.dhbwhub.proposal.EventProposal;

public class EventMapper {
    public static Event mapToModel(EventProposal proposal) {
        return new Event(
                proposal.getName(),
                proposal.getDate()
        );
    }
}
