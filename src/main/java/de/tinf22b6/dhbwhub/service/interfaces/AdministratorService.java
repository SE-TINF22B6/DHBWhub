package de.tinf22b6.dhbwhub.service.interfaces;

import de.tinf22b6.dhbwhub.model.Administrator;
import de.tinf22b6.dhbwhub.proposal.AdministratorProposal;

import java.util.List;

public interface AdministratorService {
    List<Administrator> getAll();
    Administrator create(AdministratorProposal proposal);
    Administrator get(Long id);
    Administrator update(Long id, AdministratorProposal proposal);
    void delete(Long id);
}
