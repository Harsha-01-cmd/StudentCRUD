package in.strikes.crudSpringBootDemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main entry point for the Student CRUD REST API application.
 *
 * @SpringBootApplication combines three annotations:
 *   - @Configuration: marks this class as a bean definition source.
 *   - @EnableAutoConfiguration: auto-configures Spring based on classpath dependencies
 *     (e.g., embeds Tomcat when spring-boot-starter-web is present).
 *   - @ComponentScan: scans this package and sub-packages for @Component, @Service,
 *     @Repository, and @Controller classes.
 *
 * SpringApplication.run() starts the app: loads properties, creates the database
 * connection pool, creates all beans, and starts the embedded Tomcat server on port 8080.
 */
@SpringBootApplication
public class CrudSpringBootDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudSpringBootDemoApplication.class, args);

	}

}
