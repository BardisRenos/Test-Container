# test-container

### Intro

This repository shows how to test the repository layer with **Testcontainer**. 

## About Testcontainers

Testcontainers is a Java library that supports JUnit tests, providing lightweight, throwaway instances of common databases, Selenium web browsers, or anything else that can run in a Docker container.

## Implementation 

As the first step is to declaire the annotation on the test class, **@Testcontainers**


```java
  @DataJpaTest
  @Testcontainers
  @AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
  class UserRepositoryTest
```

## Setting the container by the Database.

```java
  @Container
  private static final MySQLContainer mySQLContainer = new MySQLContainer("mysql:latest");
```

## Setting the credentials of the database 

By adding the credentials of the MySQL database from the file application-test.properties.

```java
  @DynamicPropertySource
  public static void overrideProps(DynamicPropertyRegistry registry) {
      registry.add("application-test.properties/spring.datasource.url", mySQLContainer::getJdbcUrl);
      registry.add("application-test.properties/spring.datasource.username", mySQLContainer::getUsername);
      registry.add("application-test.properties/spring.datasource.password", mySQLContainer::getPassword);
  }

  static {
      mySQLContainer.start();
  }

``
