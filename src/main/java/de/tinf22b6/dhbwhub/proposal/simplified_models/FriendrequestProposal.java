
package de.tinf22b6.dhbwhub.proposal.simplified_models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FriendrequestProposal {

    private Long friendlistId;

    private Long accountId;

    private String username;

    private byte[] image;

    private String status;

}
