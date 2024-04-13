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
@Table(name = "saved_post")
public class SavedPost {
    @Id
    @GeneratedValue
    @Column(name = "saved_post_id")
    private Long id;

    @JoinColumn(name = "client_user_id")
    @ManyToOne(cascade = CascadeType.PERSIST)
    private final User user;

    @JoinColumn(name = "post_id")
    @ManyToOne(cascade = CascadeType.PERSIST)
    private final Post post;
}
