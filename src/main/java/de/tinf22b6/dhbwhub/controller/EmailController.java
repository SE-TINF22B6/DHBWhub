package de.tinf22b6.dhbwhub.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("login")
public class EmailController {
    @PostMapping("/email")
    public void receiveEmailAddress(@RequestBody String email) {
        System.out.println(email);
    }
}
