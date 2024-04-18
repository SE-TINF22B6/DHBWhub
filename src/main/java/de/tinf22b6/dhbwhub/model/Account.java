package de.tinf22b6.dhbwhub.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor(force = true)
@Entity
public class Account {

    @Id
    @GeneratedValue
    @Column(name = "account_id")
    private Long id;

    @NotBlank
    private final String username;

    @NotBlank
    private final String email;

    @NotBlank
    private final String password;

    @JoinColumn(name = "picture_id")
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Picture picture;

    private final boolean active;

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Role> roles = new HashSet<>();

    public Account(String username, String email, String password, boolean active) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.active = active;
    }

    public Account(String username, String email, String password, Picture picture, boolean active) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.picture = picture;
        this.active = active;
    }

}
