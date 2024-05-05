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
public class CreateEventCommentProposal {
    private Long eventId;

    private Long accountId;

    private String description;

    private Date timestamp;

    private byte[] postImage;
}
