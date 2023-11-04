package de.tinf22b6.dhbwhub.proposal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountProposal {
    private String username;

    private String email;

    private String password;

    private PictureProposal picture;
}
