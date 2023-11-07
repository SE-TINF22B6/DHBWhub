## Our CI/CD-Setup
In this document, we will highlight our CI/CD-Setup in a little bit more detail. The graph below should give a rough idea on how our setup currently looks like. 
We have divided it into two section:
- Development environment: CI/CD-Tools mainly focussing on developing clean and tested code
- Central repository environment: CI/CD-Pipelines with automated processes to ensure absolute correctness on the main-branch

Afterwards, we will show you in detail on how we have configured the corresponding tools and processes to ensure high code quality:

![CICD-Workflow](https://github.com/SE-TINF22B6/DHBWhub/blob/master/docs/diagrams/CI.CD_workflow.png?raw=true)

### Development Environment
#### Git
In order to avoid merge conflicts and create isolated environments, we have decided to divide our main-branch into a frontend and backend branch. To avoid further
clashes, we agreed on creating feature branches which are only temporary available for a new function and get deleted after merging it to the next higher stage.

#### Apache Maven
Maven is an tool used for automating building processes and easier project management. You can compile and test your project directly on your local machine to see
whether your code changes are integrated well in the whole project folder. The most important maven file ist the pom.xml file. This file describes the configuration
of the whole project such as general project information, needed dependencies and build configurations. You can find our pom.xml file [here](https://github.com/SE-TINF22B6/DHBWhub/blob/master/pom.xml). 
In our case, we use the following important dependencies:
- Spring-Framework: Ensures easier communication and configuration between the frontend, middleware and backend
- PostgreSQL: Used for connecting to our database server
- Spring-Dotenv: Allows creation of .env-files, which save important environment variables such as the database server credentials

#### JUnit 5
A famous framework for writing unit tests in java is JUnit 5. Hereby, Maven already created an extra folder in which you can write your unit tests. JUnit allows you to
create classes in which you can test methods and functions of the respective class in the original source folder. You can also create parameterized tests. In order to
use JUnit, you better include the latest version of junit.jupiter in your maven dependencies.

#### React Testing Library
Another testing framework is the React Testing Library. This tool allows us to create tests mainly with JavaScript/React components. The focus lies on recreating and testing
typical user behaviour and scenarious on our webpage to ensure reliability and user friendliness (end-to-end tests).

#### SonarLint - SonarQube - SonarCloud
SonarLint is a plugin/extension which is downloadable in your IDE. It analyzes your current source code folder for bugs, code smells and provides suggestions for cleaner code
development. To make the analysis available for every team member, you either need SonarQube on your own server or use a cloud service like SonarCloud in which you can link 
your GitHub repository.

### Source Code Repository Environment
After pushing new codes or features onto our main branch (or other selected branches like the frontend/backend branch), several CI/CD-processes run automatically through GitHub Actions.
In order to describe the respective processes, we need to configure .yml-files. These are our current essential workflows:
- CodeQL:
   - CodeQL is a GitHub tool used to analyze our entire repository for vulnerabilites, dependency and code quality issues
   - You can find our configuration file [here](https://github.com/SE-TINF22B6/DHBWhub/blob/master/.github/workflows/codeql.yml)
   - This workflow activates after each push or pull-request on the master and frontend branch
   - It checks the branch out, initializes the required CodeQL-tools and performs the code analyis
- Maven:
   - Our Maven pipeline runs automatically after each push and pull request on the master and frontend branch
   - You can find our configuration file [here](https://github.com/SE-TINF22B6/DHBWhub/blob/master/.github/workflows/maven.yml)
   - It mainly builds the whole project from scratch and automatically runs all the tests
- React:
   - Our React pipeline runs automatically after each push and pull request on the master and frontend branch.
   - You can find our configuration file [here](https://github.com/SE-TINF22B6/DHBWhub/blob/master/.github/workflows/react.yml)
   - Only focusses on the frontend. Builds and tests the react application
- DependaBot:
   - DependaBot is a utility tool which periodically scans our repository dependencies
   - You can find our configuration file [here](https://github.com/SE-TINF22B6/DHBWhub/blob/master/.github/dependabot.yml)
   - Checks if there are updated or new versions of dependencies and sends a pull request which the team needs to approve
