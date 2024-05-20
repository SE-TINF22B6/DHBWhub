package de.tinf22b6.dhbwhub.model.log_tables;

import de.tinf22b6.dhbwhub.model.EventPost;
import de.tinf22b6.dhbwhub.model.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
@Entity
@Table(name = "like_logtable_event_post")
public class LikeLogtableEventPost {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "log_event_generator")
    @SequenceGenerator(name = "log_event_generator", sequenceName = "log_event_seq", allocationSize = 1)
    @Column(name = "log_id")
    private Long id;

    @JoinColumn(name = "user_id")
    @ManyToOne(cascade = CascadeType.PERSIST)
    private final User user;

    @JoinColumn(name = "event_post_id")
    @ManyToOne(cascade = CascadeType.PERSIST)
    private final EventPost eventPost;
}
