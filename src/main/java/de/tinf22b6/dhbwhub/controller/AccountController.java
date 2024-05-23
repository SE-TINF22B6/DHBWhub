package de.tinf22b6.dhbwhub.controller;

import de.tinf22b6.dhbwhub.model.Account;
import de.tinf22b6.dhbwhub.proposal.AccountProposal;
import de.tinf22b6.dhbwhub.service.interfaces.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value = "/account")
public class AccountController {
    private final AccountService service;

    public AccountController(@Autowired AccountService service) {
        this.service = service;
    }

    @GetMapping
    public List<Account> getAll() {
        return service.getAll();
    }

    @PostMapping
    public Account create(@RequestBody AccountProposal proposal) {
        return service.create(proposal);
    }

    @GetMapping("/{id}")
    public Account get(@PathVariable Long id) {
        return service.get(id);
    }

    @PutMapping("/{id}")
    public Account update(@PathVariable Long id, @RequestBody AccountProposal proposal) {
        return service.update(id, proposal);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

}
