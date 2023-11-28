# Software Requirements Specification - DHBWhub
- [Software Requirements Specification - DHBWhub](#software-requirements-specification---dhbwhub)
   * [1. Introduction](#1-introduction)
      + [1.1 Overview](#11-overview)
      + [1.2 Scope](#12-scope)
      + [1.3 Definitions, Acronyms and Abbreviations](#13-definitions-acronyms-and-abbreviations)
      + [1.4 References](#14-references)
   * [2. Functional requirements](#2-functional-requirements)
      + [2.1 Overview](#21-overview)
         - [2.1.1 General use case](#211-general-use-case)
         - [2.1.2 Important entities and classes](#212-important-entities-and-classes)
      + [2.2 Use Cases](#22-use-cases)
         - [2.2.1 Browse Homepage](#221-browse-homepage)
         - [2.2.2 Creating account/Signing in](#222-creating-accountsigning-in)
         - [2.2.3 Account Management](#223-account-management)
            * [2.2.3.1 Profile personalization](#2231-profile-personalization)
            * [2.2.3.2 Managing 3rd-party login](#2232-managing-3rd-party-login)
         - [2.2.4 Website activities (individual)](#224-website-activities-individual)
            * [2.2.4.1 Browsing personalized Feed](#2241-browsing-personalized-feed)
            * [2.2.4.2 Create posts](#2242-create-posts)
            * [2.2.4.3 Save posts](#2243-save-posts)
            * [2.2.4.4 Calendar customization](#2244-calendar-customization)
            * [2.2.4.5 View Events](#2245-view-events)
            * [2.2.4.6 Checking notifications](#2246-checking-notifications)
         - [2.2.5 User Interaction](#225-user-interaction)
            * [2.2.5.1 Post interaction](#2251-post-interaction)
            * [2.2.5.2 Friendlist administration](#2252-friendlist-administration)
         - [2.2.6 Administrative activities](#226-administrative-activities)
            * [2.2.6.1 View legal notice and FAQ page ](#2261-view-legal-notice-and-faq-page)
            * [2.2.6.2 Report issues/requests to administrators ](#2262-report-issuesrequests-to-administrators)
   * [3. Nonfunctional requirements](#3-nonfunctional-requirements)
      + [3.1 Usability](#31-usability)
      + [3.2 Security](#32-security)
      + [4. Technical constraints](#4-technical-constraints)
     
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
- 
>  A complete list of all documents referenced. Each document should be identified by title, date, and publishing organization. You can also insert hyperlinks, in order to open the references conviniently.

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

**Link to mockup**: https://www.figma.com/file/n6GgzaugPmNSt1OF1RBfZJ/DHBWhub?type=design&mode=design&t=8058pcoXIPrzWU3J-0

  
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

**Link to mockup**: https://www.figma.com/file/n6GgzaugPmNSt1OF1RBfZJ/DHBWhub?type=design&mode=design&t=8058pcoXIPrzWU3J-0

---

#### 2.2.3 Account Management
##### 2.2.3.1 Profile personalization
- As a user, I want to be able to change my own settings to personalize my profile, upload profile pictures or change other crucial personal details.

- user stories: [Issue 66](https://github.com/SE-TINF22B6/DHBWhub/issues/66)
- Link to mockup: n.A.

- Preconditions: The landing page as well as the user accounts have to be fully functioning in order to change it.
- Postconditions: Users can customize their own profile.
- Estimated efforts: medium
> Other Use Cases will be added when discussed properly

##### 2.2.3.2 Managing 3rd-party login

---

#### 2.2.4 Website activities (individual)
##### 2.2.4.1 Browsing personalized Feed
##### 2.2.4.2 Create posts
##### 2.2.4.3 Save posts
##### 2.2.4.4 Calendar customization
##### 2.2.4.5 View Events
##### 2.2.4.6 Checking notifications

--- 

#### 2.2.5 User Interaction
##### 2.2.5.1 Post interaction
- As a user, I want to be able to view a Thread/Question/PostDetail with its comments on a single page. I should also be able to interact with the post on various ways like commenting, liking or sharing. 

- user stories: [Issue 67](https://github.com/SE-TINF22B6/DHBWhub/issues/67)
- Link to mockup: n.A.

- This sequence diagram showcases how the user might create posts. At the start, the user is already logged in:
>![post_creation_seq](https://github.com/SE-TINF22B6/DHBWhub/blob/master/docs/diagrams/CreatePostSequenceDiagram.drawio.png)

- Preconditions: Sample Threads and a mockup have to be created, as well as the database schema.
- Postconditions: Users can view single Threads/ Posts with all of their interactions.
- Estimated efforts: medium
##### 2.2.5.2 Friendlist administration

---

#### 2.2.6 Administrative activities
##### 2.2.6.1 View legal notice and FAQ page 
##### 2.2.6.2 Report issues/requests to administrators 

---

## 3. Nonfunctional requirements

### 3.1 Usability
It is very important to provide the users a smooth and user-friendly experience. Thus, it is crucial during the entire development process, to think of simplifying user interactions.
Schemas: n.A.

### 3.2 Security
In order to establish a secure website and protect the users against unlawful use of their personal data, a plan for the authentication and authorization process is necessary.
Hereby, we have to design a secure authentication system and define authorization rules through e.g. user roles.
Schema: n.A.

> [!IMPORTANT]  
> Categories: Usability, Reliability, Performance, Efficiency, Integrity, Maintainability, Flexibility, Testability, Reusability, Security.  

### 4. Technical constraints
> Specify any major constraints, assumptions or dependencies, e.g., any restrictions about which type of server to use, which type of open source license must be complied, etc. 
