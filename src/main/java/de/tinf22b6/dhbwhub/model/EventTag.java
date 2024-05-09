package de.tinf22b6.dhbwhub.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
@Entity
public class EventTag {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "event_tag_generator")
    @SequenceGenerator(name = "event_tag_generator", sequenceName = "event_tag_seq", allocationSize = 1)
    @Column(name = "tag_id")
    private Long id;

    @JoinColumn(name = "event_post_id")
    @ManyToOne(cascade = CascadeType.PERSIST)
    private final EventPost eventPost;

    private final String tag;

}
