package de.tinf22b6.dhbwhub.model.views;

import de.tinf22b6.dhbwhub.model.Course;
import de.tinf22b6.dhbwhub.model.Picture;
import de.tinf22b6.dhbwhub.model.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Immutable;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
@Immutable
@Entity
public class View_comment_amount {
    @Id
    @GeneratedValue
    @Column(name = "post_id")
    private Long id;

    private final int comment_amount;

}
