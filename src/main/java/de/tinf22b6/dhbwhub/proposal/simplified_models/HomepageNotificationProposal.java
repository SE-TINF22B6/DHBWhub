package de.tinf22b6.dhbwhub.proposal.simplified_models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HomepageNotificationProposal {
    private Long notificationId;

    private Long groupId;

    private String text;

    private String link;

    private String type;
}
