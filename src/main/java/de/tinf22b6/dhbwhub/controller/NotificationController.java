package de.tinf22b6.dhbwhub.controller;

import de.tinf22b6.dhbwhub.proposal.simplified_models.DeleteNotificationProposal;
import de.tinf22b6.dhbwhub.proposal.simplified_models.HomepageNotificationProposal;
import de.tinf22b6.dhbwhub.service.interfaces.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/notification")
public class NotificationController {
    private final NotificationService service;

    public NotificationController(@Autowired NotificationService service) {
        this.service = service;
    }

    @GetMapping("/unseen/{id}")
    public List<HomepageNotificationProposal> getNotificationsByUser(@PathVariable Long id) {
        return service.getUserNotifications(id);
    }

    @DeleteMapping
    public void deleteNotification(@RequestBody DeleteNotificationProposal deleteNotificationProposal) {
        service.deleteNotification(deleteNotificationProposal);
    }
}
