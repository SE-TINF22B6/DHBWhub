package de.tinf22b6.dhbwhub.proposal.simplified_models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HomepagePostPreviewProposal {
    private Long id;

    private String title;

    private String description;

    private List<String> tags;

    private int likeAmount;

    private int commentAmount;

    private Date timestamp;

    private byte[] postImage;

    private Long accountId;

    private String username;
}
