package de.tinf22b6.dhbwhub.repository.interfaces;

import de.tinf22b6.dhbwhub.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringCourseRepository extends JpaRepository<Course, Long> {}
