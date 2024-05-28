package de.tinf22b6.dhbwhub.controller;

import de.tinf22b6.dhbwhub.model.Picture;
import de.tinf22b6.dhbwhub.proposal.PictureProposal;
import de.tinf22b6.dhbwhub.service.interfaces.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"https://www.dhbwhub.de", "http://localhost:3000"})
@RequestMapping(value = "/picture")
public class PictureController {
    private final PictureService service;

    public PictureController(@Autowired PictureService service) {
        this.service = service;
    }

    @GetMapping
    public List<Picture> getAll() {
        return service.getAll();
    }

    @PostMapping
    public Picture create(@RequestBody PictureProposal proposal) {
        return service.create(proposal);
    }

    @GetMapping("/{id}")
    public Picture get(@PathVariable Long id) {
        return service.get(id);
    }

    @GetMapping("/find/{id}")
    public Picture findByUserId(@PathVariable Long id) {
        return service.findByUserId(id);
    }

    @PutMapping("/{id}")
    public Picture update(@PathVariable Long id, @RequestBody PictureProposal proposal) {
        return service.update(id, proposal);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
