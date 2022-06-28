# RecipeManager

Recipe Manager application is a standalone java application which allows users to manage their favourite recipes via REST API. It allow adding, updating, 
removing and fetching recipes. Additionally provided search filter for recipes based on the following criteria:
1. Whether or not the dish is vegetarian
2. The number of servings
3. Specific ingredients (either include or exclude) 
4. Text search within the instructions.


How to Execute the Project:-
1. Go to Root folder of project at terminal
2. Execute command ./mvnw clean install

How to setup Profile for Development , Test and production Environment :-
1. Open file at location /src/main/resources/application.properties in text editor
2. Update property spring.profiles.active to "dev" or "test" or "prod" according to the environment.

Pre-requisite installation :-
- Java version 11 

Swagger UI accessible on :-
http://localhost:8080/swagger-ui/

<img width="1553" alt="Screenshot 2022-06-28 at 02 41 00" src="https://user-images.githubusercontent.com/100705527/176062844-52dbfc62-40c3-44ce-ae3e-f80a332b78d2.png">
