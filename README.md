# test-container

### Intro

This repository shows how to test the repository layer with **Testcontainer**. 

## About Testcontainers

Testcontainers is a Java library that supports JUnit tests, providing lightweight, throwaway instances of common databases, Selenium web browsers, or anything else that can run in a Docker container.

## Implementation 




```java
  @DataJpaTest
  @Testcontainers
  @AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
  class UserRepositoryTest
```
