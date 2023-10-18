package de.tinf22b6.dhbwhub.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    private String username;
    private String emailAddress;

    public User(String username, String emailAddress) {
        this.username = username;
        this.emailAddress = emailAddress;
    }
}
