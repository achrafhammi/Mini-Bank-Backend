package com.achraf.minibankbackend.services.implementations;

import com.achraf.minibankbackend.exceptions.InternalErrorException;
import com.achraf.minibankbackend.models.BankAccount;
import com.achraf.minibankbackend.repos.BankAcountsRepo;
import com.achraf.minibankbackend.services.BankAccountService;
import com.achraf.minibankbackend.utils.ModelUpdater;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class BankAcountService implements BankAccountService {
    private final BankAcountsRepo bankAcountsRepo;
    @Override
    public BankAccount getAccount(Long ID) {
        try {
            return bankAcountsRepo.getReferenceById(ID);
        }catch(Exception e){
            throw new InternalErrorException("Problem at getting an account: "+ e);
        }
    }

    @Override
    public BankAccount createAccount(Long ID, BankAccount newAccount) {
        try {
            return bankAcountsRepo.save(newAccount);
        }catch(Exception e) {
            throw new InternalErrorException("Problem at creating an account: " + e);
        }
    }

    @Override
    public List<BankAccount> getAllAcounts() {
        try {
            return bankAcountsRepo.findAll();
        }catch(Exception e) {
            throw new InternalErrorException("Problem at getting all accounts: " + e);
        }
    }

    @Override
    public BankAccount updateAccount(Long ID, BankAccount updatedAccount) {
        try {
            BankAccount existingAccount = bankAcountsRepo.getReferenceById(ID);
            BankAccount toSaveAccount = ModelUpdater.updateModel(existingAccount, updatedAccount);
            return bankAcountsRepo.save(toSaveAccount);
        }catch(Exception e) {
            throw new InternalErrorException("Problem at updating an account: " + e);
        }
    }

    @Override
    public void deleteAccount(Long ID) {
        try{
            bankAcountsRepo.deleteById(ID);
        }catch (Exception e){
            throw new InternalErrorException("Problem at deleting an account: "+e);
        }

    }
}
