package com.achraf.minibankbackend.services.implementations;

import com.achraf.minibankbackend.models.BankAccount;
import com.achraf.minibankbackend.models.User;
import com.achraf.minibankbackend.repos.BankAcountsRepo;
import com.achraf.minibankbackend.repos.UserRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;
import java.util.HashSet;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@DataJpaTest
@ExtendWith(MockitoExtension.class)
class BankAcountServiceTest {
    @Mock
    private BankAcountsRepo bankAcountsRepo;
    @Mock
    private UserRepo userRepo;
    private BankAcountService underTestBankAccountsService;
    private UserService userService;

    private final BankAccount testAccount;
    private final User testUser;

    BankAcountServiceTest() {
        testAccount = new BankAccount();
        testAccount.setIdAccount(12345L);
        testAccount.setPassCode(1234);
        testAccount.setAmount(1000.0f);
        testAccount.setDateCreated(new Date());
        testAccount.setAccountStatus(true);

        testUser = new User();
        // Set fake data for User object
        testUser.setFirstName("John");
        testUser.setLastName("Doe");
        testUser.setDateOfBirth(new java.sql.Date(System.currentTimeMillis()));
        testUser.setUsername("johndoe");
        testUser.setEmail("johndoe@example.com");
        testUser.setPassword("password123");
        testUser.setAdmin(false);
        testUser.setBankAccounts(new HashSet<>(List.of(testAccount)) {
        });


        testAccount.setOwnerUser(testUser);
    }

    @BeforeEach
    void setUp() {
        underTestBankAccountsService = new BankAcountService(bankAcountsRepo);
        userService = new UserService(userRepo);
    }

    @Test
    void testGetAccount() {
        //arrange
        when(bankAcountsRepo.getReferenceById(1L)).thenReturn(testAccount);
        when(userRepo.getReferenceById(1L)).thenReturn(testUser);

        //test
        BankAccount expectedAccount = underTestBankAccountsService.getAccount(1L);
        User expectedUser = userService.getUser(1L);

        //Assertions
        verify(bankAcountsRepo).getReferenceById(1L);
        assertThat(expectedAccount).isEqualTo(testAccount);
        assertThat(expectedUser).isEqualTo(testUser);
    }

    @Test
    void testCreateAccount() {

    }

    @Test
    void getAllAcounts() {
    }

    @Test
    void updateAccount() {
    }

    @Test
    void deleteAccount() {
    }
}