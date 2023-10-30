package de.tinf22b6.dhbwhub.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
@Entity
public class Post {
    @Id
    @GeneratedValue
    private Long postId;

    private final String title;

    private final String description;

    private final Date timestamp;

    @JoinColumn(name = "pictureId")
    @ManyToOne
    private final Picture picture;

    @JoinColumn(name = "userId")
    @ManyToOne
    private final User user;

    @JoinColumn(name = "courseId")
    @ManyToOne
    private final Course course;
}
