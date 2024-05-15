package de.tinf22b6.dhbwhub.model.logtables;

import de.tinf22b6.dhbwhub.model.Comment;
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
@Table(name = "like_logtable_post_comment")
public class LikeLogtablePostComment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "log_comment_generator")
    @SequenceGenerator(name = "log_comment_generator", sequenceName = "log_comment_seq", allocationSize = 1)
    @Column(name = "log_id")
    private Long id;

    @JoinColumn(name = "user_id")
    @ManyToOne(cascade = CascadeType.PERSIST)
    private final User user;

    @JoinColumn(name = "comment_id")
    @ManyToOne(cascade = CascadeType.PERSIST)
    private final Comment comment;
}
