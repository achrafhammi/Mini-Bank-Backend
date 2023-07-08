package com.achraf.minibankbackend.services;

import com.achraf.minibankbackend.models.Savings;

import java.util.Set;

public interface SavingsService {
    Savings createSavings(Savings newSavings);
    Set<Savings> getAllSavings(); // for admin
    Savings updateSavings(Long ID, Savings updatedSavings);
    void deleteSavings(Long ID);
    Savings getSavings(Long ID);

}
