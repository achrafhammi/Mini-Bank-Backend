package com.achraf.minibankbackend.services;

import com.achraf.minibankbackend.models.Savings;

import java.util.Set;

public interface SavingsService {
    public Savings createSavings(Savings newSavings);
    public Set<Savings> getAllSavings(); // for admin
    public Savings updateSavings(Long ID, Savings updatedSavings);
    public void deleteSavings(Long ID);
    public Savings getSavings(Long ID);

}
