package com.achraf.minibankbackend.services.implementations;

import com.achraf.minibankbackend.exceptions.InternalErrorException;
import com.achraf.minibankbackend.models.BankAccount;
import com.achraf.minibankbackend.models.Loan;
import com.achraf.minibankbackend.repos.BankAccountsRepo;
import com.achraf.minibankbackend.repos.LoansRepo;
import com.achraf.minibankbackend.utils.ModelUpdater;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class LoanService implements com.achraf.minibankbackend.services.LoanService {
    private final LoansRepo loansRepo;
    private final BankAccountsRepo bankAccountsRepo;

    @Override
    public Loan createLoan(Long idBankAccount, Loan newLoan) {
        try {
            BankAccount loanToBankAccount = bankAccountsRepo.getReferenceById(idBankAccount);
            newLoan.setBankAccountLoan(loanToBankAccount);
            return loansRepo.save(newLoan);
        }catch(Exception e){
            throw new InternalErrorException("Problem at creating loan: "+e);
        }
    }

    @Override
    public List<Loan> getAllLoans() {
        try {
            return loansRepo.findAll();
        }catch(Exception e){
            throw new InternalErrorException("Problem at getting all loans: "+e);
        }
    }

    @Override
    public Loan updateLoan(Long ID, Loan updatedLoan) {
        try {
            Loan existingLoan = loansRepo.getReferenceById(ID);
            Loan loanToUpdate = ModelUpdater.updateModel(existingLoan, updatedLoan);
            return loansRepo.save(loanToUpdate);
        }catch(Exception e){
            throw new InternalErrorException("Problem at updating loans: "+e);
        }
    }

    @Override
    public void deleteLoan(Long ID) {
        try {
            loansRepo.deleteById(ID);
        }catch(Exception e){
            throw new InternalErrorException("Problem at deleting loans: "+e);
        }
    }

    @Override
    public Loan getLoan(Long ID) {
        try {
            return loansRepo.getReferenceById(ID);
        }catch(Exception e){
            throw new InternalErrorException("Problem at getting loan by ID: "+ e);
        }
    }


    @Override
    public void approveLoan(Long idBank, Long idLoan) {
        try{
            BankAccount accountToLoan = bankAccountsRepo.getReferenceById(idBank);
            Loan loanToApprove = loansRepo.getReferenceById(idLoan);
            loanToApprove.setStatusRequest("Approved");
            accountToLoan.setAmount(accountToLoan.getAmount()+loanToApprove.getAmountRequest());
            updateLoan(loanToApprove.getIdLoans(), loanToApprove);
            bankAccountsRepo.save(accountToLoan);

        }catch (Exception e){
            throw new InternalErrorException("Problem at approving loan: "+e);
        }
    }
}
