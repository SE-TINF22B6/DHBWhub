package de.tinf22b6.dhbwhub.proposal.simplifiedModels;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentThreadViewProposal {
    private Long postId;

    private Long commentId;

    private Long accountId;

    private String username;

    private byte[] userImageData;

    private String description;

    private int amountLikes;

    private int dateDifference;

    private byte[] commentImageData;
}
