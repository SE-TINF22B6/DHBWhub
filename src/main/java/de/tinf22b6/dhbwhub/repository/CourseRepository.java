package de.tinf22b6.dhbwhub.repository;

import de.tinf22b6.dhbwhub.model.Course;
import de.tinf22b6.dhbwhub.repository.interfaces.SpringCourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CourseRepository {
    private final SpringCourseRepository repository;

    public CourseRepository(@Autowired SpringCourseRepository repository) {
        this.repository = repository;
    }

    public List<Course> findAll() {
        return repository.findAll();
    }

    public Course find(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Course save(Course course) {
        return repository.save(course);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Course findByName(String course) {
        return repository.findByName(course);
    }
}
