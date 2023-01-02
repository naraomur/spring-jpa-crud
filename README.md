# Spring-JPA-CRUD
### Basic CRUD template for Department and Employee management by using REST API
**Table of contents:**
- Introduction
- Requirements for installation
- Getting Started
- Project Structure
- Models
- API calls
- Advanced call
- Exceptions
- Conclusion
- Author

### Introduction
The spring-jpa-crud is a simple template project that demonstrates how to use Spring Boot and JPA to create a CRUD (create, read, update, delete) web application. The project is built using Maven and uses PostgreSQL to store data.

### Requirements for installation
Tools and environments:
- JDK 11 or later
- PostgreSQL(E.g. PgAdmin)
- Postman
- IntelliJ Idea Ultimate version

### Getting Started
1. Open Git commander or any other commander window which is default on your OS(cmd on Windows, Terminal on Mac). All further examples will be shown for Windows.
If you're on Mac you can always search the alternative commands for Mac on the internet.

2. Swich to a directory where you want place this project
```
C:\Users\ThinkPad>cd desktop
```

3. Write the following command on your cmd prompt window as follows:
```
C:\Users\ThinkPad\Desktop>git clone https://github.com/naraomur/spring-jpa-crud.git
```
if you got the following response you are good to go!
```
Cloning into 'spring-jpa-crud'...
remote: Enumerating objects: 149, done.
remote: Counting objects: 100% (149/149), done.
remote: Compressing objects: 100% (89/89), done.
Receiving objects:  69% (103/149) 149 (delta 45), reused 136 (delta 32), pack-reused 0
Receiving objects: 100% (149/149), 75.75 KiB | 724.00 KiB/s, done.
Resolving deltas: 100% (45/45), done.

```

### Project Structure
1. In your IDE open this project and you should see similar to the below structure of the project and you can run the application by **SpringJpaCrudApplication.java**
```
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── sprinjpacrud01
│   │   │           ├── controller
│   │   │           ├── dto
│   │   │           ├── model
│   │   │           ├── repository
│   │   │           ├── service
│   │   │           └── SpringJpaCrudApplication.java
│   │   └── resources
│   │       ├── application.properties   
│   │       └── text_bundles...
│   └── test
│       └── java
├── pom.xml
├──.gitignore
├── mvnw
├── mvnw.cmd
└── README.md
├── External Libraries...
...

```

### Models and database
- In this application there are two models one is Department and Employee. These two models are related to each other as Department having many employees and the Employee belonging to only one department.
- For the database connection use the application.properties in the **main/resources**. Simply modify your own username and password for PostgreSql. 
- Create **departments** database in your Postgres management tool where the models Department and Employee will be stored. No need to create the tables manually just the database itself.
```
##connection to the database
spring.datasource.driver-class-name=org.postgresql.Driver
spring.datasource.url=jdbc:postgresql://localhost:5432/departments
spring.datasource.username={your name}
spring.datasource.password={your password}
# Allows Hibernate to generate SQL optimized for a particular DBMS
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### API calls
1. Open the Postman and enter the following endpoint and the body requests
- Send POST request as adding new employee to the database by using this endpoint and JSON body:

```
http://localhost:8080/employee
```

```
   {
        "id": 1,
        "name": "Caroline",
        "position": "Manager"
    }
```
> You can add several employees by sending one at a time POST request. 

- Same for the department

```
http://localhost:8080/department
```

```
   {
    "id": 1,
    "name": "Technical"
   }
```
> You can add several departments by sending one at a time POST request. 

### Advanced call
In the directory of **controller** you can explore the controllers and notice **MainController** which uses okHttp Client in order to assign employee to the department by simply getting their ids' in JSON body.
In Postman create a new request POST with the endpoint and JSON body as follows:
```
http://localhost:8080/main/post-get
```
```
   {
    "em_id": 1,
    "dep_id": 1
   }
```
JSON body Response in Postman:
> Several employees belong to Technical department including the new employee Caroline.
```
{
    "id": 1,
    "name": "Caroline",
    "position": "Manager"
}
{
    "id": 1,
    "name": "Technical",
    "employeeHashSet": [
        {
            "id": 1,
            "name": "Caroline",
            "position": "Manager"
        },
        {
            "id": 11,
            "name": "Jack",
            "position": "Technician"
        },
        {
            "id": 2,
            "name": "Barbara",
            "position": "Manager"
        }
    ]
}
```
### Exceptions
In the directory of **controller** you can explore the **exceptions** directory and note **ResponseExcepton** extended from class ResponseEntityExceptionHandler.
Which helps to handle thrown exceptions on server side as NullPointerException, IllegalArgumentException and etc.
In Postman send GET request of employee by id as follows:
> Instead of Long type id we are sending String type of id
```
http://localhost:8080/employee/id?id=r
```
Response body sent by the class ResponseExcepton with status code in Postman:
```
{
    "status": "OK",
    "time": "02-01-2023 03:45:20",
    "msg": "Не удалось преобразовать id=r в требуемый тип число: 1, 2, 3..."
}
```
On the server side you get **MethodArgumentTypeMismatchException** resolved 

```
: Resolved [org.springframework.web.method.annotation.MethodArgumentTypeMismatchException
: Failed to convert value of type 'java.lang.String' to required type 'java.lang.Long';
```

### Conclusion
The technical documentation I provided describes the structure and features of the spring-jpa-crud project, but does not include all of the specific examples of how to use the API.

To use the API fully, you will need to explore the source code and try out the various endpoints and functions provided by the application. Some things you may want to try include:

- Sending HTTP requests to the various endpoints (e.g. GET http://localhost:8080/employee/id?id=1) to see how the application responds.
- Modifying the data model (i.e. the Employee class) to see how these changes affect the application's behavior.
- Customizing the application's configuration (e.g. the application.properties file) to see how this affects the application's behavior.

I hope this helps! Let me know if you have any other questions. 

### Author

:woman_technologist: @naraomur 
:e-mail: naraomur@gmail.com



