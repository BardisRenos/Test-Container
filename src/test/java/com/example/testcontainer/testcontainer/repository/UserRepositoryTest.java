package com.example.testcontainer.testcontainer.repository;

import com.example.testcontainer.testcontainer.dao.UserRepository;
import com.example.testcontainer.testcontainer.resource.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Container
    private static final MySQLContainer mySQLContainer = new MySQLContainer("mysql:latest");

    @DynamicPropertySource
    public static void overrideProps(DynamicPropertyRegistry registry) {
        registry.add("application-test.properties/spring.datasource.url", mySQLContainer::getJdbcUrl);
        registry.add("application-test.properties/spring.datasource.username", mySQLContainer::getUsername);
        registry.add("application-test.properties/spring.datasource.password", mySQLContainer::getPassword);
    }

    static {
        mySQLContainer.start();
    }

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

    @Test
    void saveUserTest() {
        User user = new User(1L, "Renos", "Renos87", "Bardis", "renos@gmail.com");
        User userRes = userRepository.save(user);

        assertThat(user).usingRecursiveComparison().isEqualTo(userRes);
    }

    @Test
    void getAllUsersTest() {
        List<User> listRes = userRepository.findAll();

        assertThat(listRes).hasSize(2);
    }

    @Test
    void getUserByLastName() {
        User user = userRepository.findUserByLastName("Bardis");

        assertThat(user.getLastName()).isEqualTo("Bardis");
    }

    @Test
    void getUserByUserId() {
        User user = userRepository.findUserByUserId(1L);

        assertThat(user.getLastName()).isEqualTo("Bardis");
    }
}