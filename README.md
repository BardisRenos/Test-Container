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

```

## Test cases

Setting the test cases. 

```java
    @BeforeEach
    void init() {
        User user1 = new User(1L, "Renos", "Renos87", "Bardis", "renos@gmail.com");
        User user2 = new User(2L, "Nikos", "Nikos91", "Papas", "nikos@gmail.com");

        List<User> listOfUser = new ArrayList<>();
        listOfUser.add(user1);
        listOfUser.add(user2);

        userRepository.saveAll(listOfUser);
    }


    @AfterEach
    void delete() {
        userRepository.deleteAll();
    }
    
```

```java
    @Test
    void saveUserTest() {
        User user = new User(3L, "John", "John75", "Smith", "john@gmail.com");
        User userRes = userRepository.save(user);

        assertThat(user).usingRecursiveComparison().isEqualTo(userRes);
    }

    @Test
    void getAllUsersTest() {
        List<User> listRes = userRepository.findAll();

        assertThat(listRes).hasSize(2);
    }
```
