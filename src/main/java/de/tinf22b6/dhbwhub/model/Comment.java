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
public class Comment {
    @Id
    @GeneratedValue
    @Column(name = "comment_id")
    private Long id;

    private final String description;

    private final Date timestamp;

    private final int likes;

    @JoinColumn(name = "picture_id")
    @ManyToOne
    private final Picture picture;

    @JoinColumn(name = "user_id")
    @ManyToOne
    private final User user;

    @JoinColumn(name = "post_id")
    @ManyToOne
    private final Post post;
}
