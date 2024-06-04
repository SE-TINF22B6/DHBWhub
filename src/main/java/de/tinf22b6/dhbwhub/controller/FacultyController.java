package de.tinf22b6.dhbwhub.controller;

import de.tinf22b6.dhbwhub.model.Faculty;
import de.tinf22b6.dhbwhub.proposal.FacultyProposal;
import de.tinf22b6.dhbwhub.service.interfaces.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/faculty")
public class FacultyController {
    private final FacultyService service;

    public FacultyController(@Autowired FacultyService service) {
        this.service = service;
    }

    @GetMapping
    public List<Faculty> getAll() {
        return service.getAll();
    }

    @PostMapping
    public Faculty create(@RequestBody FacultyProposal proposal) {
        return service.create(proposal);
    }

    @GetMapping("/{id}")
    public Faculty get(@PathVariable Long id) {
        return service.get(id);
    }

    @PutMapping("/{id}")
    public Faculty update(@PathVariable Long id, @RequestBody FacultyProposal proposal) {
        return service.update(id, proposal);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
