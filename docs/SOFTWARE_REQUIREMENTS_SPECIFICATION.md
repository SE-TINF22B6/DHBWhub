# DHBWhub
## Software Requirements Specification
> This template is a simplified version based on the documentation templates from IBM Rational Unified Process (RUP).
### 1. Introduction
#### 1.1 Overview
> We are a DHBW-internal forum where users can ask questions and post DHBW-related content. 
#### 1.2 Scope
> This Software Requirements Specification (SRS) document covers the complete system of DHBWHub. It encompasses both functional and non-functional requirements necessary for the successful development, deployment, and operation of the platform. The document aims to provide a comprehensive understanding of the system's architecture, features, and limitations.
#### 1.3 Definitions, Acronyms and Abbreviations
> Definitions of all terms, acronyms, and abbreviations required to properly interpret this document.
#### 1.4 References
> A complete list of all documents referenced. Each document should be identified by title, date, and publishing organization. You can also insert hyperlinks, in order to open the references conviniently.

### 2. Functional requirements
>  This section contains all the software requirements to a level of detail sufficient to enable designers to design a system to satisfy those requirements and testers to test that the system satisfies those requirements.
>  Our UML use-case-diagram:
>
  ![UML_ diagram](https://github.com/SE-TINF22B6/DHBWhub/blob/master/docs/UseCaseEndUser.drawio.png)
> 


#### 2.1 Overview 
> Users can post DHBW-related content and see other user-generated content and interact with it by liking or commenting on it. 


#### 2.2 Landing Page
> - Create Landing Page for user.
> - As a user, I want to be greeted with an appealing, modern looking Landingpage when visiting the website for the first time. 
>
> - user stories: # 23, #27
>
> - Link to mockup: https://www.figma.com/file/n6GgzaugPmNSt1OF1RBfZJ/DHBWhub?type=design&mode=design&t=8058pcoXIPrzWU3J-0
> - **Preconditions**. The mockup is created.
>
> - **Postconditions**. -
> - **Estimated efforts**: medium


#### 2.3 Account Management Page
> - Create Page for user to Log in / Create an Account
> - As a user, I want to be able to log into my account / create an account / log myself out.
>
> - Link to mockup: https://www.figma.com/file/SqiyoobCDMDfIl9j8O2N7a/Login-Page?type=design&mode=design&t=8058pcoXIPrzWU3J-0
> - user stories: # 23, #27

> - **Preconditions**: Backend server has to be active and the mockup has to be created.
> - **Postconditions**: Users can manage their account.
> - Estimated efforts: medium

#### 2.4 Thread Page
> - Create Page for a Thread to be displayed with its interactions.
> - As a user, I want to be able to view a Thread/Question/Post with its comments on one Page.
>

> - user stories: n.A.

> - **Preconditions**: sample Threads and a mockup have to be created.
> - **Postconditions**: Users can view single Threads/ Posts with all of their interactions.
> - Estimated efforts: medium

#### 3. Nonfunctional requirements

> [!IMPORTANT]  
> It is not necessary to cover all of the following categories, but focus on what your project will implement.  
> If some nonfunctional requirements are described as user stories in your backlog, add their **links** in this section, or any information to guide the reader find them in your backlog, such as a **label** of those relevant user stories.

> Categories: Usability, Reliability, Performance, Efficiency, Integrity, Maintainability, Flexibility, Testability, Reusability, Security.  


### 4. Technical constraints
> Specify any major constraints, assumptions or dependencies, e.g., any restrictions about which type of server to use, which type of open source license must be complied, etc. 
