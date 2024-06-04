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
public class OAuthAccount {
    public static String GOOGLE_ENTRY = "GOOGLE";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "oauth_account_generator")
    @SequenceGenerator(name = "oauth_account_generator", sequenceName = "oauth_account_seq", allocationSize = 1)
    @Column(name = "oauth_id")
    private Long id;

    @JoinColumn(name = "account_id")
    @ManyToOne(cascade = CascadeType.PERSIST)
    private final Account account;

    private final String authType;
}
