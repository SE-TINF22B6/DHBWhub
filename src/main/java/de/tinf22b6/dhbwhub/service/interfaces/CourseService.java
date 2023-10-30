package de.tinf22b6.dhbwhub.service.interfaces;

import de.tinf22b6.dhbwhub.model.Course;
import de.tinf22b6.dhbwhub.proposal.CourseProposal;

import java.util.List;

public interface CourseService {
    List<Course> getAll();
    Course create(CourseProposal proposal);
    Course get(Long id);
    Course update(Long id, CourseProposal proposal);
    void delete(Long id);
}
