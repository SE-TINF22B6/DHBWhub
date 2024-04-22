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
public class Faculty {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "faculty_generator")
    @SequenceGenerator(name = "faculty_generator", sequenceName = "faculty_seq", allocationSize = 1)
    @Column(name = "faculty_id")
    private Long id;

    private final String name;
}
