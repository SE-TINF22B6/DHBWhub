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
public class UpdateEventPostProposal {
    private String title;

    private String description;

    private LocationProposal location;

    private Date startdate;

    private Date enddate;

    private String[] tags;

    private String postImage;
}
