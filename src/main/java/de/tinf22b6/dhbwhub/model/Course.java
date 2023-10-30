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
    private Long courseId;

    private final String name;

    @JoinColumn(name = "fac_id")
    @ManyToOne
    private final Faculty faculty;
}
