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
    private Long userId;

    private final Integer age;

    private final String description;

    @JoinColumn(name = "courseId")
    @ManyToOne
    private final Course course;

    @JoinColumn(name = "accountId")
    @ManyToOne
    private final Account account;
}
