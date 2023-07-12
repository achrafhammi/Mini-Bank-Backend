package com.achraf.minibankbackend.services.implementations;

import com.achraf.minibankbackend.models.BankAccount;
import com.achraf.minibankbackend.models.Loan;
import com.achraf.minibankbackend.repos.BankAccountsRepo;
import com.achraf.minibankbackend.repos.LoansRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@DataJpaTest
@ExtendWith(MockitoExtension.class)
class LoanServiceTest {
    @Mock
    private LoansRepo loansRepo;
    @Mock
    private BankAccountsRepo bankAccountsRepo;
    private LoanService underTestLoanService;
    private final BankAccount testAccount;
    private final Loan testLoan;
    private final List<Loan> loans;

    public LoanServiceTest() {
        testAccount = new BankAccount();
        testAccount.setIdAccount(12345L);
        testAccount.setPassCode(1234);
        testAccount.setAmount(1000.0f);
        testAccount.setDateCreated(new Date());
        testAccount.setAccountStatus(true);

        testLoan = new Loan();
        testLoan.setIdLoans(1L);
        testLoan.setAmountRequest(5000.0f);
        testLoan.setReasonRequest("Emergency medical expenses");
        testLoan.setStatusRequest("Pending");
        testLoan.setInterest(8.5f);
        testLoan.setPaymentPerMonth(500.0f);
        testLoan.setDateRequest(new Date());

        loans = new ArrayList<>(List.of(testLoan));

    }

    @BeforeEach
    void setUp() {
        underTestLoanService = new LoanService(loansRepo, bankAccountsRepo);
    }

    @Test
    void createLoan() {
        //arrange
        when(bankAccountsRepo.getReferenceById(12345L)).thenReturn(testAccount);
        testLoan.setBankAccountLoan(testAccount);
        when(loansRepo.save(testLoan)).thenReturn(testLoan);
        testLoan.setBankAccountLoan(null);

        //Test
        Loan createdLoan = underTestLoanService.createLoan(12345L, testLoan);

        //Assertions
        testLoan.setBankAccountLoan(testAccount);
        testLoan.setBankAccountLoan(testAccount);
        verify(bankAccountsRepo).getReferenceById(12345L);
        verify(loansRepo).save(testLoan);
        assertThat(testLoan).isEqualTo(createdLoan);
    }

    @Test
    void getAllLoans() {
        //Mocking
        when(loansRepo.findAll()).thenReturn(loans);

        //Tests
        List<Loan> allLoans = underTestLoanService.getAllLoans();

        //Assertions
        assertThat(allLoans).isEqualTo(allLoans);
    }

    @Test
    void updateLoan() {
        //Mocking
        when(loansRepo.getReferenceById(1L)).thenReturn(testLoan);
        Loan loanToUpdate = new Loan();
        loanToUpdate.setInterest(0.5F);
        testLoan.setInterest(0.5F);
        when(loansRepo.save(testLoan)).thenReturn(testLoan);


        //Tests
        Loan expectedLoan = underTestLoanService.updateLoan(1L, loanToUpdate);

        //Assertions
        verify(loansRepo).save(testLoan);
        verify(loansRepo).getReferenceById(1L);
        assertThat(expectedLoan).isEqualTo(testLoan);
    }

    @Test
    void deleteLoan() {
        //Test
        underTestLoanService.deleteLoan(1L);

        //assert
        verify(loansRepo).deleteById(1L);
    }

    @Test
    void getLoan() {
        //arrange
        when(loansRepo.getReferenceById(1L)).thenReturn(testLoan);

        //test
        Loan expectedAccount = underTestLoanService.getLoan(1L);

        //Assertions
        verify(loansRepo).getReferenceById(1L);
        assertThat(expectedAccount).isEqualTo(testLoan);
    }

    @Test
    void approveLoan() {
        //arrange
        when(bankAccountsRepo.getReferenceById(12345L)).thenReturn(testAccount);
        when(loansRepo.getReferenceById(1L)).thenReturn(testLoan);

        //test
        underTestLoanService.approveLoan(testAccount.getIdAccount(),testLoan.getIdLoans());
        ArgumentCaptor<Loan> loanArgumentCaptor = ArgumentCaptor.forClass(Loan.class);
        ArgumentCaptor<BankAccount> accountArgumentCaptor = ArgumentCaptor.forClass(BankAccount.class);

        //assert
        verify(bankAccountsRepo).save(accountArgumentCaptor.capture());
        verify(loansRepo).save(loanArgumentCaptor.capture());
        testAccount.setAmount(testAccount.getAmount()+testLoan.getAmountRequest());
        testLoan.setStatusRequest("Approved");
        Loan capturedLoan = loanArgumentCaptor.getValue();
        BankAccount capturedAccount = accountArgumentCaptor.getValue();
        assertThat(capturedLoan).isEqualTo(testLoan);
        assertThat(capturedAccount).isEqualTo(testAccount);
    }
}