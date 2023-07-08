package com.achraf.minibankbackend.repos;

import com.achraf.minibankbackend.models.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.sql.Date;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class UserRepoTest {
    @Autowired
    private UserRepo underTest;
    @Test
    void getUserByUsernameAndPassword() {
        User newUser = new User();
        newUser.setFirstName("John");
        newUser.setLastName("Doe");
        newUser.setDateOfBirth(new Date(System.currentTimeMillis()));
        newUser.setUsername("johndoe");
        newUser.setEmail("johndoe@example.com");
        newUser.setPassword("password123");
        newUser.setAdmin(false);
        underTest.save(newUser);

        //when
        Boolean login = underTest.existsByUsernameAndPassword("johndoe", "password123");

        assertThat(login).isTrue();
    }
}