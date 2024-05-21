package de.tinf22b6.dhbwhub.model;

public enum NotificationType {
    TYPE_POST_COMMENT("Type-Post-Comment"),
    TYPE_POST_LIKE("Type-Post-Like"),
    TYPE_COMMENT_LIKE("Type-Comment-Like"),
    TYPE_FOLLOW("Type-Follow"),
    TYPE_EVENT_COMMENT_LIKE("Type-Event-Comment-Like");

    private String type;
    NotificationType(String type) {
        this.type = type;
    }
}
