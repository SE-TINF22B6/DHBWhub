package de.tinf22b6.dhbwhub.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Entity
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "notification_generator")
    @SequenceGenerator(name = "notification_generator", sequenceName = "notification_seq", allocationSize = 1)
    @Column(name = "notification_id")
    private Long id;

    @JoinColumn(name = "account_id")
    @ManyToOne(cascade = CascadeType.PERSIST)
    private final Account account;

    private final String description;

    @JoinColumn(name = "post_id")
    @ManyToOne(cascade = CascadeType.PERSIST)
    private final Post post;

    @JoinColumn(name = "event_id")
    @ManyToOne(cascade = CascadeType.PERSIST)
    private final EventPost eventPost;

    @JoinColumn(name = "friend_id")
    @ManyToOne(cascade = CascadeType.PERSIST)
    private final Account friend;

    private final boolean read;
}
