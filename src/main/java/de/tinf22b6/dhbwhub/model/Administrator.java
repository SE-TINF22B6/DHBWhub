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
public class Administrator {
    @Id
    @GeneratedValue
    private Long adminId;

    @JoinColumn(name = "accountId")
    @ManyToOne
    private Account account;
}
