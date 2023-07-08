package com.achraf.minibankbackend.repos;

import com.achraf.minibankbackend.models.BankAccount;
import com.achraf.minibankbackend.models.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class BankAccountsRepoTest {
    @Autowired
    private BankAccountsRepo underTest;
    @Autowired
    private UserRepo userRepo;

    @Test
    void existsByPassCode() {
        BankAccount testAccount = new BankAccount();
        testAccount.setIdAccount(12345L);
        testAccount.setPassCode(1234);
        testAccount.setAmount(1000.0f);
        testAccount.setDateCreated(new Date());
        testAccount.setAccountStatus(true);

        User newUserTest = new User();
        newUserTest.setIdUser(1L);
        newUserTest.setFirstName("John");
        newUserTest.setLastName("Doe");
        newUserTest.setDateOfBirth(new java.sql.Date(System.currentTimeMillis()));
        newUserTest.setUsername("johndoe");
        newUserTest.setEmail("johndoe@example.com");
        newUserTest.setPassword("password123");
        newUserTest.setAdmin(false);

        userRepo.save(newUserTest);

        testAccount.setOwnerUser(newUserTest);

        underTest.save(testAccount);

        //when
        Boolean login = underTest.existsByPassCode(1234);

        assertThat(login).isTrue();
    }
}