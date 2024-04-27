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
- CreateCommentComponentSequenceDiagram, 27.04.2024:
  https://github.com/SE-TINF22B6/DHBWhub/blob/master/docs/diagrams/CreateCommentComponentSequenceDiagram.drawio.png
- UML-PackageDiagram, 27.04.2024:
  https://github.com/SE-TINF22B6/DHBWhub/blob/master/docs/diagrams/PackageDiagram.drawio.png
- Utility-Tree, 27.04.2024:
  https://github.com/SE-TINF22B6/DHBWhub/blob/master/docs/diagrams/UtilityTree.drawio.png

### 2. Architecture tactics
The reason for our architectural choice is partly based on our significant architecture requirements analysis. The following image depicts a summary of our top three quality attributes:  

![UtilityTree](https://github.com/SE-TINF22B6/DHBWhub/assets/122597204/e9a99f50-a210-43b4-9c1a-2e8feb230323)  

A modular structure, as aleady mentioned in the chapter 1.1, is important since it allows our developers an easier und faster development of new features. On the one hand, 
it helps the end users to navigate through our application without getting confused. On the other hand, developers can extend the application with new code components without
disturbing the already existing system. This goal can be reached through an previously established CI/CD pipeline which already covers most areas. Another way is through 
atomic component architecture. The web design is composed of several small components which are composed of even smaller different components. By reusing already programmed assets,
the addition of new features should reduce the development time drastically.  
  
A fast response time is also a top priority. High latency worsens the mood of the end user. Thus, some structures need to be included in our software architecture design such as using
intermediate data layers where relevant data is already stored and can be retrieved easily or asynchronous loading, in which the retrieval of specific data happens in the background and  
can be used, when actually needed.

### 3. Architecture design
#### 3.1 Overview 
In the following image you can see the three main components of our software, the website itself (frontend), the backend and our database:  
  
![PackageDiagram](https://github.com/SE-TINF22B6/DHBWhub/assets/122597204/5445e198-1f31-43db-a00a-0998bbb98c5f)  

The website consists of the packages atoms and molecules, which is included in the well known frontend framework 'component-based architecture'. Hereby, these packages consist of components
which on the other hand can be decomposed to smaller components, that are also reusable. 

#### 3.2 Runtime view (Tips: https://docs.arc42.org/section-6/)

#### 3.3 Deployment view (Tips: https://docs.arc42.org/section-7/)

#### 3.4 ... ...

