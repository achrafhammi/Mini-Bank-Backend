package com.achraf.minibankbackend.services.implementations;

import com.achraf.minibankbackend.exceptions.InternalErrorException;
import com.achraf.minibankbackend.models.BankAccount;
import com.achraf.minibankbackend.models.Operation;
import com.achraf.minibankbackend.repos.BankAccountsRepo;
import com.achraf.minibankbackend.repos.OperationRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class OperationService implements com.achraf.minibankbackend.services.OperationService {
    private final BankAccountsRepo bankAccountsRepo;
    private final OperationRepo operationRepo;
    @Override
    public Operation createOperation(Long idAccountMade, Long idAccountConcerned, Operation newOperation) {
        try{
            BankAccount bankAccountMade = bankAccountsRepo.getReferenceById(idAccountMade);
            BankAccount bankAccountConcerned = bankAccountsRepo.getReferenceById(idAccountConcerned);
            bankAccountMade.setAmount(bankAccountMade.getAmount()-newOperation.getAmountOperation());
            bankAccountConcerned.setAmount(bankAccountConcerned.getAmount()+newOperation.getAmountOperation());
            newOperation.setBankAccountMade(bankAccountMade);
            newOperation.setBankAccountConcerned(bankAccountConcerned);
            return operationRepo.save(newOperation);
        }catch (Exception e){
            throw new InternalErrorException("Problem at creating operation: "+e);
        }
    }

    @Override
    public List<Operation> getAllOperations() {
        try{
            return operationRepo.findAll();
        }catch (Exception e){
            throw new InternalErrorException("Problem at getting all operations: "+e);
        }
    }

    @Override
    public Operation getOperation(Long ID) {
        try{
            return operationRepo.getReferenceById(ID);
        }catch (Exception e){
            throw new InternalErrorException("Problem at getting operation: "+e);
        }
    }


    @Override
    public void deleteOperation(Long ID) {
        try{
            operationRepo.deleteById(ID);
        }catch (Exception e){
            throw new InternalErrorException("Problem at deleting operation: 0530715000"+e);
        }
    }
}
