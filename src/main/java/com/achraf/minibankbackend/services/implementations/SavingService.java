package com.achraf.minibankbackend.services.implementations;

import com.achraf.minibankbackend.exceptions.InternalErrorException;
import com.achraf.minibankbackend.models.BankAccount;
import com.achraf.minibankbackend.models.Saving;
import com.achraf.minibankbackend.repos.BankAccountsRepo;
import com.achraf.minibankbackend.repos.SavingsRepo;
import com.achraf.minibankbackend.utils.ModelUpdater;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class SavingService implements com.achraf.minibankbackend.services.SavingService {
    private final SavingsRepo savingsRepo;
    private final BankAccountsRepo bankAccountsRepo;

    @Override
    public Saving createSaving(Long idBankAccount, Saving newSaving) {
        try {
            BankAccount accountSavings = bankAccountsRepo.getReferenceById(idBankAccount);
            newSaving.setBankAccountSavings(accountSavings);
            return savingsRepo.save(newSaving);
        }catch (Exception e){
            throw new InternalErrorException("Problem at creating saving: "+e);
        }
    }

    @Override
    public List<Saving> getAllSavings() {
        try {
            return savingsRepo.findAll();
        }catch (Exception e){
            throw new InternalErrorException("Problem at getting all savings: "+e);
        }
    }

    @Override
    public Saving updateSaving(Long ID, Saving updatedSaving) {
        try {
            Saving existingSaving = savingsRepo.getReferenceById(ID);
            Saving toUpdateSaving = ModelUpdater.updateModel(existingSaving, updatedSaving);
            return savingsRepo.save(toUpdateSaving);
        }catch (Exception e){
            throw new InternalErrorException("Problem at updating saving: "+e);
        }
    }

    @Override
    public void deleteSaving(Long ID) {
        try {
            savingsRepo.deleteById(ID);
        }catch (Exception e){
            throw new InternalErrorException("Problem at deleting saving: "+e);
        }
    }

    @Override
    public Saving getSaving(Long ID) {
        try {
            return savingsRepo.getReferenceById(ID);
        }catch (Exception e){
            throw new InternalErrorException("Problem at getting a saving: "+e);
        }
    }
}
