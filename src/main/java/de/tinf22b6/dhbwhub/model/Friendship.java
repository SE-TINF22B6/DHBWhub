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
public class Friendship {
    @Id
    @GeneratedValue
    @Column(name = "friendship_id")
    private Long id;

    @JoinColumn(name = "requester_id")
    @ManyToOne(cascade = CascadeType.PERSIST)
    private final Account requester;

    @JoinColumn(name = "receiver_id")
    @ManyToOne(cascade = CascadeType.PERSIST)
    private final Account receiver;

    private final boolean accepted;
}
