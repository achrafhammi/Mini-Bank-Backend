package com.achraf.minibankbackend.services;

import com.achraf.minibankbackend.models.BankAccount;

import java.util.List;

public interface BankAccountService {
    public BankAccount getAccount(Long ID);
    public BankAccount createAccount(Long idUser, BankAccount newAccount);
    public List<BankAccount> getAllAcounts(); // for admin
    public BankAccount updateAccount(Long ID, BankAccount updatedAccount);
    public void deleteAccount(Long ID);
}
