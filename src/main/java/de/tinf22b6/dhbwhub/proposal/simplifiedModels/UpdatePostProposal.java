package de.tinf22b6.dhbwhub.proposal.simplifiedModels;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePostProposal {

    private String title;

    private String description;

    private List<String> tags;

    private byte[] postImage;

}
