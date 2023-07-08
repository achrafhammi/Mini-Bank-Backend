package com.achraf.minibankbackend.services;

import com.achraf.minibankbackend.models.Loans;
import com.achraf.minibankbackend.models.Operation;
import com.achraf.minibankbackend.models.Savings;

import java.util.Set;

public interface LoansService {
    public Loans createLoan(Loans newLoan);
    public Set<Loans> getAllLoans(); // for admin
    public Loans updateLoans(Long ID, Loans updatedLoan);
    public void deleteLoans(Long ID);
    public Loans getLoans(Long ID);

}
