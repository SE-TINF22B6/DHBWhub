package de.tinf22b6.dhbwhub.model.notification_tables;

import de.tinf22b6.dhbwhub.model.User;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Entity
public class FollowNotification {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "follow_notification_generator")
    @SequenceGenerator(name = "follow_notification_generator", sequenceName = "follow_notification_seq", allocationSize = 1)
    @Column(name = "notification_id")
    private Long id;

    @JoinColumn(name = "user_id")
    @ManyToOne(cascade = CascadeType.PERSIST)
    private final User user;

    @JoinColumn(name = "triggering_user_id")
    @ManyToOne(cascade = CascadeType.PERSIST)
    private final User triggeringUser;

    private final boolean seen;

    @Column(name = "accumulated_id")
    private Long accumulatedId;
}
