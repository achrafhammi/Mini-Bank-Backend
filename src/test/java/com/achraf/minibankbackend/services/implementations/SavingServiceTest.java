package com.achraf.minibankbackend.services.implementations;

import com.achraf.minibankbackend.models.BankAccount;
import com.achraf.minibankbackend.models.Saving;
import com.achraf.minibankbackend.repos.BankAccountsRepo;
import com.achraf.minibankbackend.repos.SavingsRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@DataJpaTest
@ExtendWith(MockitoExtension.class)
class SavingServiceTest {
    @Mock
    private SavingsRepo savingRepo;
    @Mock
    private BankAccountsRepo bankAccountsRepo;
    private SavingService underTestSavingService;
    private final Saving testSaving;
    private final BankAccount testAccount;
    private final List<Saving> savings;

    public SavingServiceTest() {
        testAccount = new BankAccount();
        testAccount.setIdAccount(12345L);
        testAccount.setPassCode(1234);
        testAccount.setAmount(1000.0f);
        testAccount.setDateCreated(new Date());
        testAccount.setAccountStatus(true);

        testSaving = new Saving();
        testSaving.setIdSavings(1L);
        testSaving.setAmountSavings(10000.0f);
        testSaving.setDateCreated(new Date());
        testSaving.setDateToClose(new Date(System.currentTimeMillis() + 7 * 24 * 60 * 60 * 1000)); // Set date 7 days from now

        testSaving.setBankAccountSavings(testAccount);

        savings = new ArrayList<>(List.of(testSaving));
    }

    @BeforeEach
    void setUp() {
        underTestSavingService = new SavingService(savingRepo, bankAccountsRepo);
    }

    @Test
    void createSaving() {
        //arrange
        when(bankAccountsRepo.getReferenceById(12345L)).thenReturn(testAccount);
        testSaving.setBankAccountSavings(testAccount);
        when(savingRepo.save(testSaving)).thenReturn(testSaving);
        testSaving.setBankAccountSavings(null);

        //Test
        Saving createdSaving = underTestSavingService.createSaving(12345L, testSaving);

        //Assertions
        testSaving.setBankAccountSavings(testAccount);
        testSaving.setBankAccountSavings(testAccount);
        verify(bankAccountsRepo).getReferenceById(12345L);
        verify(savingRepo).save(testSaving);
        assertThat(testSaving).isEqualTo(createdSaving);
    }

    @Test
    void getAllSavings() {
        //Mocking
        when(savingRepo.findAll()).thenReturn(savings);

        //Tests
        List<Saving> allSavings = underTestSavingService.getAllSavings();

        //Assertions
        assertThat(allSavings).isEqualTo(savings);
    }

    @Test
    void updateSaving() {
        //Mocking
        when(savingRepo.getReferenceById(1L)).thenReturn(testSaving);
        Saving savingToUpdate = new Saving();
        savingToUpdate.setAmountSavings(0.5F);
        testSaving.setAmountSavings(5000F);
        when(savingRepo.save(testSaving)).thenReturn(testSaving);


        //Tests
        Saving expectedSaving = underTestSavingService.updateSaving(1L, savingToUpdate);

        //Assertions
        verify(savingRepo).save(testSaving);
        verify(savingRepo).getReferenceById(1L);
        assertThat(expectedSaving).isEqualTo(testSaving);
    }

    @Test
    void deleteSaving() {
        //Test
        underTestSavingService.deleteSaving(1L);

        //assert
        verify(savingRepo).deleteById(1L);
    }

    @Test
    void getSaving() {
        //arrange
        when(savingRepo.getReferenceById(1L)).thenReturn(testSaving);

        //test
        Saving expectedSaving = underTestSavingService.getSaving(1L);

        //Assertions
        verify(savingRepo).getReferenceById(1L);
        assertThat(expectedSaving).isEqualTo(testSaving);
    }
}