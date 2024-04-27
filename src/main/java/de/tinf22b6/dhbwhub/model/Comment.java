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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comment_generator")
    @SequenceGenerator(name = "comment_generator", sequenceName = "comment_seq", allocationSize = 1)
    @Column(name = "comment_id")
    private Long id;

    private String description;

    private Date timestamp;

    private int likes;

    @JoinColumn(name = "picture_id")
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Picture picture;

    @JoinColumn(name = "user_id")
    @ManyToOne(cascade = CascadeType.PERSIST)
    private User user;

    @JoinColumn(name = "post_id")
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Post post;
}
