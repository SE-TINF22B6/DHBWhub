package de.tinf22b6.dhbwhub.proposal.simplified_models;

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
public class EventThreadViewProposal {
    private Long id;

    private String title;

    private String description;

    private List<String> tags;

    private LocationProposal locationProposal;

    private int likes;

    private int commentAmount;

    private Date timestamp;

    private byte[] postImage;

    private Long accountId;

    private String username;

    private List<EventCommentThreadViewProposal> comments;
}
