package com.example.testcontainer.testcontainer.dao;

import com.example.testcontainer.testcontainer.resource.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByUserId(Long id);

    User findUserByLastName(String lastName);
}
