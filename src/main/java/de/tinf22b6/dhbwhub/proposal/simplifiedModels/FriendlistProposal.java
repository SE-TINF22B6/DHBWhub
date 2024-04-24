
package de.tinf22b6.dhbwhub.proposal.simplifiedModels;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FriendlistProposal {

    private Long friendlistId;

    private Long accountId;

    private String username;

    private byte[] image;

}
