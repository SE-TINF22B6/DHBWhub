package de.tinf22b6.dhbwhub.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
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

    @Lob
    private final List<String> tags;

    @JoinColumn(name = "picture_id")
    @ManyToOne
    private final Picture picture;

    @JoinColumn(name = "user_id")
    @ManyToOne
    private final User user;

    @JoinColumn(name = "course_id")
    @ManyToOne
    private final Course course;

}
