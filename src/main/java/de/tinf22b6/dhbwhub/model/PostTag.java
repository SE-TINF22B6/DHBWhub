package de.tinf22b6.dhbwhub.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Entity
public class PostTag {
    @Id
    @GeneratedValue
    @Column(name = "tag_id")
    private Long id;

    @JoinColumn(name = "post_id")
    @ManyToOne(cascade = CascadeType.PERSIST)
    private final Post post;

    private final String tag;

}
