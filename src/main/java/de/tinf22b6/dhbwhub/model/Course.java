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
public class Course {
    @Id
    @GeneratedValue
    private Long courseId;

    private String name;

    @JoinColumn(name = "facId")
    @ManyToOne
    private Faculty faculty;
}
