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
public class PostThreadViewProposal {
    private Long id;

    private String title;

    private String description;

    private List<String> tags;

    private int amountLikes;

    private int commentAmount;

    private Date timestamp;

    private byte[] postImage;

    private Long accountId;

    private String username;

    private List<CommentThreadViewProposal> comments;
}
