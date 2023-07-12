package com.achraf.minibankbackend.services;

import com.achraf.minibankbackend.models.Saving;

import java.util.List;

public interface SavingService {
    Saving createSaving(Long idBankAccount, Saving newSaving);
    List<Saving> getAllSavings(); // for admin
    Saving updateSaving(Long ID, Saving updatedSaving);
    void deleteSaving(Long ID);
    Saving getSaving(Long ID);

}
