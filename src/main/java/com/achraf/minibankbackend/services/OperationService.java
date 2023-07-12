package com.achraf.minibankbackend.services;

import com.achraf.minibankbackend.models.Operation;

import java.util.List;

public interface OperationService {
    Operation createOperation(Long idAccountMade, Long idAccountConcerned, Operation newOperation);
    List<Operation> getAllOperations(); // for admin
    Operation getOperation(Long ID);
    void deleteOperation(Long ID);
}
