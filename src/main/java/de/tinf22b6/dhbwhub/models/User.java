package de.tinf22b6.dhbwhub.models;

public class User {

    private String username;
    private String emailAddress;

    public User(String username, String emailAddress) {
        this.username = username;
        this.emailAddress = emailAddress;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

}
