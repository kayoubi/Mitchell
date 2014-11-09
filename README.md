## Technology

* Spring Boot (http://projects.spring.io/spring-boot/)
* Spring MVC
* Hibernate
* JAXB binding
* In-Memory HSQL DB
* In-Memory Tomcat

## Usage

* to start the application 
```
mvn spring-boot:run
```

* to run the tests
```
mvn test
```

## Testing
* REST API testing: ClaimsControllerTest
* Integration testing: ClaimServiceIntegrationTest
* manual testing using any rest client (make sure to set content-type / accept headers to application/xml)

## Notes and Assumptions
* the supported mxl has lower case elements name, I could easily add @XmlElement to every single property in the models and update my tests to accommodate this, but that’s just typing! in production I’d override JAXB’s XmlElement annotation to always change the element name to CamelCase
* wasn’t sure if the Vehicle “vin” is it’s primary key, but I went with the assumption that it has an “id” (for update / get) 
* I assumed Vehicles are accessible only via claims (for get and update) hence one controller
* I assumed the only entry point to the system is the REST API (controller) so all validation / conversion / etc.. happen there, and the service will always receives a valid Objects, if there are other integration points (JMS, Schedules etc..) i’d design the architecture differently to accommodate this
 
## Data Model

![alt tag](https://github.com/kayoubi/Mitchell/blob/master/data%20model.png)