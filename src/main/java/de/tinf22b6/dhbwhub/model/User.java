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
@Table(name = "client_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_generator")
    @SequenceGenerator(name = "user_generator", sequenceName = "user_seq", allocationSize = 1)
    @Column(name = "user_id")
    private Long id;

    private final Integer age;

    private final String description;

    @JoinColumn(name = "course_id")
    @ManyToOne(cascade = CascadeType.PERSIST)
    private final Course course;

    @JoinColumn(name = "account_id")
    @ManyToOne(cascade = CascadeType.PERSIST)
    private final Account account;
}
