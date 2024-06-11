# DHBWHub
## Test Report

### 1. Introduction
Our application is mainly divided into three major components:
1.  Frontend
2.  Backend
3.  Database  

Each component requires its own methodology of testing since they focus on different things. These will be elaborated in further detail in the next section. We are using the following testing software to accomplish our goals:
- Frontend:
  - React Testing Library
- Backend:
  - JUnit5
  - Mockito
  - SpringFramework Libraries

### 2. Test Strategy
#### Frontend
The focus lies on the correct (re-)rendering of objects as well as the right function calls of the buttons. Hereby, integration and unit tests are crucial when testing the frontend. For the framework React, the 'React Testing Library' is quite useful to implement these kind of tests. Manual testing is also required, because some visual aspects need to be reviewed by the actual developers.
#### Backend
Since the backend consists of three major layers with each requiring components from the layer beneath, integration tests play an essential role in this section. These subcomponents need to be mocked, so that the testing of the actual component can proceed. This is possible through the library "Mockito". The focal point of the backend is the correct transformation of data, which means that we insert a datamodel into a component and either expect the correct transformation of the data into the right datamodel or if things go wrong (e.g. object doesn't exist in the database), an appropriate error message. The whole testing will be handled by the testing framework 'JUnit5' (implementation) and the build tool 'Maven' (runs all the defined tests).
#### Database
Some tables trigger functions that create external views which can be used by the backend for faster query retrievals. We have to manually check, whether the views contain the expected result by checking the respective SQL-queries on correctness.

### 3. Test Plan
In order to achieve our testing goals, we need to setup the previously mentioned libraries such as 'JUnit5', 'Mockito' and 'React testing library'. When implementing new features, we are also writing the tests concurrently. In this way, we can be more sure of how the new feature works and reacts to different possible scenarios.
As mentioned earlier, the focus on the frontend testing lies in the correct rerendering of the objects, the depiction of appropriate data as well as the right function calls when clicking on buttons.  
In the backend, we are planning to check for each function, whether they are transforming or retrieving the data correctly when given the correct data input, or if they are cancelling the operation when given false input.

### 4. Test Cases
All the tests that are depicted in the next subtopics have already passed, since we only launch new features to our main branch, whenever all the relevant tests have passed.
#### 4.1 Frontend
> TODO
#### 4.2 Backend
Each layer in the backend consists of similar test structures. Thus, we will show you an example for each layer, that is applicable for all the other entity models in our project.
#### Controller
In the controller layer, we mainly check whether the regarded HTTP-Request returns the anticipated datamodel. You can see in the image below, that the AccountService needed to be mocked since the AccountController is using it in the actual method. Thus, we need to setup the Service beforehand with the help of Mockito. Hereby we tell JUnit, that whenever the 'get(id)'-command in the AccountService is called, it should return a predefined model which includes all the relevant data. After that, the HTTP-Request (we want to test) is sent to our application and the response is captured. Within the response, we check if the HTTP-Response is '200' (Request worked succesfully) and if the keys in the body contain the transformed data in the respective values:
  
![ControllerTests](https://github.com/SE-TINF22B6/DHBWhub/assets/122597204/5a399852-b154-4c17-ba18-aaf34ae8af90)

#### Service
The service component tests work in a similar fashion, although the object we need to mock is the corresponding repository. In contrast to the controllers, we only need simple assertions just like in the image below. Usually, we check whether the repository returns the correct amount of data in different situations:  
  
![ServiceTests](https://github.com/SE-TINF22B6/DHBWhub/assets/122597204/1c4f06c4-1a43-4d38-899a-b39cd81bdbf0)

#### Repository
Basically the same as the tests in the service layer, since the repositories use JPA-repositories to retrieve the actual data:

![RepositoryTests](https://github.com/SE-TINF22B6/DHBWhub/assets/122597204/f772b6e8-097e-4d29-b287-ed3b7b314ac1)

### 5. Test Results 
#### Frontend
> Todo

#### Backend
In the following image, you can see an example of running all the tests related to the AccountController at the same time. JUnit shows whether each test has passed and if yes, how long did it take.  
  
![RunAllTests](https://github.com/SE-TINF22B6/DHBWhub/assets/122597204/da3354e6-5230-4d76-999c-a9a6b4865480)

### 6. Metrics
#### Frontend
> Todo
#### Backend
When building the local version of the project that contains the newly implemented feature, Maven automatically runs all the tests that are located in the test package. The same applies to creating pull requests in which a maven pipeline does the same function and tells all the developers, whether all the tests in the branch function correctly:

![MavenBuild](https://github.com/SE-TINF22B6/DHBWhub/assets/122597204/01b72b92-ab64-4632-add9-03d2c699ca94)
  
### 7. Recommendations
Although our current tests can verify that components function properly under certain conditions, it is still important to use our actual product as much as possible. The largest amount of bugs and errors can be found through actual interaction with the website and imitation of an actual client user. Stress tests can also be helpful to determine, whether our database and backend connection is strong enough to handle multiple requests at the same time while also maintaining data coherence.  
### 8. Conclusion
> This section summarizes the key findings of the testing and the overall status of the software quality.
> TODO towards the end
