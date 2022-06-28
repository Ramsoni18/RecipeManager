# RecipeManager

Recipe Manager application is a standalone java application that allows users to manage their favourite recipes using REST API. It supports adding, updating, 
removing and fetching recipes. Additionally, a search filter for recipes based on the following criteria is provided:
1. Whether or not the dish is vegetarian
2. The number of servings
3. Specific ingredients (either include or exclude) 
4. Text search within the instructions.


How to Execute the Project:-
1. Go to Root folder of project at terminal
2. Execute command for installation of project ./mvnw clean install
3. Execute command for running the project ./mvnw spring-boot:run

How to configure a Profile for Development , Testing and Production Environment :-
1. In a text editor, Open the file at location /src/main/resources/application.properties
2. spring.profiles.active should be set to "dev" ,"test" or "prod" depending on the environment.

Pre-requisite installation :-
- Java version 11 

Swagger UI is vaialable on after execution:-
http://localhost:8080/swagger-ui/

<img width="1553" alt="Screenshot 2022-06-28 at 02 41 00" src="https://user-images.githubusercontent.com/100705527/176062844-52dbfc62-40c3-44ce-ae3e-f80a332b78d2.png">
