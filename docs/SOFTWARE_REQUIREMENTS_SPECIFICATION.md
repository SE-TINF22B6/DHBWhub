# Software Requirements Specification - DHBWhub

- [1. Introduction](#1-introduction)
   * [1.1 Overview](#11-overview)
   * [1.2 Scope](#12-scope)
   * [1.3 Definitions, Acronyms and Abbreviations](#13-definitions-acronyms-and-abbreviations)
   * [1.4 References](#14-references)
- [2. Functional requirements](#2-functional-requirements)
   * [2.1 Overview](#21-overview)
      + [2.1.1 General use case](#211-general-use-case)
      + [2.1.2 Important entities and classes](#212-important-entities-and-classes)
   * [2.2 Use Cases](#22-use-cases)
      + [2.2.1 Browse Homepage](#221-browse-homepage)
      + [2.2.2 Creating account/Signing in](#222-creating-accountsigning-in)
      + [2.2.3 Account Management](#223-account-management)
         - [2.2.3.1 Profile personalization](#2231-profile-personalization)
         - [2.2.3.2 Managing 3rd-party login](#2232-managing-3rd-party-login)
      + [2.2.4 Website activities (individual)](#224-website-activities-individual)
         - [2.2.4.1 Browsing personalized Feed](#2241-browsing-personalized-feed)
         - [2.2.4.2 Create posts](#2242-create-posts)
         - [2.2.4.3 Save posts](#2243-save-posts)
         - [2.2.4.4 View Calendar and events](#2244-view-calendar-and-events)
         - [2.2.4.5 Checking notifications](#2245-checking-notifications)
         - [2.2.4.6 Search bar](#2246-search-bar)
      + [2.2.5 User Interaction](#225-user-interaction)
         - [2.2.5.1 Post interaction](#2251-post-interaction)
         - [2.2.5.2 Friendlist administration](#2252-friendlist-administration)
      + [2.2.6 Administrative activities](#226-administrative-activities)
         - [2.2.6.1 View legal notice and FAQ page ](#2261-view-legal-notice-and-faq-page)
         - [2.2.6.2 Report issues/requests to administrators ](#2262-report-issuesrequests-to-administrators)
- [3. Nonfunctional requirements](#3-nonfunctional-requirements)
   * [3.1 Usability](#31-usability)
      + [3.1.1 Quick Learning](#311-quick-learning)
      + [3.1.2 Visual feedback](#312-visual-feedback)
   * [3.2 Modifiability](#32-modifiability)
      + [3.2.1 Modularity](#321-modularity)
      + [3.2.2 Component Design](#322-component-design)
   * [3.3 Performance](#33-performance)
      + [3.3.1 Response time](#331-response-time)
      + [3.3.2 Asynchronous loading](#332-asynchronous-loading)
   * [3.4 Security](#34-security)
- [4. Technical constraints](#4-technical-constraints)


     
## 1. Introduction

### 1.1 Overview
We are a DHBW-internal forum where users can ask questions and post DHBW-related content. The site is divided into several sections which represent the faculty and the courses of the DHBW. Besides, users can befriend each other so that socializing with other students gets even easier.

### 1.2 Scope
This Software Requirements Specification (SRS) document covers the complete system of DHBWhub. It encompasses both functional and non-functional requirements necessary for the successful development, deployment, and operation of the platform. The document aims to provide a comprehensive understanding of the system's architecture, features, and limitations.

### 1.3 Definitions, Acronyms and Abbreviations
> TODO.

### 1.4 References
Documents:
- Rough Use-Case-Diagram for the End-User, 24.10.2023:\
  https://github.com/SE-TINF22B6/DHBWhub/blob/master/docs/diagrams/UsecaseEndUser.drawio.png
- Landing-Page-Design on Figma, 24.10.2023:\
  https://www.figma.com/file/n6GgzaugPmNSt1OF1RBfZJ/DHBWhub?type=design&mode=design&t=8058pcoXIPrzWU3J-0
- ER-Diagram Rough first version, 24.10.2023:\
  https://github.com/SE-TINF22B6/DHBWhub/blob/master/docs/diagrams/ER-DiagramFirstVersion.drawio.png
- User-Activity-Diagram for signing in, 26.10.2023:\
  https://github.com/SE-TINF22B6/DHBWhub/blob/master/docs/diagrams/UserActivityLogin.drawio.png
- User-Activity-Diagram for signing up, 26.10.2023:\
  https://github.com/SE-TINF22B6/DHBWhub/blob/master/docs/diagrams/UserActivityRegister.drawio.png
- User-Activity-Diagram for viewing post in thread-view, 26.10.2023:\
  https://github.com/SE-TINF22B6/DHBWhub/blob/master/docs/diagrams/UserActivityThreadView.drawio.png
- Sequence-Diagram for PostDetail-Creation, 31.10.2023:\
  https://github.com/SE-TINF22B6/DHBWhub/blob/master/docs/diagrams/CreatePostSequenceDiagram.drawio.png
- Sequence-Diagram for Logging in per Google API, 31.10.2023:\
  https://github.com/SE-TINF22B6/DHBWhub/blob/master/docs/diagrams/LoginSequenceDiagram.drawio.png
- User-Activity-Diagram updated, more refined version of the first draft, 01.11.2023:\
  https://github.com/SE-TINF22B6/DHBWhub/blob/master/docs/diagrams/UsecaseDiagramGeneral.drawio.png
- EER-Diagram for general classe, 15.11.2023:\
  https://github.com/SE-TINF22B6/DHBWhub/blob/master/docs/diagrams/er_diagram_general_overview.drawio.png
- Utility-Tree, 28.11.2023:\
  https://github.com/SE-TINF22B6/DHBWhub/blob/master/docs/diagrams/UtilityTree.drawio.png
- Link to all mockups in figma, 23.12.2023:\
  https://www.figma.com/file/n6GgzaugPmNSt1OF1RBfZJ/DHBWhub?type=design&mode=design&t=zP3nHfwEDQeziwMq-0
- Sequence-Diagram for Updating profile, 25.12.2023:\
  https://github.com/SE-TINF22B6/DHBWhub/blob/master/docs/diagrams/ProfilePersonalizationSequenceDiagram.drawio.png    
---

## 2. Functional requirements

### 2.1 Overview
#### 2.1.1 General use case
Our website is comparable to other famous sites like Quora or Reddit, but rather specialized for students of the DHBW-environment. In the following graph, we have
depicted the main use cases of our soon to be finished product. An unregistered user is able to either browse the homepage and its sections, create an user account
or directly sign in with an existing user account. After signing in, the user is able to participate in four main sections of our web application. 

These section are:
- Account Management,
- Website Activities,
- User Interaction,
- Administration.

The latter section is a rather special one which involves the administrators of this website and their interactions or responsibilities towards the end users.

>![UML-diagram](https://github.com/SE-TINF22B6/DHBWhub/blob/master/docs/diagrams/UsecaseDiagramGeneral.drawio.png)

#### 2.1.2 Important entities and classes
In order to better understand all the entities involved in our project, we have created the following ER-diagram to better visualize and represent every important
entity and its relations to other entities on a high level. 

>![EER-entities](https://github.com/SE-TINF22B6/DHBWhub/blob/master/docs/diagrams/er_diagram_general_overview.drawio.png)

- Account: Both administrators as well as our end-users need to create an account with the most basic information (username, email-address, password and optionally a profile picture)
- Administrator: Accounts which are responsible for maintaining our website and fix and interact with user issues
- Client User: Our end users; compared to administrators, they are able to personalize their biography (description and age) and may create/save posts and write comments
- Faculty/Course: Faculties are the rough divisions of the DHBW consisting of several courses in which the students study in
- Post: An end user is able to save, create, delete, like and share its own posts in a certain course or faculty area. A post consists of a title, description, timestamp, tags and optionally a picture.
- Comment: End users can write comments under any post. These can also be liked and even commented. If a post gets deleted, the comments will also get deleted. 
>TODO update
---

### 2.2 Use Cases
In the following subsection, we will depict the most important use cases of our project and display it visually.
  
#### 2.2.1 Activities without an user account
This section covers all the user activities that don't require an existing user account. Pages such as the FAQ or privacy policy will be handled in the chapter 2.2.3.  
  
#### 2.2.1.1 Browse Homepage
Every user is able to hop onto our page and view/browse the homepage without signing in on the first place. They may view the most liked or recently created posts, the calendar or thread display of events and also general pages such as the FAQ, contact, privacy policy, TOS and imprint pages. It is also allowed to search for other users, posts or tagged posts via a certain keyword in the search bar. However, for all the other features such as creating posts/comments or following specific users, a user has to create a user account by clicking on the sign-in/register button or may also sign in via 3rd party support (in our case Google).  

In the following image, you can see the homepage of our application:

>![Homepage](https://github.com/SE-TINF22B6/DHBWhub/assets/122597204/b1f8a88f-d83a-4148-95b1-b94fa8897ae9)

In the next one, the inability to like a post is displayed when a user is not signed in with the respective tooltip:

>![TooltipLogin](https://github.com/SE-TINF22B6/DHBWhub/assets/122597204/89daea29-1f52-4929-a69c-ab5de0b90cd6)

**Preconditions**:  
The end user has opened our application through [this link](https://www.dhbwhub.de/). 

**Postconditions**:  
The landing page is the starting point of our project which allows the navigation to all the other following functions and components of our project.

**Estimated efforts**: medium

**Linked user stories**: [Issue 23](https://github.com/SE-TINF22B6/DHBWhub/issues/23), [Issue 27](https://github.com/SE-TINF22B6/DHBWhub/issues/27)

#### 2.2.1.2 Account creation / Sign up
In order to use all of our important features, the end user needs to create a new account which is used for signing up in future sessions. Alternatively, 
signing in/up through 3rd-party applications e.g. Google is also possible but will be explained in further detail under section 2.2.1.3.2.
We have visualized the regiser process with the following user activity diagram.  
  
>![registration_sequence_diagram](https://github.com/SE-TINF22B6/DHBWhub/blob/master/docs/diagrams/UserActivityRegister.drawio.png)
  
Here are some additional information:
- You are able to cancel the process anytime.
- Choosing the 3rd party option will redirect you to the respective 3rd party site in which your credentials get validated.
- After creating your account, an email with a verification code will be send to your email address so that you are able to verify your account.
- The username needs to be unique as well as the email.
- The password gets encrypted in the backend for more safety measures.
   
Just like the homepage, our signup window needs to be simple and easy to understand. In the following pictures, you can see the current state
of our sign up dialog:
>![ModalRegisterWindow](https://github.com/SE-TINF22B6/DHBWhub/assets/122597204/b0875b16-a936-4351-9687-0d06e3430958)
  
After entering the email-address, a verification mail is send to the respective address:
>![EmailCode](https://github.com/SE-TINF22B6/DHBWhub/assets/122597204/6f79d7de-c5e0-4993-9056-f6cfb75f6d16)

Lastly, the user is able to sign up with a username and a password. The latter needs to be reentered for better conformation:
>![EnteringUsernameAndPassword](https://github.com/SE-TINF22B6/DHBWhub/assets/122597204/9dc9dcde-6acc-4037-a663-44829c405971)

**Preconditions**:  
The user needs a valid email address which can be used for the validation as well as for the creation of the new DHBWhub account itself. The sign-up window is located in the header and can be reached from everywhere on the website.

**Postconditions**:  
After a user is registered, it is allowed to sign in and use all the personalized functions of our project which will be explained in the next use cases.

**Estimated efforts**: medium-high

**Linked user stories**: [Issue 23](https://github.com/SE-TINF22B6/DHBWhub/issues/23), [Issue 27](https://github.com/SE-TINF22B6/DHBWhub/issues/27)

#### 2.2.1.3 Signing in
##### 2.2.1.3.1 Via a local account
If an end user has already created an account beforehand, it may use it to sign in and experience a more personalized application. The following sequence diagram depicts all the steps that are needed for entering and validating the user data but also the possible redirects:  
  
>![login_sequence_diagram](https://github.com/SE-TINF22B6/DHBWhub/blob/master/docs/diagrams/UserActivityLogin.drawio.png)
  
You can see the sign in window in the next image:

>![SignInWindow](https://github.com/SE-TINF22B6/DHBWhub/assets/122597204/68cd35ad-607b-4291-af2b-fb8a30a7b92a)

The user has to enter its credentials, e.g. the username and password, into the two fields and click on the 'Login' button. Both fields can't contain empty values. When entering the correct data, the user is redirected to the account management page. Otherwise, a 'Bad credentials'-notification will pop up in the bottom of the dialogue.  

**Preconditions**:  
The user needs an already existing local account and may reach the login window through the header of the page, which is always present. 

**Postconditions**:  
After a user is signed in, it is actually allowed to use all the personalized functions and use cases of our application.

**Estimated efforts**: medium-high

**Linked user stories**: [Issue 23](https://github.com/SE-TINF22B6/DHBWhub/issues/23), [Issue 27](https://github.com/SE-TINF22B6/DHBWhub/issues/27)

##### 2.2.2.3.2 Via 3rd-Party (Google)
We are also offering the sign in via Google OAuth for the users, who don't want to create a new account on our website and instead, use their existing Google account. The process is quite simple. In both sign up and sign in windows, the user is able to click on the 'Weiter mit Google'-button which you can see in the image below:

>![SignInViaGoogle](https://github.com/SE-TINF22B6/DHBWhub/assets/122597204/ae9ff39a-162d-443e-8f0c-ec8607db1c50)

The button click will redirect the user to Google, so that Google can validate the user credentials:

>![RedirectGoogle](https://github.com/SE-TINF22B6/DHBWhub/assets/122597204/c91cbdeb-f303-43ae-8f79-85f8c196938c)

Optionally, they can use 1-Click, which authorizes the user directly without redirecting it to google (if the account is correct):

>![Google1Click](https://github.com/SE-TINF22B6/DHBWhub/assets/122597204/7b98b3ba-c8ae-4e83-9dcf-693b3158b607)

**Preconditions**:  
The user needs an existing Google account, whose username or email is not already used within the DHBWhub application. 

**Postconditions**:  
Just the same like a normal sign in. However, there are some restrictions regarding the profile personalization of the user which will be explained in a later chapter. 

**Estimated efforts**: medium

**Linked user stories**: [Issue 23](https://github.com/SE-TINF22B6/DHBWhub/issues/23), [Issue 27](https://github.com/SE-TINF22B6/DHBWhub/issues/27)

#### 2.2.1.4 Using the search function
Users are able to search for posts, tagged posts or users via the search button and editing field which you can see in the image below:

>![SearchField](https://github.com/SE-TINF22B6/DHBWhub/assets/122597204/82c896af-df89-4b0b-a047-45d6b60ba741)

After entering something in the search field and clicking on the search button, all the result posts will be displayed first, that contain the entered keyword:

>![SearchResult](https://github.com/SE-TINF22B6/DHBWhub/assets/122597204/109603b1-f6d2-4b36-8b86-54cbb2b2fffe)
  
If the user wants to search for posts with a certain tag or for users with a specific name, they can enter the keyword into the search field again and choose one of the search options on the left side:

>![SearchByTag](https://github.com/SE-TINF22B6/DHBWhub/assets/122597204/cb9d62a1-6274-45e4-938f-23e2c922e02f)

>![SearchByUser](https://github.com/SE-TINF22B6/DHBWhub/assets/122597204/2ea832a1-dadf-4397-9f74-5058d7881ad9)

The keyword is case insensitive.

**Preconditions**:  
The user doesn't need to be signed in to use this function. The search bar can directly be used on the homepage and automatically redirects the user to the search page.  

**Postconditions**:  
Users can search for specific posts or users without the need to scroll through the entire homepage. 

**Estimated efforts**: high

**Linked user stories**: [Issue 23](https://github.com/SE-TINF22B6/DHBWhub/issues/23), [Issue 27](https://github.com/SE-TINF22B6/DHBWhub/issues/27)

#### 2.2.1.5 Viewing user profiles
User profiles can be viewed by everyone since we are mostly a public platform. The navigation either happens through the names/profile pictures of the respective users on posts/comments or through the search bar. After the redirect, the user page will be shown, depicting personalized informations of the user such as the username, profile picture, follower amount, course name, age or profile description. There is also the possibility to follow the user when the end user is signed in.
You can see one example of the user page in the image below:

>![UserProfile](https://github.com/SE-TINF22B6/DHBWhub/assets/122597204/7a051f1d-b5e9-432e-a01c-4bfafbf5d6bb)

**Preconditions**:  
The user needs to exist in the database. Following the user is only allowed, when the the following user is signed in. Otherwise, nothing will happen.

**Postconditions**:  
You have now enlargened your knowledge on one or several other users and thus, enhanced your consciousness on your social environment.  

**Estimated efforts**: low-medium

**Linked user stories**: [Issue 23](https://github.com/SE-TINF22B6/DHBWhub/issues/23), [Issue 27](https://github.com/SE-TINF22B6/DHBWhub/issues/27)

#### 2.2.1.6 Viewing events and calendar
On the homepage, the next five events will be displayed on the right side with information such as the date, title, location and the event tags.
  
>![EventHomePage](https://github.com/SE-TINF22B6/DHBWhub/assets/122597204/d70c66f6-3b44-4537-b129-c46dc73ade94)

After clicking on the event, the user will be redirected to the corresponding event post. Latter also contains additional information such as the start- and enddate, description and the location displayed via OpenStreetMap, as you can see in the image below:
  
>![EventThreadView](https://github.com/SE-TINF22B6/DHBWhub/assets/122597204/131d0ef3-9f63-4fee-b6d4-249e78f53708)

However, if you want to view all the events without number restrictions, you can use the calendar. The calendar can display all the events in a monthly, weekly or daily view. It is also possible to view only the events that are scheduled within a certain day. Clicking on the events will redirect the user to the corresponding Eventpost!  
Monthly view:  
>![CalendarView](https://github.com/SE-TINF22B6/DHBWhub/assets/122597204/46dd0a6b-a1f5-4948-8930-ea8f028b3b2c)
  
Weekly view:
  
>![WeeklyView](https://github.com/SE-TINF22B6/DHBWhub/assets/122597204/310042ce-b80e-4d5d-9b1f-20392b475bec)
  
Daily view:
  
>![DailyView](https://github.com/SE-TINF22B6/DHBWhub/assets/122597204/8c594e0a-04c8-4283-828f-d968db292b2d)
  
Agenda view:
  
>![Agenda](https://github.com/SE-TINF22B6/DHBWhub/assets/122597204/26ae71e7-648a-4654-9e24-df806bdb74d6)

**Preconditions**:  
The user can navigate to the next five events through the homepage. For the other ones, the calendar icon needs to be clicked so that the user gets redirected to the calendar component, where all the events can be found.

**Postconditions**:  
Users can learn more about the next events and get the chance to socialize even more. 

**Estimated efforts**: high

**Linked user stories**: [Issue 23](https://github.com/SE-TINF22B6/DHBWhub/issues/23), [Issue 27](https://github.com/SE-TINF22B6/DHBWhub/issues/27)

#### 2.2.1.7 View popular tags
The seven most popular tags are displayes on the left side of the homepage.

>![PopularTags](https://github.com/SE-TINF22B6/DHBWhub/assets/122597204/4dc73cae-1015-4b7c-add3-14e879c2166f)
  
After clicking on one tag, the homepage will show you all the posts that contain the exact tag!

>![PostsWithTag](https://github.com/SE-TINF22B6/DHBWhub/assets/122597204/b200c9c6-73e5-4f7a-b654-84191d826c1b)
  
**Preconditions**:  
The popular tags are only displayed on the homepage. You also don't need to be signed in.

**Postconditions**:  
Posts with a certain tag can be found definitely easier and faster. 

**Estimated efforts**: medium

**Linked user stories**: [Issue 23](https://github.com/SE-TINF22B6/DHBWhub/issues/23), [Issue 27](https://github.com/SE-TINF22B6/DHBWhub/issues/27)

---

#### 2.2.2 Activities with an user account
This section covers all the activities that are allowed when the user is signed in.
  
#### 2.2.2.1 Profile personalization
After creating a user account, each user is able to personalize its profile such as the age, course name, description and profile picture. Other parameters like the username, email address or password are also changable. However, there are some restrictions that need to be considered:
- Usernames need to be unique, a user can't change to a username that already belongs to another user.
- The same applies to the email address.
- Before changing the password, the user needs to enter the old password. After the correct validation, the new password needs to entered (twice).

In the following graph, you can see the whole process with the condition, that the user enters valid data:
![Seq-diagram](https://github.com/SE-TINF22B6/DHBWhub/blob/master/docs/diagrams/ProfilePersonalizationSequenceDiagram.drawio.png?raw=true)
 
The next image also depicts the profile page with example data:

![ProfilePage](https://github.com/SE-TINF22B6/DHBWhub/assets/122597204/96fabcbf-fcfd-4ab2-acf6-2767d210503d)
  
**Preconditions**:  
User needs to be signed in and click on the own profile on the top right corner.

**Postconditions**:  
Users can personalize their own profile as they please.

**Estimated efforts**: medium

**Linked user stories**: [Issue 66](https://github.com/SE-TINF22B6/DHBWhub/issues/66)

#### 2.2.2.2 Create posts
One of our most essential features for end users is the ability to create posts. Hereby, the signed in user can click on the 'Create post' button and can insert a necessary title, a description and an optional picture into the editing field. You can see the flow of sequences in the next sequence diagram:
>![post_creation_seq](https://github.com/SE-TINF22B6/DHBWhub/blob/master/docs/diagrams/CreatePostSequenceDiagram.drawio.png)

Here is how it looks like on the web-application:
  
>![OpenEditWindow](https://github.com/SE-TINF22B6/DHBWhub/assets/122597204/3897e4ba-d26b-4656-9a0a-0024ef2e2a16)

>![TestPost](https://github.com/SE-TINF22B6/DHBWhub/assets/122597204/b51c2ad8-e61f-469e-9e94-33db234b2949)
  
Posts can also be displayed in the thread view where you can see all the corresponding comments!
  
>![PostThreadView](https://github.com/SE-TINF22B6/DHBWhub/assets/122597204/28a1ec59-8d5a-4d24-85b5-7b6e7bcb7e45)

**Preconditions**:  
The user needs to be signed in and on the homepage. The 'Create post'-button or the edit field needs to be clicked once to open the post editor. There are also restrictions regarding the post itself. Title and desription can't be empty, Tags must start with a capital letter and have a limit of 12 characters and the image limit is one.  

**Postconditions**:  
The post can be viewed in the thread view and allows other users to like or comment it.

**Estimated efforts**: medium-high

**Linked user stories**: [Issue 67](https://github.com/SE-TINF22B6/DHBWhub/issues/67)

#### 2.2.2.3 Save posts
All signed in users are able to save several existing posts from other users, which they can view all the time, as long the posts still exist. In order to save a post, the user has to click on the three dots which appear on the upper right side of each post and afterwards choose the option 'Save post'. This can be done not only in the thread view but also on the homepage. All the saved ones (or at least a certain amount) can be found under the 'Saved posts'-component on the lower left side of the homepage.  
The 'Save post'-button on the post in the homepage view:

>![SavePostHomepage](https://github.com/SE-TINF22B6/DHBWhub/assets/122597204/7bd2e287-4973-4357-8da9-e181df49d654)
  
The notification when the save was successful:

>![SuccessfullSave](https://github.com/SE-TINF22B6/DHBWhub/assets/122597204/22811191-05c3-4bbf-97cb-02da6127256c)

Now it is visible on the homepage on the bottom left side. Clicking on it will redirect the user to the post:
>![VisibleOnHomepage](https://github.com/SE-TINF22B6/DHBWhub/assets/122597204/ecb3097c-7dd2-4372-b174-ed771d4ac807)

The user can unsave it now if they feel like it:
>![grafik](https://github.com/SE-TINF22B6/DHBWhub/assets/122597204/5fa4a5fa-9a8c-4c4d-bb34-0aeacde0c6f0)

**Preconditions**:  
The user has to be signed in and there must be at least one existing post. The saving and unsaving actions can happen on the homepage or the thread view of the posts

**Postconditions**:  
Marked/Saved posts can be viewed at any time.

**Estimated efforts**: medium

**Linked user stories**: [Issue 23](https://github.com/SE-TINF22B6/DHBWhub/issues/262)

##### 2.2.2.4 Likes and comments
Users can interact with posts either through likes/dislikes or the creation of comments. Comments can be created in the thread view from not only posts, but also event posts! Hereby, the user can enter their texts in the editable field under a post and click the 'Reply'-button as you can see in the image below:

>![CreateComments](https://github.com/SE-TINF22B6/DHBWhub/assets/122597204/a43bef0d-7d02-4c4d-beb0-eb191e71bbd7)

After the successfull creation:
  
>![SuccessfullComment](https://github.com/SE-TINF22B6/DHBWhub/assets/122597204/70eb36c3-c6af-4681-af1f-72afdf3bc81c)
  
Now both the post and the comments can be liked by the signed in user!

>![SuccessfullPost](https://github.com/SE-TINF22B6/DHBWhub/assets/122597204/12f9128e-c118-46ea-94e9-8af3760d8c66)
  
The signed in user can also like his own posts and comments - but we won't get into the ethical implications of these actions...

**Preconditions**:  
For the creation of comments, the user has to be logged in and at least one post must exist obviously. This also applies to the ability to like or dislike likable components.

**Postconditions**:  
Interactions between users will get more lively. The liked components are saved for the current and next sessions to avoid inconsistencies.

**Estimated efforts**: medium.

**Linked user stories**: [Issue 67](https://github.com/SE-TINF22B6/DHBWhub/issues/67)
   
##### 2.2.2.5 Checking notifications
When certain events happen, the affected users get corresponding notifications on their feed. To view these notifications, the user has to click on the bell button. A red mark above the bell indicates, that there are new notifications which haven't been deleted yet. Thus, after clicking on the bell, the user will get a small list with new notifications of the following type:
- User/s has/have liked your post.
- User/s has/have commented your post
- User/s has/have liked your comment.
- User/s has/have liked your event comment.
- User/s started following you.

The bell button looks like this:
  
>![Bell button](https://github.com/SE-TINF22B6/DHBWhub/assets/122597204/be87f75c-49f7-4142-863f-ea07e9870869)
  
After clicking on the button, the user can see a list like this:
  
>![NotificationList](https://github.com/SE-TINF22B6/DHBWhub/assets/122597204/1463822f-1885-4381-93b4-f5ebfbe5e104)
  
Clicking on an item in the list will redirect you to the respective component e.g. the post, event post, user profile or friendship page. You can also click on the cross button to delete a notification. Before that, the user needs to confirm that the notification should be removed from the list.
   
>![Remove Notification](https://github.com/SE-TINF22B6/DHBWhub/assets/122597204/404dbddc-3e28-4e5b-b4ac-fd6c48401b93)

**Preconditions**:  
The user has to be signed in to receive or manage their notifications.

**Postconditions**:  
Users are able to receive or see updates even faster, since they are mentioned directly

**Estimated efforts**: low-medium

**Linked user stories**: [Issue 264](https://github.com/SE-TINF22B6/DHBWhub/issues/264)

##### 2.2.5.2 Friendlist administration
In order to receive posts directly in the own feed from specific persons or organizations, users are able to establish friendships through friend requests. The user has to search the profile of the other user and needs to click on 'Send friend request'. After clicking the button, the lable will change to 'Friend request sent'. The other person will then receive a notification which states, which person has sent a friend request. It can either be declined, ignored or accepted. When latter happens, the lable from before simply changes to 'Friends'. 

**Preconditions**:  
All the involved users need active accounts. The search function also need to be implemented first.

**Postconditions**:  
The personal feed involves posts from friends. It is also possible to implement a filter, which only shows posts from the users the main user follows.

**Estimated efforts**: medium-high

**Linked user stories**: [Issue 265](https://github.com/SE-TINF22B6/DHBWhub/issues/265)

---

#### 2.2.6 Administrative activities
The last section covers all the use cases that happen between the end users and the administrators.

##### 2.2.6.1 View legal notice and FAQ page 
Users should be able to look into our privacy policy or FAQ page, whenever questions or problems occur, involving either the legality of content or simply answering the most important questions regarding the main functions of this website. There must also be a terms of condition before signing up to the website, so that the users are able to read all the legal conditions before creating an account.

> TODO mockup

**Preconditions**:  
The homepage and the sign up form needs to be implemented.

**Postconditions**:  
Users can access both pages anytime.

**Estimated efforts**: low

**Linked user stories**: [Issue 88]((https://github.com/SE-TINF22B6/DHBWhub/issues/88), [Issue 91](https://github.com/SE-TINF22B6/DHBWhub/issues/91)


##### 2.2.6.2 Report issues/requests to administrators 
If users experience trouble or see/experience misuse in any form on our website, they are able to fill a contact form and send it to our administration team. The contact form can be achieved from the homepage through clicking on the 'Contact'-button. Afterwards, they have to fill in their e-mail address, their name and the message which contains the essential points about the problem. Lastly, the user only needs to click on the 'Send'-button to finish the process. You can see the form in the image below:

>![grafik](https://github.com/SE-TINF22B6/DHBWhub/assets/122597204/a803be28-fb3c-41ee-93bb-1b6145a407df)

**Preconditions**:  
The homepage needs to be implemented.

**Postconditions**:  
Interactions between end users and administrators are established, even when not signed in.

**Estimated efforts**: low-medium

**Linked user stories**: [Issue 266](https://github.com/SE-TINF22B6/DHBWhub/issues/266)


---

## 3. Nonfunctional requirements
In the following diagram, you can see the top 3 quality attributes of our project. These are usability, modifiability and performance. We will take a closer and more detailed look at them 
in the next subchapters!
>![utiltyTree](https://github.com/SE-TINF22B6/DHBWhub/blob/master/docs/diagrams/UtilityTree.drawio.png?raw=true)

---

### 3.1 Usability
In terms of usability, the following factors play a very important role.

#### 3.1.1 Quick Learning
It may be crucial to us that every user is able to participate or use every function and explores all the possibilities our project has to offer. Yet, it is even more important to create a structure and visual overview that doesn't deter new users from using all the features to its fullest extent.  
There are some nice ways to combat this problem:
- Using a modular webdesign, in which the most important things are displayed in the center. Furthermore, other important aspects such as the navigation bar or the account settings are located at places which are usually used by other social media applications.
- Providing a documentation or FAQ site which states the most important use cases in a concise yet understandable manner.
  
#### 3.1.2 Visual feedback
If a user wants to create a post/comment or even wants to change his personal account information, he needs to get a visible notification whether his commits/changes have been successfully applied or if some errors occured during the process. This can be solved by providing temporary textblocks which inform the user whether the changes have been applied or not. One tricky part about this is the amount of information we convey to the user. We have to apply the minimality principle (give away as less system/application information as possible to the casual user) while also guiding the user to fix occuring problems. 

---

### 3.2 Modifiability
In terms of modifiabilty, the following factors play a very important role. This aspect rather applies to the developers of this project. 

#### 3.2.1 Modularity
Modularity is one of the core designs of our project. The structure/architekture is created in a way, in which we can develop newer and newer features without breaking the existing design through modules. This also satisfies the user, since he doesn't have to adapt constantly to new designs and doesn't need to relearn the updated navigations. In order to achieve modularity, we need following aspects:
- Aleady established CI/CD-pipeline which covers all the important areas to maintain the stability of the existing system.
- Webdesign, which is composed of many different components. This point is described in more detail in 3.2.2 .

#### 3.2.2 Component Design
One way to support modularity is through an atomic component design. This means that the website is structured into several different components which consist of other components until the basic ones are used. With this design pattern, we are encouraging the developers to reuse already existing components while also shortening the development time and reducing the need to reinvent the wheel twice. 

---

### 3.3 Performance
In terms of performance, the following factors play a very important role.

#### 3.3.1 Response time
Our end users shouldn't need to wait long for the response whether their data transactions have been transmitted succesfully or some error occured along the way. The response time captures lots of instances, these are the crucial ones:
- Rerendering of components shouldn't last long at all.
- The amount of time, a response needs for a single request should be limited by a maximum of 10 seconds. One way to prevent high latency is using cache/buffer storage or external views in the database, which already arranged the needed information beforehand so that the response may happen immediately.

#### 3.3.2 Asynchronous loading
If a user is logged in and wants to use features later on which weren't initially requested, the response time and rerendering of the page shouldn't take a long time. In order to prevent high latency,
the local storage of other possible data should already happen in the background while the user is using other functionalities. Therefore, the already stored data can be used if the user eventually requests them.

---

### 3.4 Security
Although we haven't included this aspect in our top 3 quality attributes, it still is a crucial point regarding our web application since we are dealing with lots of personal user information and credentials. In order to store this intelligence conforming to local and regional laws, we have to achieve the following steps:
- Restricting the personal user information to a minimum.
- Storing sensitive data encrypted in the database such as hashed passwords.
- Enforcing users to apply important security standards.

---

### 4. Technical constraints
> Specify any major constraints, assumptions or dependencies, e.g., any restrictions about which type of server to use, which type of open source license must be complied, etc. 
