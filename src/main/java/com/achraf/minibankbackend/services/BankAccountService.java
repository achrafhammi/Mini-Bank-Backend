package com.achraf.minibankbackend.services;

import com.achraf.minibankbackend.models.BankAccount;

import java.util.List;

public interface BankAccountService {
    BankAccount getAccount(Long ID);
    BankAccount createAccount(Long idUser, BankAccount newAccount);
    List<BankAccount> getAllAcounts(); // for admin
    BankAccount updateAccount(Long ID, BankAccount updatedAccount);
    void deleteAccount(Long ID);
    Boolean loginAccount(Integer passcode);

}
