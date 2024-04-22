package de.tinf22b6.dhbwhub.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Entity
public class Post {
    @Id
    @GeneratedValue
    @Column(name = "post_id")
    private Long id;

    private final String title;

    private final String description;

    private final Date timestamp;

    private final int likes;

    @JoinColumn(name = "picture_id")
    @ManyToOne(cascade = CascadeType.PERSIST)
    private final Picture picture;

    @JoinColumn(name = "user_id")
    @ManyToOne(cascade = CascadeType.PERSIST)
    private final User user;

    @JoinColumn(name = "course_id")
    @ManyToOne(cascade = CascadeType.PERSIST)
    private final Course course;
}
