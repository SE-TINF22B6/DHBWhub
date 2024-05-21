package de.tinf22b6.dhbwhub.service.interfaces;

import de.tinf22b6.dhbwhub.proposal.simplified_models.DeleteNotificationProposal;
import de.tinf22b6.dhbwhub.proposal.simplified_models.HomepageNotificationProposal;

import java.util.List;

public interface NotificationService {
    List<HomepageNotificationProposal> getUserNotifications(Long id);
    void deleteNotification(DeleteNotificationProposal deleteNotificationProposal);
}
