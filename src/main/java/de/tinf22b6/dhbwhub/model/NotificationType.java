package de.tinf22b6.dhbwhub.model;

public enum NotificationType {
    TYPE_POST_COMMENT("TYPE-POST-COMMENT"),
    TYPE_POST_LIKE("TYPE-POST-LIKE"),
    TYPE_COMMENT_LIKE("TYPE-COMMENT-LIKE"),
    TYPE_FOLLOW("TYPE-FOLLOW"),
    TYPE_EVENT_COMMENT_LIKE("TYPE-EVENT-COMMENT-LIKE");

    private String type;
    NotificationType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
