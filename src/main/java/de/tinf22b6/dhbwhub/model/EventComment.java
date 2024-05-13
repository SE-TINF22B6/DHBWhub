package de.tinf22b6.dhbwhub.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
@Entity
public class EventComment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "event_comment_generator")
    @SequenceGenerator(name = "event_comment_generator", sequenceName = "event_comment_seq", allocationSize = 1)
    @Column(name = "event_comment_id")
    private Long id;

    private final String description;

    private final Date timestamp;

    private final int likes;

    @JoinColumn(name = "user_id")
    @ManyToOne(cascade = CascadeType.PERSIST)
    private final User user;

    @JoinColumn(name = "event_post_id")
    @ManyToOne(cascade = CascadeType.PERSIST)
    private final EventPost eventPost;
}
