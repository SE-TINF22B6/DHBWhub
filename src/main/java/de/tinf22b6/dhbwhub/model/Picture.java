package de.tinf22b6.dhbwhub.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
@Entity
public class Picture {
    @Id
    @GeneratedValue
    @Column(name = "picture_id")
    private Long id;

    private final String name;

    @ElementCollection
    private final List<Byte> imageData;
}
