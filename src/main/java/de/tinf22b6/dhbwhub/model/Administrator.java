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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "administrator_generator")
    @SequenceGenerator(name = "administrator_generator", sequenceName = "administrator_seq", allocationSize = 1)
    @Column(name = "admin_id")
    private Long id;

    @JoinColumn(name = "account_id")
    @ManyToOne(cascade = CascadeType.PERSIST)
    private final Account account;
}
