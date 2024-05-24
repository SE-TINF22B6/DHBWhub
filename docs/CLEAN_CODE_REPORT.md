# DHBWhub
## Clean Code Report
### Introduction
In this report, you should be able to see the ways in which we are conforming to existing Clean-Code-Principals. For each principal, we will highlight the importance of it for our project and show some examples.
  
#### 1. Following naming conventions
We have a strict convention when it comes to naming components in our project:
- Functions, classes and variables are written in 'CamelCase'.
- Packages only contain small letters and are divided by hyphens instead of whitespace.
- Links are also divided by hyphens instead of whitespace.
  
Here are some examples:  
![grafik](https://github.com/SE-TINF22B6/DHBWhub/assets/122597204/f7acdf88-27a4-4b76-97ec-8734450c4caa)

![grafik](https://github.com/SE-TINF22B6/DHBWhub/assets/122597204/24134249-a1d8-4d67-b0fc-520d3c936ee9)
  
#### 2. Single Responsibility Principle
The SRP is quite simple. Each function and method is responsible for one particular action or Set of action. If requirements are changing, then only the respective method has to change, nothing more. We are trying to implement SRP by outsourcing commonly used logic as new methods. One example for this are the MapperClasses. Instead of creating the needed Objects within the methods, we use static methods of the respective MapperClass, which takes the source data as input and returns the needed data model. You can see this in the following images. Moreover, you can also see that the logic for getting the amount of comments as well as tags of each post is located in a seperate function:  
  
![grafik](https://github.com/SE-TINF22B6/DHBWhub/assets/122597204/12dfa573-6d7e-4b5c-b200-eec3ac5919e4)  
  
![grafik](https://github.com/SE-TINF22B6/DHBWhub/assets/122597204/2b124bfa-22ef-476d-831e-c8e74f5416fc)  
  
#### 3. Consistent Formatting and Indentation
It goes without saying that the code formatting should stay consistent, even after creating new features. This is also accompanied by the boy scout ethos 'Leaving the campground cleaner than you found it'. If a developer in our team forgot the correct formatting, the developer who comes across the mistake should either fix it or point it out.

#### 4. Principle of Least Astonishement
Methods or requests as well as their results shouldn't surprise our users. That means that we have to name links as well as methods self explanatory and if that is not possible, we need to lay out comments which explain the logic behind the corresponding function. In the following examples, it is quite trivial what kind of data each endpoint returns:  
  
![grafik](https://github.com/SE-TINF22B6/DHBWhub/assets/122597204/06682dbf-740c-4274-8e8a-8eabd5fae462)
