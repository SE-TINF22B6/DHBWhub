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
public class Picture {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "picture_generator")
    @SequenceGenerator(name = "picture_generator", sequenceName = "picture_seq", allocationSize = 1)
    @Column(name = "picture_id")
    private Long id;

    private final String name;

    private final String imageData;

}
