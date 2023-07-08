package com.achraf.minibankbackend.services.implementations;

import com.achraf.minibankbackend.exceptions.InternalErrorException;
import com.achraf.minibankbackend.models.BankAccount;
import com.achraf.minibankbackend.models.User;
import com.achraf.minibankbackend.repos.BankAccountsRepo;
import com.achraf.minibankbackend.repos.UserRepo;
import com.achraf.minibankbackend.utils.ModelUpdater;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class BankAccountService implements com.achraf.minibankbackend.services.BankAccountService {
    private final BankAccountsRepo bankAccountsRepo;
    private final UserRepo userRepo;

    @Override
    public BankAccount getAccount(Long ID) {
        try {
            return bankAccountsRepo.getReferenceById(ID);
        }catch(Exception e){
            throw new InternalErrorException("Problem at getting an account: "+ e);
        }
    }

    @Override
    public BankAccount createAccount(Long idUser, BankAccount newAccount) {
        try {
            User usersAccount = userRepo.getReferenceById(idUser);
            newAccount.setOwnerUser(usersAccount);
            return bankAccountsRepo.save(newAccount);
        }catch(Exception e) {
            throw new InternalErrorException("Problem at creating an account: " + e);
        }
    }

    @Override
    public List<BankAccount> getAllAcounts() {
        try {
            return bankAccountsRepo.findAll();
        }catch(Exception e) {
            throw new InternalErrorException("Problem at getting all accounts: " + e);
        }
    }

    @Override
    public BankAccount updateAccount(Long ID, BankAccount updatedAccount) {
        try {
            BankAccount existingAccount = bankAccountsRepo.getReferenceById(ID);
            BankAccount toSaveAccount = ModelUpdater.updateModel(existingAccount, updatedAccount);
            return bankAccountsRepo.save(toSaveAccount);
        }catch(Exception e) {
            throw new InternalErrorException("Problem at updating an account: " + e);
        }
    }

    @Override
    public void deleteAccount(Long ID) {
        try{
            bankAccountsRepo.deleteById(ID);
        }catch (Exception e){
            throw new InternalErrorException("Problem at deleting an account: "+e);
        }

    }

    @Override
    public Boolean loginAccount(Integer passcode) {
        return bankAccountsRepo.existsByPassCode(passcode);

    }
}
