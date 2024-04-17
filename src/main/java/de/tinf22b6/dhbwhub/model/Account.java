package de.tinf22b6.dhbwhub.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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
    private final Picture picture;

    private final boolean active;

}
