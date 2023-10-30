package de.tinf22b6.dhbwhub.service;

import de.tinf22b6.dhbwhub.model.Course;
import de.tinf22b6.dhbwhub.proposal.CourseProposal;
import de.tinf22b6.dhbwhub.repository.CourseRepository;
import de.tinf22b6.dhbwhub.service.interfaces.CourseService;
import de.tinf22b6.dhbwhub.mapper.mapper.CourseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    private final CourseRepository repository;

    public CourseServiceImpl(@Autowired CourseRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Course> getAll() {
        return repository.findAll();
    }

    @Override
    public Course create(CourseProposal proposal) {
        Course course = CourseMapper.mapToModel(proposal);
        return repository.save(course);
    }

    @Override
    public Course get(Long id) {
        Course course = repository.find(id);
        if (course == null) {
            throw new IllegalArgumentException(String.format("Course with id %d doesn't exists", id)); // TODO: Replace with custom exception
        }
        return course;
    }

    @Override
    public Course update(Long id, CourseProposal proposal) {
        // Check if course exists
        get(id);

        Course course = CourseMapper.mapToModel(proposal);
        course.setCourseId(id);
        return repository.save(course);
    }

    @Override
    public void delete(Long id) {
        // Check if course exists
        get(id);

        repository.delete(id);
    }
}
