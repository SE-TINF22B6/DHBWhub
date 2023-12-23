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

- Account: Both administrators as well as our end-users need to create an account with the most basic information (username, email-adress, password and optionally a profile picture)
- Administrator: Accounts which are responsible for maintaining our website and fix and interact with user issues
- Client User: Our end users; compared to administrators, they are able to personalize their biography (description and age) and may create/save posts and write comments
- Faculty/Course: Faculties are the rough divisions of the DHBW consisting of several courses in which the students study in
- Post: An end user is able to save, create, delete, like and share its own posts in a certain course or faculty area. A post consists of a title, description, timestamp, tags and optionally a picture.
- Comment: End users can write comments under any post. These can also be liked and even commented. If a post gets deleted, the comments will also get deleted. 

---

### 2.2 Use Cases
In the following subsection, we will depict the most important use cases of our project and display it visually if necessary and possible.

#### 2.2.1 Browse Homepage
Every user is able to hop onto our page and view/browse the homepage without signing in on the first place. They may view the most interacted posts first and can also see the FAQ, contact 
and privacy policy page. In order to use all the other features and get a personalized feed, a user has to create a user account by clicking on the sign-in/register button or can sign in 
via 3rd party support.  
Our envisioned design for our homepage looks like this:

>![mockup_homepage](https://github.com/SE-TINF22B6/DHBWhub/assets/122597204/eb86aedc-aa41-4968-8e17-cd5aa55987c2)

**Preconditions**:  
First of all, a mockup has to be created to put our vision of an appealing, modern looking and easy-to-navigate landing page into action. The focus should lie on user intuition and a rather 
simplified design which can be extended step by step with new modules/components. After the mockup, the actual implementation should occur in React.

**Postconditions**:  
The landing page is the starting point of our project which allows the navigation to all the other following functions and components of our project.

**Estimated efforts**: medium

**Linked user stories**: [Issue 23](https://github.com/SE-TINF22B6/DHBWhub/issues/23), [Issue 27](https://github.com/SE-TINF22B6/DHBWhub/issues/27)

    
#### 2.2.2 Creating account/Signing in
In order to use all of our important features, the end user needs to create a new account which is used for signing up in future sessions. Alternatively, 
signing in/up through 3rd-party applications e.g. Google is also possible but will be explained in further detail under section 2.2.3.2.
We have visualized the login and regiser process with the following user activity diagrams.  
Here are some key takeaways:
- You are able to cancel the process anytime
- Choosing the 3rd party option will redirect you to the respective 3rd party site in which your credentials get validated
- After creating your account, an email with a verification code will be send for verifying your account
   
>![login_sequence_diagram](https://github.com/SE-TINF22B6/DHBWhub/blob/master/docs/diagrams/UserActivityLogin.drawio.png)
>![registration_sequence_diagram](https://github.com/SE-TINF22B6/DHBWhub/blob/master/docs/diagrams/UserActivityRegister.drawio.png)

Just like the homepage, our login/signup window needs to be simple and easy to understand. In the following pictures, you can see the current state
of our login and signup dialog:
>![mockup_login](https://github.com/SE-TINF22B6/DHBWhub/assets/122597204/c32bd552-6000-46c5-9fed-cfa29703f367)
>![mockup_registration](https://github.com/SE-TINF22B6/DHBWhub/assets/122597204/4837e5fa-7d30-46f1-937a-05aa39d16e9d)
 

**Preconditions**:  
Before accessing the login/signup dialog, the landing page has to exist and should be navigatable. Furthermore, a mockup has to be planned before implementing
each window as well as the validation from the database server.

**Postconditions**:  
After a user has signed in, he is able to use all the personalized functions of our project which will be explained in the next use cases.

**Estimated efforts**: medium-high

**Linked user stories**: [Issue 23](https://github.com/SE-TINF22B6/DHBWhub/issues/23), [Issue 27](https://github.com/SE-TINF22B6/DHBWhub/issues/27)


---

#### 2.2.3 Account Management
In this section, we will take a closer look at all the use cases concerning the management of the end user accounts/profiles.

##### 2.2.3.1 Profile personalization
After creating a user account, each user is able to personalize its profile. Some things are mandatory such as the course name, in order to provide the users a more appealing feed with appropriate posts by default. However, changing your description, age and profile picture is optional and helps propelling user-to-user interaction, which you can find under the section in 2.2.5.2. After the adjustment of the previously mentioned attributes, the users can permanently store these by clicking on the 'save edits' button.

**Preconditions**:  
Before accessing the account managament page, the homepage has to be implemented as well as the functioning sign in/up process.

**Postconditions**:  
Users can access a page or a form, which contains all the important and personal account information.

**Estimated efforts**: medium-high

**Linked user stories**: [Issue 66](https://github.com/SE-TINF22B6/DHBWhub/issues/66)


##### 2.2.3.2 Managing 3rd-party login
It is possible to sign up via 3rd-party-access such as depicted in the previous use cases. However, the users also have the chance to change and manage their third party logins such as connecting or disconnecting the own Google, Microsoft or Apple account. 

**Preconditions**:  
Before accessing the account managament page, the homepage has to be implemented as well as the functioning sign in/up process.

**Postconditions**:  
Users can access a page or a form, in which they can configure their 3rd-party settings (focus mainly on connecting accounts).

**Estimated efforts**: medium

**Linked user stories**: [Issue 36](https://github.com/SE-TINF22B6/DHBWhub/issues/36)


---

#### 2.2.4 Website activities (individual)
In this section, we will take a closer look at the use cases concerning all the individual website activities without user-to-user interaction, which will be displayed in section 2.2.5 in more detail.

##### 2.2.4.1 Browsing personalized Feed
After a user has signed in, the initial homepage will contain a more personalized feed. This means, that mainly posts from friends or the corresponding course, in which the user takes part in, are displayed on the main page. The user is also allowed to interact with these posts (with the minimalist view on the homepage) to some extent, such as liking, creating links or directly messaging the link through different channels as you can see in the following images:
>![grafik](https://github.com/SE-TINF22B6/DHBWhub/assets/122597204/64fc04c0-4158-4567-8a6f-0547f4dd85ab)
>![grafik](https://github.com/SE-TINF22B6/DHBWhub/assets/122597204/69205b64-a250-4c63-9177-d78566b3f0f7)

**Preconditions**:  
Users should be able to access the homepage and succesfully sign in.

**Postconditions**:  
The Homepage has a more personal appeal and surface-level interactions are allowed too.

**Estimated efforts**: medium-high

**Linked user stories**: [Issue 27](https://github.com/SE-TINF22B6/DHBWhub/issues/27)


##### 2.2.4.2 Create posts
One of our most essential features for end users is the ability to create posts. Hereby, the users can click on the 'Create post' button and can insert a necessary headline, a description and an optional picture, which is the main content. You must also add at least one tag, so that other users can find this post when searching for it or similar topics. After the post has been created, you can click it to display the thread view. You can see the flow of sequences in the next sequence diagram:
>![post_creation_seq](https://github.com/SE-TINF22B6/DHBWhub/blob/master/docs/diagrams/CreatePostSequenceDiagram.drawio.png)
  
Here is how it looks like on the web-application:
>![grafik](https://github.com/SE-TINF22B6/DHBWhub/assets/122597204/942b96bf-49e7-45d6-9e80-8f4cf4390ab5)
>![grafik](https://github.com/SE-TINF22B6/DHBWhub/assets/122597204/7e92a46a-d4d8-44f2-9e8f-c167ec816f2e)

**Preconditions**:  
The user has to be signed in and on the homepage.

**Postconditions**:  
Other users are able to search and interact with the newly created post.

**Estimated efforts**: medium-high

**Linked user stories**: [Issue 67](https://github.com/SE-TINF22B6/DHBWhub/issues/67)


##### 2.2.4.3 Save posts
All users are able to save several existing posts from other users, which they can view all the time, as long the posts still exist. In order to save a post, the user has to click on the three dots which appear on the upper right side of each post and afterwards choose the option 'Save post'. This can be done not only in the thread view but also on the homepage. All the saved ones (or at least a certain amount) can be found under the 'Saved posts'-component on the lower left side of the homepage. 
>![grafik](https://github.com/SE-TINF22B6/DHBWhub/assets/122597204/dea55779-b9fb-4af8-858d-5a5fa97d09fa)

**Preconditions**:  
The user has to be signed in and there must be at least one existing post.

**Postconditions**:  
Marked/Saved posts can be viewed at any time.

**Estimated efforts**: medium

**Linked user stories**: [Issue 23](https://github.com/SE-TINF22B6/DHBWhub/issues/262)

##### 2.2.4.4 View calendar and events
Each user has access to a global calendar which is filled with events that could be interesting for students or teachers. Each event has a date and a time slot. In order to view these events, the users need to click the calendar symbol, when they are on the homepage. The calendar has a monthly and a weekly view with hourwise segmentation as you can see in the following images:
>![grafik](https://github.com/SE-TINF22B6/DHBWhub/assets/122597204/1b3887d1-42ed-43f1-83be-41f32c4d4794)
>![grafik](https://github.com/SE-TINF22B6/DHBWhub/assets/122597204/dcf8cf67-34d8-45d7-9fae-9c72de8ce8f4)

If you click on an event, it will redirect you to a post with more specific information and an optional map. The next image shows a rough example on how an event post might look like:
>![grafik](https://github.com/SE-TINF22B6/DHBWhub/assets/122597204/994c7088-e0de-4022-90b3-11ef66e9cf53)

**Preconditions**:  
The homepage has to be implemented first, however no sign-in is necessary for viewing all the events since it is centralized. 

**Postconditions**:  
It's possible for users to interact with event posts.

**Estimated efforts**: medium-high

**Linked user stories**: [Issue 263](https://github.com/SE-TINF22B6/DHBWhub/issues/263)


##### 2.2.4.5 Checking notifications
When certain events happen, the affected users get corresponding notifications on their feed. To view these notifications, the user has to click on the bell button. A red mark above the bell indicates, that there are new notifications which haven't been read yet. Thus, after clicking on the bell, the user will get a small list with new notifications e.g. comments under a post, mentions from other users and several more. After clicking on a certain notification, it will be marked as 'read' and will redirect to the respective location.    

> TODO Mockup

**Preconditions**:  
The user has to be signed in to receive or manage their notifications.

**Postconditions**:  
Users are able to receive or see updates even faster, since they are mentioned directly

**Estimated efforts**: low-medium

**Linked user stories**: [Issue 264](https://github.com/SE-TINF22B6/DHBWhub/issues/264)


##### 2.2.4.6 Search bar
When searching for posts with specific content, tags or even for other users, every user is able to use the search bar for this purpose. When entering content into the inputfield, users can choose whether they want to search for other users or for posts and will be redirected correspondingly.

> TODO Mockup

**Preconditions**:  
The user has to be signed in to use this feature and needs to be on the homepage.

**Postconditions**:  
Users can find content or persons even faster than before.

**Estimated efforts**: high

**Linked user stories**: [Issue 85](https://github.com/SE-TINF22B6/DHBWhub/issues/85)

--- 

#### 2.2.5 User Interaction
In the following section, we'll take a closer look at all the ways users can interact with other users.

##### 2.2.5.1 Post interaction
After clicking on a post in the user's feed, it will be displayed in a more focussed view (thread view). Latter allows every user to interact with this post. Interactions could be for example liking, sharing and saving this post. However, our other main feature is the option to comment a post, so that discussions can thrive. Similiar to a post, a user only needs to insert his comment into the input box below the main post and clicks the 'Reply'-button afterwards. In the next image, you can see the input box as well as some examples on how a comment looks like:
>![grafik](https://github.com/SE-TINF22B6/DHBWhub/assets/122597204/82e26751-c6bf-4084-952b-d1779690c78e)

**Preconditions**:  
User has to be logged in and at least one post must exist obviously.

**Postconditions**:  
Interactions between users will get more lively.

**Estimated efforts**: medium-high.

**Linked user stories**: [Issue 67](https://github.com/SE-TINF22B6/DHBWhub/issues/67)
   
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
If users experience trouble or see/experience misuse in any form on our website, they are able to fill a contact form and send it to our administration team. The contact form can be achieved from the homepage through clicking on the 'Contact'-button. Afterwards, they have to fill in their e-mail adress, their name and the message which contains the essential points about the problem. Lastly, the user only needs to click on the 'Send'-button to finish the process. You can see the form in the image below:

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
