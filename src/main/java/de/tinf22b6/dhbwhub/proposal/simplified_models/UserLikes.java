package de.tinf22b6.dhbwhub.proposal.simplified_models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserLikes {
    private Long userId;

    private List<Long> likedPosts;

    private List<Long> likedComments;

    private List<Long> likedEvents;

    private List<Long> likedEventComments;
}
