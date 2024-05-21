package de.tinf22b6.dhbwhub.model.notification_tables;

import de.tinf22b6.dhbwhub.model.Post;
import de.tinf22b6.dhbwhub.model.User;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Entity
public class PostCommentNotification {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "post_comment_notification_generator")
    @SequenceGenerator(name = "post_comment_notification_generator", sequenceName = "post_comment_notification_seq", allocationSize = 1)
    @Column(name = "notification_id")
    private Long id;

    @JoinColumn(name = "user_id")
    @ManyToOne(cascade = CascadeType.PERSIST)
    private final User user;

    @JoinColumn(name = "post_id")
    @ManyToOne(cascade = CascadeType.PERSIST)
    private final Post post;

    @JoinColumn(name = "triggering_user_id")
    @ManyToOne(cascade = CascadeType.PERSIST)
    private final User triggeringUser;

    private final boolean seen;

    @Column(name = "accumulated_id")
    private Long accumulatedId;

    public Long getPostId(){
        return post.getId();
    }
}
