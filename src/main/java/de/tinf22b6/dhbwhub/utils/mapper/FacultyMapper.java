package de.tinf22b6.dhbwhub.utils.mapper;

import de.tinf22b6.dhbwhub.model.Faculty;
import de.tinf22b6.dhbwhub.proposal.FacultyProposal;

public class FacultyMapper {
    public static Faculty mapToModel(FacultyProposal proposal) {
        return new Faculty(
                proposal.getName()
        );
    }
}
