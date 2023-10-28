package de.tinf22b6.dhbwhub.model;

import jakarta.persistence.*;
import lombok.*;

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
