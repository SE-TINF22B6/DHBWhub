package de.tinf22b6.dhbwhub.model;

public class APILoginRequest {

    private String email;

    public APILoginRequest(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
