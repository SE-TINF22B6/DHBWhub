package de.tinf22b6.dhbwhub.mapper;

import de.tinf22b6.dhbwhub.model.Account;
import de.tinf22b6.dhbwhub.proposal.AccountProposal;

public class AccountMapper {
    public static Account mapToModel(AccountProposal proposal) {
        return new Account(
                proposal.getUsername(),
                proposal.getEmail(),
                proposal.getPassword(),
                PictureMapper.mapToModel(proposal.getPicture())
        );
    }
}
