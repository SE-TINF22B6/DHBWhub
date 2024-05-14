package de.tinf22b6.dhbwhub.proposal.simplified_models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentThreadViewProposal {
    private Long postId;

    private Long commentId;

    private String text;

    private String authorUsername;

    private Long accountId;

    private byte[] authorImage;

    private Date timestamp;

    private int likeAmount;
}
