package de.tinf22b6.dhbwhub.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
@Entity
public class Account {
    @Id
    @GeneratedValue
    private Long accountId;

    private final String username;

    private final String email;

    private final String password;

    @JoinColumn(name = "picture_id")
    @ManyToOne
    private final Picture picture;
}
