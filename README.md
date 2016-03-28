# Middle ware logging component

## Overview

Design a middle-ware logging component which can handle two types of logs; first is the e-commerce site customer's activities
and second is application logs.
This logging component will be used by all applications in the organization hence it should be able to keep logs per application. 
Quick search for application log is required.

## Technologies used
   - Java
   - Spring Boot
   - Gradle
   - Elasticsearch
   - Logstash
   - Kibana
   - H2 Database (MySql for production)
   - Flyway

## Features

  - RESTful APIs using Spring Boot
  - Integrated with Logstash
  - Unit Testing- Junit, EasyMock, H2 database and Spring Test Suite
  - Integrated with schema migration tool, Flyway
  - JSR 303 validation

# Running

- You can run this project from the command-line in the root directory of project:
    
    
       ```
       $ gradle build
       ```
       
       ```
       $ java -jar build/libs/loggingservice-1.0-SNAPSHOT.jar
       ```
       

- You can also run using spring boot plugin, go to project root and run following command

       ```
       $ gradle clean bootRun
       ```
    
   
       
- After startup, your instance will be available on localhost, port 8080.

      ```
      http://localhost:8080/
      ```


- To open H2 console here (use JDBC url: jdbc:h2:mem:appdb  and username: sa)

       ```
       http://localhost:8080/h2-console
       ```

### Running unit tests

- To run junit test case, use following command

       ```
       $ gradle test
       ```
       
### Creating Database

 - You don't need to create database manually. This project uses flyway to create database schema automatically on startup.
 
 

# Application Flow

- After running application, your first step is to register your application. To register your application , open 
```
http://localhost:8080
```
on browser and register application. After successful registration you will receive appId and appSecret, please make a note of them.
You need to pass appSecret in Authorization header  to call logging api.

# Curl Commands to send log data

- send application log
   

   ```
curl -X POST --header "Content-Type: application/json" --header "Authorization: Bearer er1MOirYCt" -d '{"logLevel":"DEBUG","className":"login", "message":"PassThroughBlob.class","stackTrace":"Closing org.springframework.web.context.support.GenericWebApplicationContext@6a9aa100" }' "http://127.0.0.1:8080/api/log/application" -v
   ```


- send customer activity log

   ```
curl -X POST --header "Content-Type: application/json" --header "Authorization: Bearer er1MOirYCt" -d '{"productName":"MAcBook Pro", "productSkuId":"45MKL45GH", "event":"BOUGHT","userId":"5673"}' "http://127.0.0.1:8080/api/log/customerActivity" -v
   ```
