package de.tinf22b6.dhbwhub.service;

import de.tinf22b6.dhbwhub.exception.NoSuchEntryException;
import de.tinf22b6.dhbwhub.mapper.AdministratorMapper;
import de.tinf22b6.dhbwhub.model.Administrator;
import de.tinf22b6.dhbwhub.proposal.AdministratorProposal;
import de.tinf22b6.dhbwhub.repository.AdministratorRepository;
import de.tinf22b6.dhbwhub.service.interfaces.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdministratorServiceImpl implements AdministratorService {
    private final AdministratorRepository repository;

    public AdministratorServiceImpl(@Autowired AdministratorRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Administrator> getAll() {
        return repository.findAll();
    }

    @Override
    public Administrator create(AdministratorProposal proposal) {
        Administrator administrator = AdministratorMapper.mapToModel(proposal);
        return repository.save(administrator);
    }

    @Override
    public Administrator get(Long id) {
        Administrator administrator = repository.find(id);
        if (administrator == null) {
            throw new NoSuchEntryException(String.format("%s with ID %d does not exist", Administrator.class.getSimpleName(), id));
        }
        return administrator;
    }

    @Override
    public Administrator update(Long id, AdministratorProposal proposal) {
        // Check if administrator exists
        get(id);

        Administrator administrator = AdministratorMapper.mapToModel(proposal);
        administrator.setId(id);
        return repository.save(administrator);
    }

    @Override
    public void delete(Long id) {
        // Check if administrator exists
        get(id);

        repository.delete(id);
    }
}
