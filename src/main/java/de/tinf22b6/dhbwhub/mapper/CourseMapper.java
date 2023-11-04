package de.tinf22b6.dhbwhub.mapper;

import de.tinf22b6.dhbwhub.model.Course;
import de.tinf22b6.dhbwhub.proposal.CourseProposal;

public class CourseMapper {
    public static Course mapToModel(CourseProposal proposal) {
        return new Course(
                proposal.getName(),
                FacultyMapper.mapToModel(proposal.getFaculty())
        );
    }
}
