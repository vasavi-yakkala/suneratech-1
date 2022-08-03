# suneratech Microservices Training

# JDK-11:
https://www.oracle.com/java/technologies/javase-jdk11-downloads.html

# JDK-8 : 
https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html

# Spring Boot Links:
https://start.spring.io
https://spring.io/projects
https://spring.io/projects/spring-boot
https://spring.io/guides/gs/spring-boot/
latest: https://docs.spring.io
https://spring.io/guides/gs/spring-boot/
https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#boot-documentation


# Maven Commands:
mvnw clean install -Dmaven.test.skip=true
mvnw clean
mvnw clean package -Dmaven.test.skip=true

# Dependencies: 
Web, Jpa, H2
swagger:
		<dependency>
			<groupId>org.springdoc</groupId>
			<artifactId>springdoc-openapi-ui</artifactId>
			<version>1.6.9</version>
		</dependency>
		
# Swagger-url:
http://localhost:8080/swagger-ui/index.html
http://localhost:8081/v3/api-docs/
		
application.properties:
server.port=8080

# H2-Inmemory DB-console --> http://localhost:8080/h2-console
spring.h2.console.enabled=true
# Hibernate/JPA
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.hibernate.ddl-auto=create

# Actuator
management.endpoints.web.exposure.include=*

spring data jpa:
https://spring.io/guides/gs/accessing-data-jpa/
Nice example for "Supported keywords inside method names":
https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods.query-creation
spring data jpa with mysql:
https://spring.io/guides/gs/accessing-data-mysql/

# Actuator Doc:
https://docs.spring.io/spring-boot/docs/current/reference/html/production-ready-features.html
Customize: 
https://www.baeldung.com/spring-boot-actuators

# Actuator
management.endpoints.web.exposure.include=*
management.endpoint.web.exposure.exclude=beans

# /actuator/beans
endpoints.beans.id=springbeans
endpoints.beans.sensitive=false
endpoints.beans.enabled=true

# /actuator/info
info.app.name=Actuator-App
info.app.description=My Edu App
info.app.version=1.2.3

# Profile
spring.profiles.active=dev,messaging
-Dspring.profiles.active=dev
--spring.profiles.active=dev

java -jar orderms.jar --server.port=8082 --spring.profiles.active=prod
java -jar orderms.jar --server.port=8083
mvn spring-boot:run
mvn spring-boot:run -Dspring-boot.run.arguments=--server.port=8085
