package de.tinf22b6.dhbwhub;

import de.tinf22b6.dhbwhub.model.*;
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
		return new Friendship(createDefaultAccount(), createDefaultAccount2());
	}

	protected Friendship createDefaultFriendship2() {
		return new Friendship(createDefaultAccount2(), createDefaultAccount());
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

	protected CreateCommentProposal createDefaultCreateCommentProposal(){
		return new CreateCommentProposal(1L, 1L, "Beschreibung 1", new Date(1478979207L));
	}

	protected UpdateCommentProposal createDefaultUpdateCommentProposal(){
		return new UpdateCommentProposal("Beschreibung 1");
	}

	protected CreatePostProposal createDefaultCreatePostProposal(){
		return new CreatePostProposal("Titel 1", "Beschreibung 1", new String[]{"Tag 1", "Tag 2"}, new Date(1478979207L), new byte[]{ 12, 34, 45, 67, 78, 91 }, 1L);
	}

	protected UpdatePostProposal createDefaultUpdatePostProposal(){
		return new UpdatePostProposal("Titel 1", "Beschreibung 1", new String[]{"Tag 1", "Tag 2"}, createDefaultPicture().getImageData());
	}

	protected EventPost createDefaultEventPost(){
		return new EventPost("Titel 1", "Beschreibung 1", "Location 1", 1.0, 2.0, new Date(1478979207L), new Date(1478979208L), new Date(1478979209L), 1, createDefaultPicture(), createDefaultUser());
	}

	protected EventPost createUpdatedDefaultEventPost(){
		return new EventPost("Titel 1", "Beschreibung 1", "Location 1", 1.0, 2.0, new Date(1478979207L), new Date(1478979208L), new Date(1478979209L), 2, createDefaultPicture(), createDefaultUser());
	}

	protected EventPost createUpdatedDefaultEventPost2(){
		return new EventPost("Titel 1", "Beschreibung 1", "Location 1", 1.0, 2.0, new Date(1478979207L), new Date(1478979208L), new Date(1478979209L), 0, createDefaultPicture(), createDefaultUser());
	}

	protected EventComment createDefaultEventComment(){
		return new EventComment("Beschreibung 1", new Date(1478979207L), 1,  createDefaultUser(), createDefaultEventPost());
	}

	protected EventComment createUpdatedDefaultEventComment(){
		return new EventComment("Beschreibung 1", new Date(1478979207L), 2, createDefaultUser(), createDefaultEventPost());
	}

	protected EventComment createUpdatedDefaultEventComment2(){
		return new EventComment("Beschreibung 1", new Date(1478979207L), 0, createDefaultUser(), createDefaultEventPost());
	}

	protected EventTag createDefaultEventTag(){
		return new EventTag(createDefaultEventPost(), "Tag1");
	}

	protected LocationProposal createDefaultLocationProposal(){
		return new LocationProposal("Location 1", 1.0, 2.0);
	}
	protected CreateEventPostProposal createDefaultCreateEventPostProposal(){
		return new CreateEventPostProposal("Titel 1", "Beschreibung 1", createDefaultLocationProposal(), new Date(1478979207L), new Date(1478979208L), new Date(1478979209L), new String[]{"Tag 1", "Tag 2"}, createDefaultPicture().getImageData(), 1L);
	}

	protected UpdateEventPostProposal createDefaultUpdateEventPostProposal(){
		return new UpdateEventPostProposal("Titel 1", "Beschreibung 1", createDefaultLocationProposal(), new Date(1478979207L), new Date(1478979208L), new String[]{"Tag 1", "Tag 2"}, createDefaultPicture().getImageData());
	}

	protected EventThreadViewProposal createDefaultEventThreadViewProposal(){
		return new EventThreadViewProposal(1L, "Titel 1", "Beschreibung 1", List.of("Tag 1", "Tag 2"), createDefaultLocationProposal(), 0, 0, new Date(1478979207L), new Date(1478979208L), new Date(1478979209L), createDefaultPicture().getImageData(), 1L, "Maxi", createDefaultPicture2().getImageData(), List.of(createDefaultEventCommentThreadViewProposal(),createDefaultEventCommentThreadViewProposal()));
	}

	protected HomepageEventPreviewProposal createDefaultEventPostPreviewProposal(){
		return new HomepageEventPreviewProposal(1L, "Titel 1", "Location 1", List.of("Tag 1", "Tag 2"), new Date(1478979207L));
	}

	protected EventCommentThreadViewProposal createDefaultEventCommentThreadViewProposal(){
		return new EventCommentThreadViewProposal(1L, 1L,  "Beschreibung 1", "Maxim", 1L, createDefaultPicture().getImageData(), new Date(1478979207L), 0);
	}

	protected CreateEventCommentProposal createDefaultCreateEventCommentProposal(){
		return new CreateEventCommentProposal(1L, 1L, "Beschreibung 1", new Date(1478979207L));
	}

	protected UpdateEventCommentProposal createDefaultUpdateEventCommentProposal(){
		return new UpdateEventCommentProposal("Beschreibung 1");
	}

	protected  FollowUserProposal createDefaultFollowUserProposal(){
		return new FollowUserProposal(0L, 1L);
	}
}
