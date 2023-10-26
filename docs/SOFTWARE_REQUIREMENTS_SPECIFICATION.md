# DHBWhub
## Software Requirements Specification
### 1. Introduction
#### 1.1 Overview
We are a DHBW-internal forum where users can ask questions and post DHBW-related content. The site is divided into several sections which represent the faculty and the courses of the DHBW. Besided, users can befriend each other so that socialicizing with other students gets even easier.
#### 1.2 Scope
This Software Requirements Specification (SRS) document covers the complete system of DHBWHub. It encompasses both functional and non-functional requirements necessary for the successful development, deployment, and operation of the platform. The document aims to provide a comprehensive understanding of the system's architecture, features, and limitations.
#### 1.3 Definitions, Acronyms and Abbreviations
> TODO.
#### 1.4 References
Documents:
- Rough Use-Case-Diagram for the End-User, 24.10.2023: https://github.com/SE-TINF22B6/DHBWhub/blob/master/docs/UseCaseEndUser.drawio.png
- Landing-Page-Desing on Figma, 24.10.2023: https://www.figma.com/file/n6GgzaugPmNSt1OF1RBfZJ/DHBWhub?type=design&mode=design&t=8058pcoXIPrzWU3J-0
- ER-Diagram Rough first version, 24.10.2023: https://github.com/SE-TINF22B6/DHBWhub/blob/master/docs/ER_Diagram_First_Version.drawio.png 
>  A complete list of all documents referenced. Each document should be identified by title, date, and publishing organization. You can also insert hyperlinks, in order to open the references conviniently.

### 2. Functional requirements
#### 2.1 Overview 
Our website is comparable to other famous sites like Quora or Reddit, but specialized for the DHBW-environment. Users can post DHBW-related content and see other user-generated content and interact with it by liking or commenting on it. They can also befriend each other, customize their feed and see official events by the DHBW or its sub-organisations. Here is a UML-diagram, which covers the rough and initial outline of our website: 

![UML_ diagram](https://github.com/SE-TINF22B6/DHBWhub/blob/master/docs/UseCaseEndUser.drawio.png)
 

#### 2.2 Use Cases
In the following section, we will depict the most important use cases of our project.

#### 2.2.1 Landing Page
- Create Landing Page for user.
- As a user, I want to be greeted with an appealing, modern looking Landingpage when visiting the website for the first time. 

- user stories: ![Issue 23](https://github.com/SE-TINF22B6/DHBWhub/issues/23), ![Issue 27](https://github.com/SE-TINF22B6/DHBWhub/issues/27)
- Link to mockup: https://www.figma.com/file/n6GgzaugPmNSt1OF1RBfZJ/DHBWhub?type=design&mode=design&t=8058pcoXIPrzWU3J-0

- Preconditions: The mockup is created.
- Postconditions: Many other features are going to be available once the landing page is finished.
- Estimated efforts: medium


#### 2.2.2 Login/Sign-Up Dialog
- Create a modal Dialog for users to either log in or create a new Account
- As a user, I want to be able to log into my account, create a new account, log myself out or sign in through 3rd-party applications e.g. Google.

- user stories: ![Issue 23](https://github.com/SE-TINF22B6/DHBWhub/issues/23), ![Issue 27](https://github.com/SE-TINF22B6/DHBWhub/issues/27)
- Link to mockup: https://www.figma.com/file/SqiyoobCDMDfIl9j8O2N7a/Login-Page?type=design&mode=design&t=8058pcoXIPrzWU3J-0 

- Preconditions: Backend server has to be active and the mockup has to be created.
- Postconditions: Users can sign in with their accounts and see a personalized feed.
- Estimated efforts: medium

#### 2.2.3 Thread/Post Page
- Create Page for a Thread/Post to be displayed with its interactions.
- As a user, I want to be able to view a Thread/Question/Post with its comments on a single page.

- user stories: ![Issue 67](https://github.com/SE-TINF22B6/DHBWhub/issues/67)
- Link to mockup: n.A.

- Preconditions: Sample Threads and a mockup have to be created, as well as the database schema.
- Postconditions: Users can view single Threads/ Posts with all of their interactions.
- Estimated efforts: medium

#### 2.2.4 Account Management Page
- Create a Page for the User to personalize his or her account settings.
- As a user, I want to be able to change my own settings to personalize my profile or change other crucial personal details.

- user stories: ![Issue 66](https://github.com/SE-TINF22B6/DHBWhub/issues/66)
- Link to mockup: n.A.

- Preconditions: The landing page as well as the user accounts have to be fully functioning in order to change it.
- Postconditions: Users can customize their own profile.
- Estimated efforts: medium
> Other Use Cases will be added when discussed properly
> 
### 3. Nonfunctional requirements
#### 3.1 Usability
It is very important to provide the users a smooth and user-friendly experience. Thus, it is crucial during the entire development process, to think of simplifiying user interactions.
Schemas: n.A.

#### 3.2 Security
In order to establish a secure website and protect the users against unlawful use of their personal data, a plan for the authentification and authorization process is necessary.
Hereby, we have to design an secure authentification system and define authorisation rules through e.g. user roles.
Schema: n.A.

> [!IMPORTANT]  
> Categories: Usability, Reliability, Performance, Efficiency, Integrity, Maintainability, Flexibility, Testability, Reusability, Security.  

### 4. Technical constraints
> Specify any major constraints, assumptions or dependencies, e.g., any restrictions about which type of server to use, which type of open source license must be complied, etc. 
