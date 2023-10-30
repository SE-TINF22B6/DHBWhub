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
public class Administrator {
    @Id
    @GeneratedValue
    private Long adminId;

    @JoinColumn(name = "account_id")
    @ManyToOne
    private final Account account;
}
