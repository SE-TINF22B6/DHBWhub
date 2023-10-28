package de.tinf22b6.dhbwhub.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Post {
    @Id
    @GeneratedValue
    private Long postId;

    private String title;

    private String description;

    private Date timestamp;

    @JoinColumn(name = "pictureId")
    @ManyToOne
    private Picture picture;

    @JoinColumn(name = "userId")
    @ManyToOne
    private User user;

    @JoinColumn(name = "courseId")
    @ManyToOne
    private Course course;
}
