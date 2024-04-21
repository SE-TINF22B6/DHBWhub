package de.tinf22b6.dhbwhub;

import de.tinf22b6.dhbwhub.model.*;
import de.tinf22b6.dhbwhub.proposal.*;

import java.sql.Date;
import java.util.Arrays;

public abstract class AbstractApplicationTest {
	protected Picture createDefaultPicture() {
		return new Picture("profile.png", Arrays.stream(new Byte[]{ 12, 34, 45, 67, 78, 91 }).toList());
	}

	protected Picture createDefaultPicture2() {
		return new Picture("img-user", Arrays.stream(new Byte[]{ 12, 34, 44, 67, 78 }).toList());
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

	protected Event createDefaultEvent() {
		return new Event("Master-Event", new Date(1701242553L));
	}

	protected Event createDefaultEvent2() {
		return new Event("Slave-Event", new Date(1701231243L));
	}

	protected User createDefaultUser() {
		return new User(19, "Ich studiere Informatik", createDefaultCourse(), createDefaultAccount());
	}

	protected User createDefaultUser2() {
		return new User(21, "Ich studiere Jura", createDefaultCourse2(), createDefaultAccount2());
	}

	protected Friendship createDefaultFriendship() {
		return new Friendship(createDefaultAccount(), createDefaultAccount2(), false);
	}

	protected Friendship createDefaultFriendship2() {
		return new Friendship(createDefaultAccount2(), createDefaultAccount(), false);
	}

	protected Post createDefaultPost() {
		return new Post("Titel 1", "Beschreibung 1", new Date(1478979207L), 444, createDefaultPicture(), createDefaultUser(), createDefaultCourse());
	}

	protected Post createDefaultPost2() {
		return new Post("Titel 2", "Beschreibung 2", new Date(1478979183L), 555,  createDefaultPicture2(), createDefaultUser2(), createDefaultCourse2());
	}

	protected Comment createDefaultComment() {
		return new Comment("Das ist ein normaler Kommentar", new Date(1701242553L), 4, createDefaultPicture(), createDefaultUser(), createDefaultPost());
	}

	protected Comment createDefaultComment2() {
		return new Comment("Du mieser Hund!", new Date(1678979183L), 5, createDefaultPicture2(), createDefaultUser2(), createDefaultPost2());
	}

	protected SavedPost createDefaultSavedPost() {
		return new SavedPost(createDefaultUser(), createDefaultPost());
	}

	protected SavedPost createDefaultSavedPost2() {
		return new SavedPost(createDefaultUser2(), createDefaultPost2());
	}


	protected PictureProposal createDefaultPictureProposal() {
		return new PictureProposal("profile.png", Arrays.stream(new Byte[]{ 12, 34, 45, 67, 78, 91 }).toList());
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

	protected EventProposal createDefaultEventProposal() {
		return new EventProposal("Master-Event", new Date(1701242553L));
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
}
