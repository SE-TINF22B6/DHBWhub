package de.tinf22b6.dhbwhub.controller;

import de.tinf22b6.dhbwhub.model.Course;
import de.tinf22b6.dhbwhub.proposal.CourseProposal;
import de.tinf22b6.dhbwhub.service.interfaces.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"https://www.dhbwhub.de", "http://localhost:3000"})
@RequestMapping(value = "/course")
public class CourseController {
    private final CourseService service;

    public CourseController(@Autowired CourseService service) {
        this.service = service;
    }

    @GetMapping
    public List<Course> getAll() {
        return service.getAll();
    }

    @PostMapping
    public Course create(@RequestBody CourseProposal proposal) {
        return service.create(proposal);
    }

    @GetMapping("/{id}")
    public Course get(@PathVariable Long id) {
        return service.get(id);
    }

    @PutMapping("/{id}")
    public Course update(@PathVariable Long id, @RequestBody CourseProposal proposal) {
        return service.update(id, proposal);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

}