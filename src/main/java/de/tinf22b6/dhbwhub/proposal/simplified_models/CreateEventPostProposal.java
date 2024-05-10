package de.tinf22b6.dhbwhub.proposal.simplified_models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.stream.Location;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateEventPostProposal {
    private String title;

    private String description;

    private LocationProposal location;

    private Date timestamp;

    private Date startdate;

    private Date enddate;

    private String[] tags;

    private byte[] postImage;

    private Long accountId;
}
