| Who/What | Event | Influence | Condition | Action | Measurement |  
| - | - | - | - | - | - |
| End User | Writes post and clicks "send"-button | Data within system | Normal operation | End User gets visible notification whether post has been saved successfully  | Visual feedback |
| Developer | Wants to create new big feature | System  | Development | Adding the feature without negatively impacting the existing system | CI/CD-Pipelines still work successfully |
| End User | Wants to see the posts after short loading time | Data within system | Normal operation | Data on the webpage should load "instantly" | Request-Response time max. 5 sec |
| End User | Create Account/Personalize information | Data within the system | Account management | Data should be stored securely and conform to local laws | System structure (password hashing, etc...) |
| Developer/Tester | Wants to test new features | Test classes | Development | Tests should be added quick and cover most important areas | High test coverage in short amount of time (depends on application) |
| Web Developer | Wants to create new components for the webpage | System |  Development | Adding new components shouldn't be too difficult, reusability of other components | Relatively short development time for similar features |
| End User | Clicks on the calendar  | Data within system | Normal operation | Events on calendar are up-to-date and consistent with other users | Checking the state on different devices, database monitoring |
| Unregistered user | Browses the website | Data within the system | Normal operation | Unregistered user gets to know which features he can't use or is restricted | Implementing visual reminder, manual UI-Tests |
| End Users | Opens the website in a new session | Data within the system | Normal operation | Opening different pages except the feed page shouldn't take too long | Measuring asynchronous loading while the feed page is shown |
| Developer | Develops new features on a different branch | System | Development | Developer can develop new features without manually checking the state of the product | automated CI/CD pipeline checking the state of the project|  
