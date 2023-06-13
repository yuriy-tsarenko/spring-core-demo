# **SPRING CORE**

[SPRING](https://spring.io) Framework is a Java platform that provides comprehensive infrastructure support for developing Java applications.
Spring handles the infrastructure so you can focus on your application.

### BEAN LIFECYCLE:

1. Load bean definitions
2. Dependency injection
3. Creation and bean initialization
4. Bean destroy

### BEAN CREATION PHASES:

1. Instantiation: This is where everything starts for a bean. Spring instantiates bean objects just like we would manually create a Java object instance.
2. Populating Properties: After instantiating objects, Spring scans the beans that implement Aware interfaces and starts setting relevant properties.
3. Pre-Initialization: Spring’s BeanPostProcessors get into action in this phase. The postProcessBeforeInitialization() methods do their job. Also,
   @PostConstruct annotated methods run right after them.
4. AfterPropertiesSet: Spring executes the afterPropertiesSet() methods of the beans which implement InitializingBean.
5. Custom Initialization: Spring triggers the initialization methods that we defined in the initMethod attribute of our @Beanannotations.
6. Post-Initialization: Spring’s BeanPostProcessors are in action for the second time. This phase triggers the postProcessAfterInitialization() methods.

### BEAN DESTRUCTION PHASES

1. Pre-Destroy: Spring triggers @PreDestroy annotated methods in this phase.
2. Destroy: Spring executes the destroy() methods of DisposableBean implementations.
3. Custom Destruction: We can define custom destruction hooks with the destroy Method attribute in the @Bean annotation and Spring runs them in the last phase.

### Dependency Injection types

1. Constructor based Dependency Injection.
2. Setter based Dependency Injection.
3. Field or Property-based Dependency Injection.

Se also:
[Spring overview](https://docs.spring.io/spring-framework/docs/3.2.x/spring-framework-reference/html/overview.html)
[Dependency Injection](https://www.baeldung.com/spring-dependency-injection)
