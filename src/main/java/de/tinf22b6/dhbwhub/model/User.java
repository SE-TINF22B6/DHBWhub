package de.tinf22b6.dhbwhub.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
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
