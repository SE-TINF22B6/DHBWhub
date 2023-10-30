package de.tinf22b6.dhbwhub.service;

import de.tinf22b6.dhbwhub.model.Administrator;
import de.tinf22b6.dhbwhub.proposal.AdministratorProposal;
import de.tinf22b6.dhbwhub.repository.AdministratorRepository;
import de.tinf22b6.dhbwhub.service.interfaces.AdministratorService;
import de.tinf22b6.dhbwhub.utils.mapper.AdministratorMapper;
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
            throw new IllegalArgumentException(String.format("Administrator with id %d doesn't exists", id)); // TODO: Replace with custom exception
        }
        return administrator;
    }

    @Override
    public Administrator update(Long id, AdministratorProposal proposal) {
        // Check if administrator exists
        get(id);

        Administrator administrator = AdministratorMapper.mapToModel(proposal);
        administrator.setAdminId(id);
        return repository.save(administrator);
    }

    @Override
    public void delete(Long id) {
        // Check if administrator exists
        get(id);

        repository.delete(id);
    }
}
