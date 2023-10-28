package de.tinf22b6.dhbwhub.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Picture {
    @Id
    @GeneratedValue
    private Long pictureId;

    private String name;

    @Lob
    private Byte[] imageData;
}
