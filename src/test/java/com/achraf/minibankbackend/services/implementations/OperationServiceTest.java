package com.achraf.minibankbackend.services.implementations;

import com.achraf.minibankbackend.models.BankAccount;
import com.achraf.minibankbackend.models.Operation;
import com.achraf.minibankbackend.repos.BankAccountsRepo;
import com.achraf.minibankbackend.repos.OperationRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@DataJpaTest
@ExtendWith(MockitoExtension.class)
class OperationServiceTest {
    @Mock
    private OperationRepo operationRepo;
    @Mock
    private BankAccountsRepo bankAccountsRepo;
    private OperationService underTestOperationService;
    private final BankAccount testAccountMade;
    private final BankAccount testAccountConcerned;
    private final Operation testOperation;
    private final List<Operation> operations;

    public OperationServiceTest() {
        testAccountMade = new BankAccount();
        testAccountMade.setIdAccount(12345L);
        testAccountMade.setPassCode(1234);
        testAccountMade.setAmount(1000.0F);
        testAccountMade.setDateCreated(new Date());
        testAccountMade.setAccountStatus(true);

        testAccountConcerned = new BankAccount();
        testAccountConcerned.setIdAccount(123456L);
        testAccountConcerned.setPassCode(4321);
        testAccountConcerned.setAmount(500.0F);
        testAccountConcerned.setDateCreated(new Date());
        testAccountConcerned.setAccountStatus(true);

        testOperation = new Operation();
        testOperation.setIdOperation(123456L);
        testOperation.setTypeOperation("Withdrawal");
        testOperation.setMotive("Personal expense");
        testOperation.setAmountOperation(500.0F);

        operations = new ArrayList<>(List.of(testOperation));
    }

    @BeforeEach
    void setUp() {
        underTestOperationService = new OperationService(bankAccountsRepo, operationRepo);
    }

    @Test
    void createOperation() {
        // Arrange
        when(bankAccountsRepo.getReferenceById(12345L)).thenReturn(testAccountMade);
        when(bankAccountsRepo.getReferenceById(123456L)).thenReturn(testAccountConcerned);
        when(operationRepo.save(testOperation)).thenReturn(testOperation);

        // test
        Operation createdOperation = underTestOperationService.createOperation(12345L, 123456L, testOperation);

        // Assertions
        ArgumentCaptor<BankAccount> bankAccountMadeArgumentCaptor = ArgumentCaptor.forClass(BankAccount.class);
        ArgumentCaptor<BankAccount> bankAccountConcernedArgumentCaptor = ArgumentCaptor.forClass(BankAccount.class);

        verify(bankAccountsRepo, times(2)).save(any(BankAccount.class));
        verify(bankAccountsRepo, Mockito.atLeastOnce()).save(bankAccountMadeArgumentCaptor.capture());
        verify(bankAccountsRepo, Mockito.atLeastOnce()).save(bankAccountConcernedArgumentCaptor.capture());

        BankAccount afterAccountMade = bankAccountMadeArgumentCaptor.getAllValues().get(0);
        BankAccount afterAccountConcerned = bankAccountConcernedArgumentCaptor.getAllValues().get(1);

        assertEquals(testAccountMade.getAmount(), afterAccountMade.getAmount());
        assertEquals(testAccountConcerned.getAmount(), afterAccountConcerned.getAmount());

        verify(operationRepo).save(testOperation);
        assertEquals(testOperation, createdOperation);
    }

    @Test
    void getAllOperations() {
        //Mocking
        when(operationRepo.findAll()).thenReturn(operations);

        //Tests
        List<Operation> allOperations = underTestOperationService.getAllOperations();

        //Assertions
        assertThat(allOperations).isEqualTo(operations);
    }

    @Test
    void getOperation() {
        //arrange
        when(operationRepo.getReferenceById(1L)).thenReturn(testOperation);

        //test
        Operation expectedOperation = underTestOperationService.getOperation(1L);

        //Assertions
        verify(operationRepo).getReferenceById(1L);
        assertThat(expectedOperation).isEqualTo(testOperation);
    }

    @Test
    void deleteOperation() {
        //Test
        underTestOperationService.deleteOperation(1L);

        //assert
        verify(operationRepo).deleteById(1L);
    }
}