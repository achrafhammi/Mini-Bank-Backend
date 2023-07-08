package com.achraf.minibankbackend.services.implementations;

import com.achraf.minibankbackend.models.BankAccount;
import com.achraf.minibankbackend.models.User;
import com.achraf.minibankbackend.repos.BankAccountsRepo;
import com.achraf.minibankbackend.repos.UserRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;


@DataJpaTest
@ExtendWith(MockitoExtension.class)
class BankAccountServiceTest {
    @Mock
    private BankAccountsRepo bankAccountsRepo;
    @Mock
    private UserRepo userRepo;
    private BankAccountService underTestBankAccountsService;
    private final BankAccount testAccount;
    private final User testUser;
    private final List<BankAccount> bankAccounts;

    BankAccountServiceTest() {
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

        bankAccounts = new ArrayList<>(List.of(testAccount));
    }

    @BeforeEach
    void setUp() {
        underTestBankAccountsService = new BankAccountService(bankAccountsRepo, userRepo);
    }

    @Test
    void testGetAccount() {
        //arrange
        when(bankAccountsRepo.getReferenceById(12345L)).thenReturn(testAccount);
        when(userRepo.getReferenceById(1L)).thenReturn(testUser);

        //test
        BankAccount expectedAccount = underTestBankAccountsService.getAccount(12345L);
        User expectedUser = userRepo.getReferenceById(1L);

        //Assertions
        verify(bankAccountsRepo).getReferenceById(12345L);
        assertThat(expectedAccount).isEqualTo(testAccount);
        assertThat(expectedUser).isEqualTo(testUser);
    }

    @Test
    void testCreateAccount() {
        // Mocking
        when(bankAccountsRepo.save(testAccount)).thenReturn(testAccount);
        testUser.setBankAccounts(new HashSet<>());
        when(userRepo.getReferenceById(1L)).thenReturn(testUser);
        testUser.setBankAccounts(new HashSet<>(List.of(testAccount)));

        //test
        ArgumentCaptor<BankAccount> accountArgumentCaptor = ArgumentCaptor.forClass(BankAccount.class);
        BankAccount expectedAccount = underTestBankAccountsService.createAccount(1L, testAccount);
        User updatedUser = userRepo.getReferenceById(1L);

        //Assertions
        verify(userRepo, times(2)).getReferenceById(1L);
        verify(bankAccountsRepo).save(accountArgumentCaptor.capture());
        assertThat(expectedAccount).isEqualTo(accountArgumentCaptor.getValue());
        assertThat(updatedUser).isEqualTo(testUser);
    }

    @Test
    void testGetAllAcounts() {
        //Mocking
        when(bankAccountsRepo.findAll()).thenReturn(bankAccounts);

        //Tests
        List<BankAccount> allAccounts = underTestBankAccountsService.getAllAcounts();

        //Assertions
        assertThat(bankAccounts).isEqualTo(allAccounts);

    }

    @Test
    void updateAccount() {
        //Mocking
        when(bankAccountsRepo.getReferenceById(12345L)).thenReturn(testAccount);
        BankAccount accountToUpdate = new BankAccount();
        accountToUpdate.setPassCode(4321);
        testAccount.setPassCode(4321);
        when(bankAccountsRepo.save(testAccount)).thenReturn(testAccount);


        //Tests
        BankAccount expectedAccount = underTestBankAccountsService.updateAccount(12345L, accountToUpdate);

        //Assertions
        verify(bankAccountsRepo).save(testAccount);
        verify(bankAccountsRepo).getReferenceById(12345L);
        assertThat(expectedAccount).isEqualTo(testAccount);

    }

    @Test
    void deleteAccount() {
        //Test
        underTestBankAccountsService.deleteAccount(12345L);

        //assert
        verify(bankAccountsRepo).deleteById(12345L);
    }

    @Test
    void loginAccount() {
        when(bankAccountsRepo.existsByPassCode(testAccount.getPassCode())).thenReturn(true);

        Boolean loginResult = underTestBankAccountsService.loginAccount(testAccount.getPassCode());

        ArgumentCaptor<Integer> passcodeArgumentCaptor = ArgumentCaptor.forClass(Integer.class);
        verify(bankAccountsRepo).existsByPassCode(passcodeArgumentCaptor.capture());
        Integer capturedPasscode = passcodeArgumentCaptor.getValue();
        assertThat(capturedPasscode).isEqualTo(testAccount.getPassCode());
        assertThat(loginResult).isTrue();
    }
}