package de.tinf22b6.dhbwhub;

import de.tinf22b6.dhbwhub.model.*;
import de.tinf22b6.dhbwhub.model.log_tables.LikeLogtableEventComment;
import de.tinf22b6.dhbwhub.model.log_tables.LikeLogtableEventPost;
import de.tinf22b6.dhbwhub.model.log_tables.LikeLogtablePost;
import de.tinf22b6.dhbwhub.model.log_tables.LikeLogtablePostComment;
import de.tinf22b6.dhbwhub.model.notification_tables.*;
import de.tinf22b6.dhbwhub.proposal.*;
import de.tinf22b6.dhbwhub.proposal.simplified_models.*;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

public abstract class AbstractApplicationTest {
	protected Picture createDefaultPicture() {
		return new Picture("profile.png", new byte[]{ 12, 34, 45, 67, 78, 91 });
	}

	protected Picture createDefaultPicture2() {
		return new Picture("img-user", new byte[]{ 12, 34, 44, 67, 78 });
	}

	protected Account createDefaultAccount() {
		return new Account("maxmustermann1234", "max@mustermann.de", "1234", createDefaultPicture(), false);
	}

	protected Account createDefaultAccount2() {
		return new Account("miajulia1989", "miajulia89@gmx.de", "h9zdnh9hauidaw", createDefaultPicture2(), true);
	}

	protected Administrator createDefaultAdministrator() {
		return new Administrator(createDefaultAccount());
	}

	protected Administrator createDefaultAdministrator2() {
		return new Administrator(createDefaultAccount2());
	}

	protected Faculty createDefaultFaculty() {
		return new Faculty("Informatik");
	}

	protected Faculty createDefaultFaculty2() {
		return new Faculty("BWL");
	}

	protected Course createDefaultCourse() {
		return new Course("TINF22B6", createDefaultFaculty());
	}

	protected Course createDefaultCourse2() {
		return new Course("WBWL23B6", createDefaultFaculty2());
	}

	protected User createDefaultUser() {
		return new User(19, "Ich studiere Informatik", createDefaultCourse(), createDefaultAccount());
	}

	protected User createDefaultUser2() {
		return new User(21, "Ich studiere Jura", createDefaultCourse2(), createDefaultAccount2());
	}

	protected Friendship createDefaultFriendship() {
		return new Friendship(createDefaultUser(), createDefaultUser2());
	}

	protected Friendship createDefaultFriendship2() {
		return new Friendship(createDefaultUser2(), createDefaultUser());
	}

	protected Post createDefaultPost() {
		return new Post("Titel 1", "Beschreibung 1", new Date(1478979207L), 444, createDefaultPicture(), createDefaultUser(), createDefaultCourse());
	}

	protected Post createDefaultPost2() {
		return new Post("Titel 2", "Beschreibung 2", new Date(1478979183L), 555,  createDefaultPicture2(), createDefaultUser2(), createDefaultCourse2());
	}

	protected Post createUpdatedDefaultPost() {
		return new Post("Titel 1", "Beschreibung 1", new Date(1478979207L), 445, createDefaultPicture(), createDefaultUser(), createDefaultCourse());
	}

	protected Post createUpdatedDefaultPost2() {
		return new Post("Titel 1", "Beschreibung 1", new Date(1478979207L), 443, createDefaultPicture(), createDefaultUser(), createDefaultCourse());
	}

	protected Comment createDefaultComment() {
		return new Comment("Das ist ein normaler Kommentar", new Date(1701242553L), 4, createDefaultUser(), createDefaultPost());
	}

	protected Comment createDefaultComment2() {
		return new Comment("Du mieser Hund!", new Date(1678979183L), 5, createDefaultUser2(), createDefaultPost2());
	}

	protected Comment createUpdatedDefaultComment() {
		return new Comment("Das ist ein normaler Kommentar", new Date(1701242553L), 5, createDefaultUser(), createDefaultPost());
	}

	protected Comment createUpdatedDefaultComment2() {
		return new Comment("Das ist ein normaler Kommentar", new Date(1701242553L), 3, createDefaultUser(), createDefaultPost());
	}

	protected SavedPost createDefaultSavedPost() {
		return new SavedPost(createDefaultUser(), createDefaultPost());
	}

	protected SavedPost createDefaultSavedPost2() {
		return new SavedPost(createDefaultUser2(), createDefaultPost2());
	}


	protected PictureProposal createDefaultPictureProposal() {
		return new PictureProposal("profile.png", new byte[]{ 12, 34, 45, 67, 78, 91 });
	}

	protected AccountProposal createDefaultAccountProposal() {
		return new AccountProposal("maxmustermann1234", "max@mustermann.de", "1234", createDefaultPictureProposal(), false);
	}

	protected AdministratorProposal createDefaultAdministratorProposal() {
		return new AdministratorProposal(createDefaultAccountProposal());
	}

	protected FacultyProposal createDefaultFacultyProposal() {
		return new FacultyProposal("Informatik");
	}

	protected CourseProposal createDefaultCourseProposal() {
		return new CourseProposal("TINF22B6", createDefaultFacultyProposal());
	}

	protected UserProposal createDefaultUserProposal() {
		return new UserProposal(19, "Ich studiere Informatik", createDefaultCourseProposal(), createDefaultAccountProposal());
	}

	protected FriendshipProposal createDefaultFriendshipProposal() {
		return new FriendshipProposal(createDefaultAccountProposal(), createDefaultAccountProposal(), false);
	}

	protected PostProposal createDefaultPostProposal() {
		return new PostProposal("Titel 1", "Beschreibung 1", new Date(1478979207L), 444, createDefaultPictureProposal(), createDefaultUserProposal(), createDefaultCourseProposal());
	}

	protected CommentProposal createDefaultCommentProposal() {
		return new CommentProposal("Das ist ein normaler Kommentar", new Date(1701242553L), 4, createDefaultPictureProposal(), createDefaultUserProposal(), createDefaultPostProposal());
	}

	protected SavedPostProposal createDefaultSavedPostProposal() {
		return new SavedPostProposal(createDefaultUserProposal(), createDefaultPostProposal());
	}

	protected HomepagePostPreviewProposal createDefaultHomepagePostPreviewProposal() {
		return new HomepagePostPreviewProposal(1L,"Titel 1","Beschreibung 1", Arrays.stream(new String[]{"Tag 1", "Tag 2"}).toList(), 12, 4, new Date(1478979207L), new byte[]{ 12, 34, 45, 67, 78, 91 }, 1L, "Bruno");
	}

	protected PostThreadViewProposal createDefaultPostThreadViewProposal() {
		return new PostThreadViewProposal(1L,"Titel 1","Beschreibung 1", Arrays.stream(new String[]{"Tag 1", "Tag 2"}).toList(), 12, 4, new Date(1478979207L), new byte[]{ 12, 34, 45, 67, 78, 91 }, 1L, "Bruno", new byte[]{ 12, 34, 45, 67, 78, 91 }, List.of(createDefaultCommentThreadViewProposal()));
	}

	protected CommentThreadViewProposal createDefaultCommentThreadViewProposal() {
		return new CommentThreadViewProposal(1L, 0L,"Beschreibung 1", "Bruno", 1L,  new byte[]{ 12, 34, 45, 67, 78, 91 }, new Date(1478979207L), 23);
	}

	protected FriendlistProposal createDefaultFriendlistProposal() {
		return new FriendlistProposal(1L,2L,"Bruno", new byte[]{ 12, 34, 45, 67, 78, 91 });
	}

	protected CreateCommentProposal createDefaultCreateCommentProposal() {
		return new CreateCommentProposal(1L, 1L, "Beschreibung 1", new Date(1478979207L));
	}

	protected UpdateCommentProposal createDefaultUpdateCommentProposal() {
		return new UpdateCommentProposal("Beschreibung 1");
	}

	protected CreatePostProposal createDefaultCreatePostProposal() {
		return new CreatePostProposal("Titel 1", "Beschreibung 1", new String[]{"Tag 1", "Tag 2"}, new Date(1478979207L), new byte[]{ 12, 34, 45, 67, 78, 91 }, 1L);
	}

	protected UpdatePostProposal createDefaultUpdatePostProposal() {
		return new UpdatePostProposal("Titel 1", "Beschreibung 1", new String[]{"Tag 1", "Tag 2"}, createDefaultPicture().getImageData());
	}

	protected EventPost createDefaultEventPost() {
		return new EventPost("Titel 1", "Beschreibung 1", "Location 1", 1.0, 2.0, new Date(1478979207L), new Date(1478979208L), new Date(1478979209L), 1, createDefaultPicture(), createDefaultUser());
	}

	protected EventPost createUpdatedDefaultEventPost() {
		return new EventPost("Titel 1", "Beschreibung 1", "Location 1", 1.0, 2.0, new Date(1478979207L), new Date(1478979208L), new Date(1478979209L), 2, createDefaultPicture(), createDefaultUser());
	}

	protected EventPost createUpdatedDefaultEventPost2() {
		return new EventPost("Titel 1", "Beschreibung 1", "Location 1", 1.0, 2.0, new Date(1478979207L), new Date(1478979208L), new Date(1478979209L), 0, createDefaultPicture(), createDefaultUser());
	}

	protected EventComment createDefaultEventComment() {
		return new EventComment("Beschreibung 1", new Date(1478979207L), 1,  createDefaultUser(), createDefaultEventPost());
	}

	protected EventComment createUpdatedDefaultEventComment() {
		return new EventComment("Beschreibung 1", new Date(1478979207L), 2, createDefaultUser(), createDefaultEventPost());
	}

	protected EventComment createUpdatedDefaultEventComment2() {
		return new EventComment("Beschreibung 1", new Date(1478979207L), 0, createDefaultUser(), createDefaultEventPost());
	}

	protected EventTag createDefaultEventTag() {
		return new EventTag(createDefaultEventPost(), "Tag1");
	}

	protected LocationProposal createDefaultLocationProposal() {
		return new LocationProposal("Location 1", 1.0, 2.0);
	}
	protected CreateEventPostProposal createDefaultCreateEventPostProposal() {
		return new CreateEventPostProposal("Titel 1", "Beschreibung 1", createDefaultLocationProposal(), new Date(1478979207L), new Date(1478979208L), new Date(1478979209L), new String[]{"Tag 1", "Tag 2"}, createDefaultPicture().getImageData(), 1L);
	}

	protected UpdateEventPostProposal createDefaultUpdateEventPostProposal() {
		return new UpdateEventPostProposal("Titel 1", "Beschreibung 1", createDefaultLocationProposal(), new Date(1478979207L), new Date(1478979208L), new String[]{"Tag 1", "Tag 2"}, createDefaultPicture().getImageData());
	}

	protected EventThreadViewProposal createDefaultEventThreadViewProposal() {
		return new EventThreadViewProposal(1L, "Titel 1", "Beschreibung 1", List.of("Tag 1", "Tag 2"), createDefaultLocationProposal(), 0, 0, new Date(1478979208L), new Date(1478979209L), List.of(createDefaultEventCommentThreadViewProposal(),createDefaultEventCommentThreadViewProposal()));
	}

	protected CalendarEventProposal createDefaultCalendarEventProposal() {
		return new CalendarEventProposal(1L, "Titel 1", new Date(1478979208L), new Date(1478979209L));
	}

	protected HomepageEventPreviewProposal createDefaultEventPostPreviewProposal() {
		return new HomepageEventPreviewProposal(1L, "Titel 1", "Location 1", List.of("Tag 1", "Tag 2"), new Date(1478979207L));
	}

	protected EventCommentThreadViewProposal createDefaultEventCommentThreadViewProposal() {
		return new EventCommentThreadViewProposal(1L, 1L,  "Beschreibung 1", "Maxim", 1L, createDefaultPicture().getImageData(), new Date(1478979207L), 0);
	}

	protected CreateEventCommentProposal createDefaultCreateEventCommentProposal() {
		return new CreateEventCommentProposal(1L, 1L, "Beschreibung 1", new Date(1478979207L));
	}

	protected UpdateEventCommentProposal createDefaultUpdateEventCommentProposal() {
		return new UpdateEventCommentProposal("Beschreibung 1");
	}

	protected FollowUserProposal createDefaultFollowUserProposal() {
		return new FollowUserProposal(0L, 1L);
	}

	protected LikePostProposal createDefaultLikePostProposal() {
		return new LikePostProposal(1L, 1L);
	}

	protected LikeCommentProposal createDefaultLikeCommentProposal() {
		return new LikeCommentProposal(1L, 1L);
	}

	protected LikeEventPostProposal createDefaultLikeEventPostProposal() {
		return new LikeEventPostProposal(1L, 1L);
	}

	protected LikeEventCommentProposal createDefaultLikeEventCommentProposal() {
		return new LikeEventCommentProposal(1L, 1L);
	}

	protected HomepageNotificationProposal createHomepageNotificationProposal() {
		return new HomepageNotificationProposal(0L, null, "Notification1", "Link1", "Type-Post-Like");
	}

	protected HomepageNotificationProposal createHomepageNotificationProposal2() {
		return new HomepageNotificationProposal(1L, null, "Notification2", "Link2", "Type-Post-Comment");
	}

	protected HomepageNotificationProposal createHomepageNotificationProposal3() {
		return new HomepageNotificationProposal(2L, null, "Notification3", "Link3", "Type-Comment-Like");
	}

	protected HomepageNotificationProposal createHomepageNotificationProposal4() {
		return new HomepageNotificationProposal(3L, null, "Notification4", "Link4", "Type-Event-Comment-Like");
	}

	protected HomepageNotificationProposal createHomepageNotificationProposal5() {
		return new HomepageNotificationProposal(4L, null, "Notification5", "Link5", "Type-Follow");
	}

	protected List<HomepageNotificationProposal> createHomepageNotificationProposals() {
		return List.of(createHomepageNotificationProposal(), createHomepageNotificationProposal2(), createHomepageNotificationProposal3(), createHomepageNotificationProposal4(), createHomepageNotificationProposal5());
	}

	protected DeleteNotificationProposal createDeleteNotificationProposal() {
		return new DeleteNotificationProposal(1L, null, "Type-Post-Like");
	}

	protected DeleteNotificationProposal createDeleteNotificationProposal2() {
		return new DeleteNotificationProposal(1L, null, "Type-Post-Comment");
	}

	protected DeleteNotificationProposal createDeleteNotificationProposal3() {
		return new DeleteNotificationProposal(1L, null, "Type-Comment-Like");
	}

	protected DeleteNotificationProposal createDeleteNotificationProposal4() {
		return new DeleteNotificationProposal(1L, null, "Type-Event-Comment-Like");
	}

	protected DeleteNotificationProposal createDeleteNotificationProposal5() {
		return new DeleteNotificationProposal(1L, null, "Type-Follow");
	}

	protected UserLikes createDefaultUserLikes() {
		return new UserLikes(1L, List.of(1L, 2L), List.of(2L, 4L), List.of(1L, 3L), List.of(2L));
	}

	protected LikeLogtablePost createLikeLogtablePost() {
		return new LikeLogtablePost(createDefaultUser(), createDefaultPost());
	}

	protected LikeLogtablePostComment createLikeLogtablePostComment() {
		return new LikeLogtablePostComment(createDefaultUser(), createDefaultComment());
	}

	protected LikeLogtableEventPost createLikeLogtableEventPost() {
		return new LikeLogtableEventPost(createDefaultUser(), createDefaultEventPost());
	}

	protected LikeLogtableEventComment createLikeLogtableEventComment() {
		return new LikeLogtableEventComment(createDefaultUser(), createDefaultEventComment());
	}

	protected PostLikeNotification createPostLikeNotification() {
		return new PostLikeNotification(1L, createDefaultUser(), createDefaultPost(), createDefaultUser2(), false, null);
	}

	protected PostCommentNotification createPostCommentNotification() {
		return new PostCommentNotification(1L, createDefaultUser(), createDefaultPost(), createDefaultUser2(), false, null);
	}

	protected CommentLikeNotification createCommentLikeNotification() {
		return new CommentLikeNotification(1L, createDefaultUser(), createDefaultPost(), createDefaultUser2(), false, null);
	}

	protected EventCommentLikeNotification createEventCommentLikeNotification() {
		return new EventCommentLikeNotification(1L, createDefaultUser(), createDefaultEventPost(), createDefaultUser2(), false, null);
	}

	protected FollowNotification createFollowNotification() {
		return new FollowNotification(1L, createDefaultUser(), createDefaultUser2(), false, null);
	}

	protected HomepageSavedPostProposal createHomepageSavedPostProposal() {
		return new HomepageSavedPostProposal(1L, 1L, "Beschreibung 1");
	}

	protected CreateSavedPostProposal createCreateSavedPostProposal() {
		return new CreateSavedPostProposal(1L, 1L);
	}

	protected DeleteSavedPostProposal createDeleteSavedPostProposal() {
		return new DeleteSavedPostProposal(1L, 1L);
	}

	protected ProfileInformationProposal createProfileInformationProposal() {
		return new ProfileInformationProposal(12, "adsa@gmail.com", "Beschreibung 1", "TINF22B6");
	}

	protected UserInformationProposal createUserInformationProposal() {
		return new UserInformationProposal(1L , new byte[]{12, 12, 12, 12}, 12,  12, "Beschreibung 1", "TINF22B6");
	}

	protected UpdateAgeProposal createUpdateAgeProposal() {
		return new UpdateAgeProposal(1L, 12);
	}

	protected UpdateDescriptionProposal createUpdateDescriptionProposal() {
		return new UpdateDescriptionProposal(1L, "Beschreibung 1");
	}

	protected UpdateCourseProposal createUpdateCourseProposal() {
		return new UpdateCourseProposal(1L, "TINF22B6");
	}

	protected UpdateEmailProposal createUpdateEmailProposal() {
		return new UpdateEmailProposal(1L, "test@gmail.com");
	}

	protected UpdateUsernameProposal createUpdateUsernameProposal() {
		return new UpdateUsernameProposal(1L, "User1");
	}

	protected UpdatePasswordProposal createUpdatePasswordProposal() {
		return new UpdatePasswordProposal(1L, "NeuesPasswort");
	}

	protected UpdatePictureProposal createUpdatePictureProposal() {
		return new UpdatePictureProposal(1L, new byte[]{12, 12, 12, 12});
	}
}
