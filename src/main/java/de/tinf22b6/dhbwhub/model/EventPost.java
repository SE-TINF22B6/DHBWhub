package de.tinf22b6.dhbwhub.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
@Entity
public class EventPost {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "event_post_generator")
    @SequenceGenerator(name = "event_post_generator", sequenceName = "event_post_seq", allocationSize = 1)
    @Column(name = "event_post_id")
    private Long id;

    private final String title;

    private final String description;

    private final String location;

    private final Double latitude;

    private final Double longitude;

    private final Date timestamp;

    private final Date startdate;

    private final Date enddate;

    private final int likes;

    @JoinColumn(name = "picture_id")
    @ManyToOne(cascade = CascadeType.PERSIST)
    private final Picture picture;

    @JoinColumn(name = "user_id")
    @ManyToOne(cascade = CascadeType.PERSIST)
    private final User user;

}
