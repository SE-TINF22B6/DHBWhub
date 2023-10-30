package de.tinf22b6.dhbwhub.service.interfaces;

import de.tinf22b6.dhbwhub.model.Faculty;
import de.tinf22b6.dhbwhub.proposal.FacultyProposal;

import java.util.List;

public interface FacultyService {
    List<Faculty> getAll();
    Faculty create(FacultyProposal proposal);
    Faculty get(Long id);
    Faculty update(Long id, FacultyProposal proposal);
    void delete(Long id);
}
