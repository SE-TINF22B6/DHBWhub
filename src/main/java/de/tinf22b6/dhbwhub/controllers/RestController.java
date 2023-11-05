package de.tinf22b6.dhbwhub.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("login")
public class RestController {

    @PostMapping("/email")
    public void receiveEmailAddress(@RequestBody String email) {
        System.out.println(email);
    }

}
