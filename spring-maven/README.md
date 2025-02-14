# Spring applications built "from scratch"
Spring tutorial applications where the framework is included from a basic java (maven) project, and not from using Spring Boot.
They are all variations of a conference planning application, from the Pluralsight course *Spring Framework 6 Fundamentals.*

## conference
Basic project build without using Spring to configure or inject dependencies. Hence, there are multiple `new()` statements.

* manually added Spring dependencies to `pom.xml`
* started with model, directory and service tiers
  * repository and service tiers began with hard-coded "Impl" classes, and then the IDE used to extract interfaces

## conference-java
Using the "configuration by java" technique in configuring Spring projects.

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
* scopes in Spring
  * Singleton (default)
	* one instantiation per Spring container / application context
	* `@scope("singleton")`
  * Prototype
	* guarantees a unique instance per request
	* `@scope("prototype")`
  * Request (web only) - one bean per http request
  * Session (web only) - one bean per http session
  * GlobalSession (web only) - one bean per application, regardless of number of clients

## conference-autowired
using `@Autowired` allows the wiring-in of a component (repository) into another component (service-impl) without using an explicit constructor (with arguments) or an explicit setter method
* `@Component` is a Spring stereotype that works like a bean
* `@Repository` denotes a class to be used as a repository object (interfacing a database)
* `@Service` denotes a class where we put business logic
	* `@Controller` is more associated with web-application logic
* add `@ComponentScan({package-name})` to the app-config class to tell Spring to look for these components
* declare `@Service` and `@Repository` in the ..impl classes in the application code
	* no longer need to declare them as beans in the app-config class
	* injection of repository into the service still happens via auto-wiring
* by adding `@Autowired` to the parameterized constructor, we make the application use constructor injection instead of setter injection

## conference-xml
Use an XML file to add Spring configuration, instead of using java code; it's an older method, but maintains separation of concerns
* add the app context XML into src/main/resources/
	* need to declare the xml version and <beans/> namespace
	* define <bean /> components, which are essentially classes
		* they replace the keyword `new`
	* define bean with the implementation class, in code reference the interface
* setter injection & constructor injection still both available, with setters better for existing code
	* for setter injection:
		* in service impl, remove reference to repository impl
		* generate setter for repository member
		* in XML, define the service impl <bean /> with repository impl <property />
	* in the main application, declare a context object that references the XML
		* use the context to load the service by name and class
	* all spring config annotations are out of the app code, instead defined in the XML
		* the app code is still modified as a consequence of spring configuration
* constructor injection
	* adv: guaranteed contract
	* dis: constructor has to be defined for each situation
	* generate constructor in service impl, delete default constructor
	* change definition of bean in XML: `property` --> `constructor-arg` (index-based identifiers, not name-based)

	

