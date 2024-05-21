package de.tinf22b6.dhbwhub.model.notification_tables;

import de.tinf22b6.dhbwhub.model.EventPost;
import de.tinf22b6.dhbwhub.model.User;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Entity
public class EventCommentLikeNotification {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "event_comment_like_notification_generator")
    @SequenceGenerator(name = "event_comment_like_notification_generator", sequenceName = "event_comment_like_notification_seq", allocationSize = 1)
    @Column(name = "notification_id")
    private Long id;

    @JoinColumn(name = "user_id")
    @ManyToOne(cascade = CascadeType.PERSIST)
    private final User user;

    @JoinColumn(name = "event_post_id")
    @ManyToOne(cascade = CascadeType.PERSIST)
    private final EventPost eventPost;

    @JoinColumn(name = "triggering_user_id")
    @ManyToOne(cascade = CascadeType.PERSIST)
    private final User triggeringUser;

    private final boolean seen;

    @Column(name = "accumulated_id")
    private Long accumulatedId;

    public Long getEventPostId(){
        return eventPost.getId();
    }
}
