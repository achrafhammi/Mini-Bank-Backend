package com.achraf.minibankbackend.services.implementations;

import com.achraf.minibankbackend.models.User;
import com.achraf.minibankbackend.repos.UserRepo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.sql.Date;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@DataJpaTest
@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    private UserRepo userRepo;
    private UserService underTestUserService;
    private final User newUserTest;

    public UserServiceTest() {
        newUserTest = new User();
        newUserTest.setIdUser(1L);
        newUserTest.setFirstName("John");
        newUserTest.setLastName("Doe");
        newUserTest.setDateOfBirth(new Date(System.currentTimeMillis()));
        newUserTest.setUsername("johndoe");
        newUserTest.setEmail("johndoe@example.com");
        newUserTest.setPassword("password123");
        newUserTest.setAdmin(false);

    }

    @BeforeEach
    void setUp() {
        underTestUserService = new UserService(userRepo);
    }

    @Test
    void testDeleteUser() {
        // Arrange
        Long userId = 1L;

        // Act
        underTestUserService.deleteUser(userId);

        // Assert
        verify(userRepo).deleteById(userId);
    }

    @Test
    void testRegisterUser() {
        underTestUserService.registerUser(newUserTest);

        ArgumentCaptor<User> userArgumentCaptor = ArgumentCaptor.forClass(User.class);

        verify(userRepo).save(userArgumentCaptor.capture());
        User capturedUser = userArgumentCaptor.getValue();

        assertThat(capturedUser).isEqualTo(newUserTest);
    }

    @Test
    void testGetUser() {
        when(userRepo.getReferenceById(1L)).thenReturn(newUserTest);
        User testUser = underTestUserService.getUser(1L);
        verify(userRepo).getReferenceById(1L);
        assertThat(testUser).isEqualTo(newUserTest);
    }

    @Test
    void testLoginUser() {
        underTestUserService.loginUser(newUserTest.getUsername(), newUserTest.getPassword());
        ArgumentCaptor<String> usernameArgumentCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> passwordArgumentCaptor = ArgumentCaptor.forClass(String.class);

        verify(userRepo).existsByUsernameAndPassword(usernameArgumentCaptor.capture(), passwordArgumentCaptor.capture());
        String capturedUsername = usernameArgumentCaptor.getValue();
        String capturedPassword = passwordArgumentCaptor.getValue();

        assertThat(capturedUsername).isEqualTo(newUserTest.getUsername());
        assertThat(capturedPassword).isEqualTo(newUserTest.getPassword());
    }


    @Test
    void testUpdateUser() {
        // Arrange
        when(userRepo.getReferenceById(1L)).thenReturn(newUserTest);
        User expectedUser = newUserTest;
        expectedUser.setFirstName("Achrafff");
        when(userRepo.save(expectedUser)).thenReturn(expectedUser);

        //Setting
        User toUpdate = new User();
        toUpdate.setFirstName("Achrafff");
        User newUpdated = underTestUserService.updateUser(1L, toUpdate);

        //Assertions
        assertThat(newUpdated).isEqualTo(newUserTest);

    }


}