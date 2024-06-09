package de.tinf22b6.dhbwhub.proposal.simplified_models;

import de.tinf22b6.dhbwhub.model.Picture;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserInformationProposal {
    private Long userId;

    private String username;

    private Picture picture;

    private int amountFollower;

    private int age;

    private String description;

    private String course;
}
