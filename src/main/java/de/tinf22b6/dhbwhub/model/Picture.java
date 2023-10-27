package de.tinf22b6.dhbwhub.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
public class Picture {

    @Id
    @GeneratedValue
    private Long pictureId;

    private String name;

    private Byte[] imageData;

}
