# DHBWhub
## Software Architecture Documentation

### 1. Introduction
#### 1.1 Overview
The architecture design of our project DHBWhub is based on several principles:
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
- REST: Representational State Transfer
- CRUD: Create, Read, Update and Delete
  
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
In general, our software architecture style is based on "REST". In the following image you can see the three main components of our software, the website itself (frontend), the backend and our database:  
  
![PackageDiagram](https://github.com/SE-TINF22B6/DHBWhub/assets/122597204/5445e198-1f31-43db-a00a-0998bbb98c5f)  

The website consists of the packages atoms and molecules, which is included in the well known frontend framework 'component-based architecture'. Hereby, these packages consist of components
which on the other hand can be decomposed to smaller components, that are also reusable.  
  
The backend on the other hand consists of four different modules/layer. The first layer is the 'Controller Layer' that consists of so called 'Controller' for each entity of
our project. Every controller provides end points in which external components such as the website can retrieve, insert or update data through HTTP-Requests to the respective
entity-controller. In order to work with the data, the 'Controller Layer' communicates with the 'Service Layer'. Latter consists of the actual implementations of the
'EntityService'-interfaces and is responsible for applying logic to the necessary data. It acts as the middle man between the 'Controller Layer' and the third layer, which is
called 'Repository Layer'. It includes 'Entity Repostories', which on the other hand use 'SpringEntityRepositories' to directly access the data which persists on our database.
The mapping between each layer happens through the 'Utility Layer' that provides, inter alia the packages 'Model' and 'Proposal', that are used for this purpose. This whole
structure in the backend accomplishes data independence, since every layer gets to see and work with the relevant part of the needed data.  
  
The last component is the database, which mainly consists of the tables of each entity as well as external views that are created through certain trigger functions. Views can store frequently
requested queries and get updated in the background every time, when the main entity table itself gets updated through CRUD-operations by the 'Repository Layer'. In this way, we can provide 
complex query results already established and retrieval-ready for the backend and thus, reduce the latency time.

#### 3.2 Runtime view
In the next sequence diagram, you can see the interaction between the components during runtime in a more clearer way. Hereby, we are regarding the process of creating a comment under a post:

![CreateCommentComponentSequenceDiagram](https://github.com/SE-TINF22B6/DHBWhub/assets/122597204/c9f5390b-4ab2-4d80-b54e-3d3362ca8c13)

The process starts on the website in a post thread. It gets triggered, when the user has created a comment and clicks on the 'Send'-button. The website sends a HTTP-Post-Request to the CommentController
in the backend, with the necessary data in the HTTP-RequestBody. The Controller calls the 'createComment()' command from the CommentService and also transfers the data, which was received from the website.
Latter is called 'CommentProposal', which then gets mapped into a database-conform 'CommentModel' through the usage of a 'CommentMapper' (Utility Layer). This model then gets transfered to the 'CommentRepository',
that is responsible for inserting the data into the database. If the values of the data tuple is correct and doesn't violate database checks, the database returns the same model with an unique assigned id number.
The 'CommentRepository' returns this model back to the 'CommentService' which then maps latter to a more website-conform view by removing all the unnecessary data (e.g. 'ThreadComment'). The HTTP-Response is then
delivered to the frontend with the 'ThreadComment' in the HTTP-Body. The website then rerenders the whole Post and visualizes to the user, that the comment has been created successfully.

This process visualizes best, how each layer has their own responsibility as well as works with only the data, that is relevant to them.

#### 3.3 Deployment view

#### 3.4 ... ...

