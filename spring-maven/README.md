# Spring applications built "from scratch"
Spring tutorial applications where the framework is included from a basic java (maven) project, and not from using Spring Boot.
They are all variations of a conference planning application, from the Pluralsight course *Spring Framework 6 Fundamentals.*

## conference
Basic project build without using Spring to configure or inject dependencies. Hence, there are multiple `new()` statements.

* manually added Spring dependencies to `pom.xml`
* started with model, directory and service tiers
  * repository and service tiers began with hard-coded "Impl" classes, and then the IDE used to extract interfaces

## conference-java
Using the "configuration by java," the present state of the art in configuring Spring projects.

* add a configuration POJO `AppConfig` with the `@Configuration` annotation
* add a bean with `@Bean`, making a return an implementation of the SpeakerService interface
  * registered in the bean registry in Spring, under the name given by `name=`
  * each bean is a singleton, and the @Bean-annotated function (`getSpeakerService`) is only executed the first time it's called
* setter injection as a method call: calling a setter on a bean
  * once the config class is set up, the references to the ..impl classes can be removed from the application code
  * need to set up the application class to use the config class as well
* constructor injection
  * declare beans as before
  * instead of calling the setters, call the required constructors instead
  	* not passing objects around anymore
  * preferred way, esp. if we want to used constructors to guarantee a way in which objects are created 

* Scopes in Spring
  * Singleton (default)
	* one instantiation per Spring container / application context
	* `@scope("singleton")`
  * Prototype
  * Request (web only)
  * Session (web only)
  * Global (web only)
