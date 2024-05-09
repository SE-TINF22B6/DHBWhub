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
public class EventCommentThreadViewProposal {
    private Long eventId;

    private Long commentId;

    private Long accountId;

    private String username;

    private byte[] userImageData;

    private String description;

    private int likes;

    private Date timestamp;

    private byte[] commentImageData;
}
