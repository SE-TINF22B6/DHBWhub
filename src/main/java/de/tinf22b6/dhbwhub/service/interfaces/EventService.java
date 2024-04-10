package de.tinf22b6.dhbwhub.service.interfaces;

import de.tinf22b6.dhbwhub.model.Event;
import de.tinf22b6.dhbwhub.proposal.EventProposal;

import java.util.List;

public interface EventService {
    List<Event> getAll();
    Event create(EventProposal proposal);
    Event get(Long id);
    Event update(Long id, EventProposal proposal);
    void delete(Long id);
}
