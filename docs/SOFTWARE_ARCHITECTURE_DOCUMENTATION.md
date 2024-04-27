# Project Name
## Software Architecture Documentation

### 1. Introduction
#### 1.1 Overview
The architecture design of our project DHBWHub is based on several principles:
1. **Data independence**    
   Each layer or component in our architectural system only works with a data model, which contains all the relevant data. For example, several components may
   need data from one database table, but each one gets their data delivered in an appropriate format leaving out all the unnecessary information.
2. **Fast response**  
   Since our project is based on creating, retrieving and updating user data almost the whole time, it is crucial that our architecture provides the necessary
   data in a very short time. Data independence is one way to retrieve data fast, but we also established structures, in which frequently asked queries are already
   stored in the background and only wait for the corresponding requests.
3. **Segregation of responsibilites**  
   Each module, class or endpoint exists for its own responsiblity, e.g. a post endpoint returns only data relevant for posts and uses other components, that are solely
   responsible for retrieving or updating data regarding posts. Thus, we can keep a clean and logically consistent structure which also makes it easier for fixing mistakes
   or refactoring.
4. **Modularity**
   This principle is especially important for the frontend, since it makes it easier to create new structures and components, if they can be decomposed into 'atoms' and 'molecules'
   (smaller independent components). It also enables reusability and reduces the needed amount of time, to create and test new features. 

#### 1.2 Constraints
The choice for our software architecture is due to some organizational and technical constraints as well as customer needs:
- We need to comply with a minimalist use of personal and sensible user data.
- The amount of development time to finish this project.
> May need some revisal in the near future

#### 1.3 Definitions, Acronyms and Abbreviations
> TODO
 
#### 1.4 References
- CreateCommentComponentSequenceDiagram, 27.04.2024: https://github.com/SE-TINF22B6/DHBWhub/blob/master/docs/diagrams/CreateCommentComponentSequenceDiagram.drawio.png
- UML-PackageDiagram, 27.04.2024: https://github.com/SE-TINF22B6/DHBWhub/blob/master/docs/diagrams/PackageDiagram.drawio.png

### 2. Architecture tactics
> Reference your architecturally significant requirements from Semester 3.
> Revise your architecture tactics from Semester 3.

### 3. Architecture design
> This section specifies the architecture design in various views.
> Minimum requirement:
> - sequence diagram on a component level and necessary description
> - component diagrams and/or package diagrams, and necessary description

#### 3.1 Overview 
> A summary of the architecture design -- highlights.  

#### 3.2 Runtime view (Tips: https://docs.arc42.org/section-6/)

#### 3.3 Deployment view (Tips: https://docs.arc42.org/section-7/)

#### 3.4 ... ...

