package de.tinf22b6.dhbwhub.model.logtables;

import de.tinf22b6.dhbwhub.model.Account;
import de.tinf22b6.dhbwhub.model.Post;
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
@Table(name = "like_logtable_post")
public class LikeLogtablePost {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "log_post_generator")
    @SequenceGenerator(name = "log_post_generator", sequenceName = "log_post_seq", allocationSize = 1)
    @Column(name = "log_id")
    private Long id;

    @JoinColumn(name = "user_id")
    @ManyToOne(cascade = CascadeType.PERSIST)
    private final User user;

    @JoinColumn(name = "post_id")
    @ManyToOne(cascade = CascadeType.PERSIST)
    private final Post post;
}
