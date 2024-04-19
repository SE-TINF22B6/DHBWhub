package de.tinf22b6.dhbwhub.proposal.simplifiedModels;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HomepagePostPreviewProposal {
    private Long id;

    private String title;

    private String description;

    private List<String> tags;

    private int amountLikes;

    private int amountComments;

    private int dateDifference;

    private Byte[] postImage;

    private String username;

}
