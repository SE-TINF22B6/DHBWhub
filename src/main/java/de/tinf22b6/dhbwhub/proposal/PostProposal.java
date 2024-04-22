package de.tinf22b6.dhbwhub.proposal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostProposal {
    private String title;

    private String description;

    private Date timestamp;

    private int likes;

    private PictureProposal picture;

    private UserProposal user;

    private CourseProposal course;
}
