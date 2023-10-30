package de.tinf22b6.dhbwhub.service;

import de.tinf22b6.dhbwhub.exception.NoSuchEntryException;
import de.tinf22b6.dhbwhub.model.Account;
import de.tinf22b6.dhbwhub.model.Faculty;
import de.tinf22b6.dhbwhub.proposal.FacultyProposal;
import de.tinf22b6.dhbwhub.repository.FacultyRepository;
import de.tinf22b6.dhbwhub.service.interfaces.FacultyService;
import de.tinf22b6.dhbwhub.mapper.FacultyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacultyServiceImpl implements FacultyService {
    private final FacultyRepository repository;

    public FacultyServiceImpl(@Autowired FacultyRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Faculty> getAll() {
        return repository.findAll();
    }

    @Override
    public Faculty create(FacultyProposal proposal) {
        Faculty faculty = FacultyMapper.mapToModel(proposal);
        return repository.save(faculty);
    }

    @Override
    public Faculty get(Long id) {
        Faculty faculty = repository.find(id);
        if (faculty == null) {
            throw new NoSuchEntryException(Faculty.class.getSimpleName(), id);
        }
        return faculty;
    }

    @Override
    public Faculty update(Long id, FacultyProposal proposal) {
        // Check if faculty exists
        get(id);

        Faculty faculty = FacultyMapper.mapToModel(proposal);
        faculty.setFacId(id);
        return repository.save(faculty);
    }

    @Override
    public void delete(Long id) {
        // Check if faculty exists
        get(id);

        repository.delete(id);
    }
}
