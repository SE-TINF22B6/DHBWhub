package de.tinf22b6.dhbwhub.mapper;

import de.tinf22b6.dhbwhub.model.Account;
import de.tinf22b6.dhbwhub.model.Picture;
import de.tinf22b6.dhbwhub.proposal.AccountProposal;

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
   public static Account mapToUpdatedUsernameProposal(Account account, String username) {
        return new Account(
                username,
                account.getEmail(),
                account.getPassword(),
                account.getPicture(),
                account.isActive()
        );
   }
   public static Account mapToUpdatedEmailProposal(Account account, String email) {
       return new Account(
               account.getUsername(),
               email,
               account.getPassword(),
               account.getPicture(),
               account.isActive()
       );
   }
   public static Account mapToUpdatedPasswordProposal(Account account, String password) {
       return new Account(
               account.getUsername(),
               account.getEmail(),
               password,
               account.getPicture(),
               account.isActive()
       );
   }

    public static Account mapToUpdatedPictureProposal(Account account, Picture picture) {
        return new Account(
                account.getUsername(),
                account.getEmail(),
                account.getPassword(),
                picture,
                account.isActive()
        );
    }
}
