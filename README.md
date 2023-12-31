# Marathon Planner

This is a full-stack marathon training plan creator that takes in information about the user and dynamically generates a marathon training plan customized to the skill level of the runner. It lists out the training plan as a schedule and visualizes it with a graph.

## Technologies Used

- Spring Boot
- React (Create React App)
- Java
- JavaScript
- HTML
- Bootstrap 5
- Chart.js
- Axios

## To Run

1.  Start up back-end:

>> `cd marathon-planner`

>> `mvn spring-boot:run`

2. Start up front-end:

>> `npm start`

## Credits

### Chart.js Functionality
- https://blog.logrocket.com/using-chart-js-react/

## Images

### *Runner fills their information, and then clicks on 'Generate Training Plan'*

![Runner Training Plan Form](images/runnerForm.png)

### *A Training Plan is Dynamically Generated in the Back End, and Listed in the UI*
![Generated Marathon Training Plan Schedule](images/trainingPlanSchedule.png)

### *A Graph of the Entire Training Plan is also Shown (using Chart.js)*
![Line Chart of Training Plan](images/trainingPlanGraph.png)