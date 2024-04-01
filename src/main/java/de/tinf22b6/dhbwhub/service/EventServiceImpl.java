package de.tinf22b6.dhbwhub.service;

import de.tinf22b6.dhbwhub.exception.NoSuchEntryException;
import de.tinf22b6.dhbwhub.mapper.AccountMapper;
import de.tinf22b6.dhbwhub.mapper.EventMapper;
import de.tinf22b6.dhbwhub.model.Account;
import de.tinf22b6.dhbwhub.model.Event;
import de.tinf22b6.dhbwhub.proposal.AccountProposal;
import de.tinf22b6.dhbwhub.proposal.EventProposal;
import de.tinf22b6.dhbwhub.repository.AccountRepository;
import de.tinf22b6.dhbwhub.repository.EventRepository;
import de.tinf22b6.dhbwhub.service.interfaces.AccountService;
import de.tinf22b6.dhbwhub.service.interfaces.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventServiceImpl implements EventService {
    private final EventRepository repository;

    public EventServiceImpl(@Autowired EventRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Event> getAll() {
        return repository.findAll();
    }

    @Override
    public Event create(EventProposal proposal) {
        Event event = EventMapper.mapToModel(proposal);
        return repository.save(event);
    }

    @Override
    public Event get(Long id) {
        Event event = repository.find(id);
        if (event == null) {
            throw new NoSuchEntryException(String.format("%s with ID %d does not exist", Account.class.getSimpleName(), id));
        }
        return event;
    }

    @Override
    public Event update(Long id, EventProposal proposal) {
        // Check if account exists
        get(id);

        Event event = EventMapper.mapToModel(proposal);
        event.setId(id);
        return repository.save(event);
    }

    @Override
    public void delete(Long id) {
        // Check if account exists
        get(id);

        repository.delete(id);
    }
}
