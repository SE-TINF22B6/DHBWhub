package de.tinf22b6.dhbwhub.mapper;

import de.tinf22b6.dhbwhub.model.Account;
import de.tinf22b6.dhbwhub.proposal.AccountProposal;
import de.tinf22b6.dhbwhub.security.OAuth.CustomOAuth2User;

public class AccountMapper {
    public static Account mapToModel(AccountProposal proposal) {
        return new Account(
                proposal.getUsername(),
                proposal.getEmail(),
                proposal.getPassword(),
                proposal.getPicture() != null ? PictureMapper.mapToModel(proposal.getPicture()) : null,
                proposal.isActive()
        );
    }

    public static Account mapToModel(CustomOAuth2User customOAuth2User) {
        return new Account(
                customOAuth2User.getName(),
                customOAuth2User.getEmail(),
                "testPassword",               //TODO: Find solution for password
                PictureMapper.mapToPicture(customOAuth2User.getPicture()),
                true
        );
    }
}
