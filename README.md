# User Agent API

## Responses:

- Response is in JSON.
- Error Response is in VND+ERROR

## Endpoints

- See http://localhost:8080/swagger-ui.html

## Stack

This is a microservice application built using the following technologies:

- **Language**: Java 8   
- **Framework**: Spring Boot
- **Metrics Database**: InfluxDB
- **Documentation**: Swagger (OpenAPI)
- **Infrastructure**: Docker


## Development

To start your application in the dev profile, simply run:

```
./mvnw
```

Alternatively, you may use the Spring-boot only command:
 
```
mvn spring-boot:run
```

## Before pushing to git repository

Format code: 

```
mvn formatter:format
```

## Building for Production


To optimize the chat application for production, run:


```
./mvnw -Pprod clean package
```


To ensure everything worked, run:

```
java -jar target/*.jar
```    

Then navigate to [http://localhost:8080/v2/api-docs](http://localhost:8081/v2/api-docs) in your browser. This will load the API documentation in Swagger format.
