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
public class User {
    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    private final Integer age;

    private final String description;

    @JoinColumn(name = "course_id")
    @ManyToOne
    private final Course course;

    @JoinColumn(name = "account_id")
    @ManyToOne
    private final Account account;
}
