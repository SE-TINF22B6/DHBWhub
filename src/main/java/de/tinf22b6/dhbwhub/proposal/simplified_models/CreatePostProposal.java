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
public class CreatePostProposal {

    private String title;

    private String description;

    private List<String> tags;

    private Date timestamp;

    private byte[] postImage;

    private Long accountId;

}
