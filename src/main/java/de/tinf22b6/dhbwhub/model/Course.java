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
public class Course {
    @Id
    @GeneratedValue
    @Column(name = "course_id")
    private Long id;

    private final String name;

    @JoinColumn(name = "faculty_id")
    @ManyToOne(cascade = CascadeType.PERSIST)
    private final Faculty faculty;
}
