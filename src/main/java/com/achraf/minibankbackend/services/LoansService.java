package com.achraf.minibankbackend.services;

import com.achraf.minibankbackend.models.Loans;

import java.util.Set;

public interface LoansService {
    Loans createLoan(Loans newLoan);
    Set<Loans> getAllLoans(); // for admin
    Loans updateLoans(Long ID, Loans updatedLoan);
    void deleteLoans(Long ID);
    Loans getLoans(Long ID);

}
