package de.tinf22b6.dhbwhub.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Account {
    @Id
    @GeneratedValue
    private Long accountId;

    private String username;

    private String email;

    private String password;

    @JoinColumn(name = "pictureId")
    @ManyToOne
    private Picture picture;
}
