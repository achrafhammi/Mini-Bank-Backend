package com.achraf.minibankbackend.services;

import com.achraf.minibankbackend.models.Loan;

import java.util.List;

public interface LoanService {
    Loan createLoan(Long idBankAccount, Loan newLoan);
    List<Loan> getAllLoans(); // for admin
    Loan updateLoan(Long ID, Loan updatedLoan);
    void deleteLoan(Long ID);
    Loan getLoan(Long ID);
    void approveLoan(Long idBank, Long idLoan);
}
