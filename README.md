## Technology

* Spring Boot (http://projects.spring.io/spring-boot/)
* Spring MVC
* Hibernate
* JAXB binding
* Spring-test / DBUnit / Mockito
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

## REST API
<h4>Claim</h4>
* GET /mitchell/claims/{id} (get one claim by id)     
* PUT /mitchell/claims/{id} (update one claim) 
* POST /mitchell/claims (create new claim)
* DELETE /mitchell/{id} (delete one claim by id)
* GET /mitchell/claims?lossDateFrom={yyyy-MM-dd}&lossDateTo={yyyy-MM-dd} (search between two dates, or 'lossDateFrom' date and 'now' if 'lossDateTo' is absent)

<h4>Vehicle</h4>
* GET /michell/claims/{id}/vehicle/{id}                

## Testing
* REST API testing: `ClaimsControllerTest`
* Integration testing: `ClaimServiceIntegrationTest`
* manual testing using any rest client (make sure to set content-type / accept headers to application/xml)

## Notes and Assumptions
* for create/update the supported date format is the default "yyyy-MM-dd" but that can be easily changed to a specific requirement
* wasn’t sure if the Vehicle “vin” is its primary key, but I went with the assumption that it has an “id” (for update / get)
* the supported mxl has lower case elements name, to change this i'd need to add @XmlElement to every single property in the models and update the tests! in production I’d override JAXB’s XmlElement annotation to always change the element name to CamelCase (let me know if you want this implemented ?)
* I assumed Vehicles are accessible only via claims (for get and update) hence one controller
* I assumed the only entry point to the system is the REST API (controllers) so all validation / conversion / etc.. happen in this layer, and the services will always receive valid Objects, if there are other integration points (JMS, internal schedules etc..) i’d design the architecture differently to accommodate this
* I have runtime jvm set to version 8, everything works fine on older versions (tried with 6), but I noticed that with older versions JAXB doesn't generate the xmlns properly, it still consumes the requests all right.
 
## Data Model

![alt tag](https://github.com/kayoubi/Mitchell/blob/master/data%20model.png)