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
