package de.tinf22b6.dhbwhub.controller;

import de.tinf22b6.dhbwhub.model.Administrator;
import de.tinf22b6.dhbwhub.proposal.AdministratorProposal;
import de.tinf22b6.dhbwhub.service.interfaces.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"https://www.dhbwhub.de", "http://localhost:3000"})
@RequestMapping(value = "/administrator")
public class AdministratorController {
    private final AdministratorService service;

    public AdministratorController(@Autowired AdministratorService service) {
        this.service = service;
    }

    @GetMapping
    public List<Administrator> getAll() {
        return service.getAll();
    }

    @PostMapping
    public Administrator create(@RequestBody AdministratorProposal proposal) {
        return service.create(proposal);
    }

    @GetMapping("/{id}")
    public Administrator get(@PathVariable Long id) {
        return service.get(id);
    }

    @PutMapping("/{id}")
    public Administrator update(@PathVariable Long id, @RequestBody AdministratorProposal proposal) {
        return service.update(id, proposal);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
