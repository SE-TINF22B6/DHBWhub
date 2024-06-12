# DHBWhub
## Metrics Report
In this document, we are reporting the results of the additional metrics we have chosen for DHBWhub.  
  
### Relation among classes
We have used the tool [ck](https://github.com/mauricioaniche/ck) to calculate metrics such as the depth of the inheritance tree, number of children, method inheritance factor and response for class. These are the results:
  

  
### Web Application Performance and Monitoring
We have used the web extension 'PageSpeed Insights' to evaluate our website in regards to its rendering and response performance. The following image contains the result for the current state of our application:
  
![grafik](https://github.com/SE-TINF22B6/DHBWhub/assets/122597204/926b8fe6-249c-4f37-8ab8-a33762f730e6)

The high score on best practices is just what we wanted to achieve with our component based architecture on the frontend. The low score on the response time was quite expected, since there are several reasons for it:
1. On the homepage, all the posts are retrieved from the database every time you enter it or refresh the tab. The reason is to provide consistent data which is conform to the data in the database.
2. Our backend is located on a BWcloud server which was provided for free for every student in the region. Thus, there are obviously restrictions regarding the request and response times between the backend and the frontend.
