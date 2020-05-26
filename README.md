# Order-Up! - Backend Java Api

* _PostgreSQL database_repository is located at (https://github.com/shaunmccready/order-up-postgresdb)_


*** 
*** 

### Tech stack used:
- [Spring Boot 2.3.0](https://spring.io/projects/spring-boot)
- [Java v11](https://www.oracle.com/technetwork/java/javase/overview/index.html)
- [PostgreSQL v11 for persistence](https://www.postgresql.org/docs/11/index.html)
- [Docker CE](https://www.docker.com/why-docker)
- [JUnit 5](https://junit.org/junit5/)
- [Mockito](https://site.mockito.org/)



### Purpose for the App

For guests of parties to indicate to the hosts what they are eating, drinking, etc. You place your order and the chef/bartender can prepare your item and call you when ready

## Installation

- Root Context path /api
- Running on port 8080

You will need to define the following environment variables:

- ORDERUP_DB_POSTGRES_USER
- ORDERUP_DB_POSTGRES_PASSWORD
- ORDERUP_DB_NAME
- ORDERUP_DB_POSTGRES_PORT
- ORDERUP_DB_POSTGRES_HOST

Build project as a jar in command line using: `mvn clean install`

To run the project from the project's root: `mvn spring-boot:run`
or `java -jar ./target/orderup-java-api.jar`

## Run via Docker
1) First build the image using the Dockerfile, for ex:  `docker build -t orderup-java-api .`

2) Then heres a docker run example:
 
 `docker run --name orderup-java-api --rm -p 8080:8080 -e "ORDERUP_DB_POSTGRES_USER=you_postgres_user" -e "ORDERUP_DB_POSTGRES_PASSWORD=your_pg_password" -e "ORDERUP_DB_NAME=your_pg_db_name" -e "ORDERUP_DB_POSTGRES_HOST=0.0.0.0" -e "ORDERUP_DB_POSTGRES_PORT=5432" -v "your_hosts_directory":/tmp orderup-java-api`

