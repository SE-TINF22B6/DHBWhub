package de.tinf22b6.dhbwhub.mapper;

import de.tinf22b6.dhbwhub.model.Administrator;
import de.tinf22b6.dhbwhub.proposal.AdministratorProposal;

public class AdministratorMapper {
    public static Administrator mapToModel(AdministratorProposal proposal) {
        return new Administrator(
                AccountMapper.mapToModel(proposal.getAccount())
        );
    }
}
