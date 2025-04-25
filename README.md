# Spring test back-end

This is spring-boot backend for spring-test.

## Tools used for backend
1. Spring boot
2. Spring security - JWT auth settings
3. Lombok - POJO Generation
4. Mysql

## Steps for full setup

1. Get/Clone backend repo from https://github.com/devnev39/spring-test-back
2. Get/Clone frontend repo from https://github.com/devnev39/spring-test-front
3. Mysql is required for backend setup. Create a database with name dac OR setup the name in application.properties.
4. Run the backend migrations with 
```bash
    mvn flyway:migrate
```
5. Run the backend with 
```bash
    mvn clean spring-boot:run
```
6. Install the frontend dependencies with 
```bash
    npm i
```
7. Run the frontend with 
```bash
    npm run dev
```
The backend url can be set from the config.js.

## Info about folder structure

.
|____out.txt
|____test
| |____java
| | |____com
| | | |____example
| | | | |____demo
| | | | | |____DemoApplicationTests.java
|____main
| |____resources
| | |____static
| | |____**db**  -  Contains db related files like migrations
| | | |____migration
| | |____templates
| | |____application.properties
| |____java
| | |____com
| | | |____example
| | | | |____demo
| | | | | |____**filters**  - Contains filters like middlewares
| | | | | |____**repository**  - Contains jpa repositories which interfaces with database
| | | | | |____**security**  -  Contains security configuration and jwt configs
| | | | | |____**models**  -  Contains all the models required for information transfer
| | | | | | |____**dto**  -  Contains classes associated with REST API transfer
| | | | | | |____**entity**  -  Contains classes which are directly translated to database schema
| | | | | | |____**common** - Contains classes which are used internally in the backend
| | | | | |____**exceptions** - Contains classes which handle all the exceptions in the backend code
| | | | | |____**controllers** - All the rest api controllers like express routers
| | | | | |____**services** - Service configurations like JWT
| | | | | |____DemoApplication.java
