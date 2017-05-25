
//

package game;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

////is a convenience annotation that adds all of the following:
//@Configuration tags the class as a source of bean definitions
// for the application context.
//@EnableAutoConfiguration tells Spring Boot to start adding beans
// based on classpath settings, other beans, and various property settings.
//        Normally you would add @EnableWebMvc for a Spring MVC app,
// but Spring Boot adds it automatically when it sees spring-webmvc on
// the classpath. This flags the application as a web application and
// activates key behaviors such as setting up a DispatcherServlet.
//@ComponentScan tells Spring to look for other components, configurations,
// and services in the game package, allowing it to find the controllers.

@SpringBootApplication
public class Application {

    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);

    }
}

//run local:
//mvn spring-boot:run

// build the JAR file ./mvn clean package
//java -jar target/gs-rest-service-0.1.0.jar

// если вдруг failed to start end point associated with protocolhandler http-nio-8080 то:
//  lsof -i :<portNumber> | grep LISTEN

// {"gameid":2}