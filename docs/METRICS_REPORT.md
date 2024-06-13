# DHBWhub
## Metrics Report
In this document, we are reporting the results of the additional metrics we have chosen for DHBWhub.  
  
### Relation among classes
We have used the tool [ck](https://github.com/mauricioaniche/ck) to calculate metrics such as the depth of the inheritance tree, number of children and response for class. These are the results:\
Depth inheritance tree
  
![grafik](https://github.com/SE-TINF22B6/DHBWhub/assets/122597204/020c0bb6-f49f-4a1a-8d46-4c6bdc428f62)

Beside the one unusual spike, the max depth of the tree is 2 which is quite flat (max. one class depends on another class).

The number of children is constantly zero, since we don't use any abstract classes.
  
The response for classes indicator on average is 9.28.

### Coupling
We used the handy extension 'CodeMR Metrics' from the IntelliJ extension marketplace to calculate several indicators related to coupling. The attributes are afferent (AC) and efferent coupling (EC), abstractness (Abs) and instability (Ins).
  
![Coupling](https://github.com/SE-TINF22B6/DHBWhub/assets/122597204/175ad119-2b6b-43c0-94df-97e0eff03bbc)
    
As you can see, all the indicators are on the very low side, just as we expected.

### Web Application Performance
We have used the web extension 'PageSpeed Insights' to evaluate our website in regards to its rendering and response performance. The following image contains the result for the current state of our application:
  
![grafik](https://github.com/SE-TINF22B6/DHBWhub/assets/122597204/926b8fe6-249c-4f37-8ab8-a33762f730e6)

The high score on best practices is just what we wanted to achieve with our component based architecture on the frontend. The low score on the response time was quite expected, since there are several reasons for it:
1. On the homepage, all the posts are retrieved from the database every time you enter it or refresh the tab. The reason is to provide consistent data which is conform to the data in the database.
2. Our backend is located on a BWcloud server which was provided for free for every student in the region. Thus, there are obviously restrictions regarding the request and response times between the backend and the frontend.
3. The homepage data also includes many images, that we haven't compressed yet. Thus, the latency naturally increases.
