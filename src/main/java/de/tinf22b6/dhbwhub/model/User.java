package de.tinf22b6.dhbwhub.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue
    private Long userId;

    private Integer age;

    private String description;

    @JoinColumn(name = "courseId")
    @ManyToOne
    private Course course;

    @JoinColumn(name = "accountId")
    @ManyToOne
    private Account account;

}
