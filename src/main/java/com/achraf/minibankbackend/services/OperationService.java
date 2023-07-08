package com.achraf.minibankbackend.services;

import com.achraf.minibankbackend.models.Operation;

import java.util.Set;

public interface OperationService {
    Operation createOperation(Operation newOperation);
    Set<Operation> getAllOperations(); // for admin
    Operation getOperation(Long ID);
    Operation updateOperation(Long ID, Operation updatedOperation);
    void deleteOperation(Long ID);
}
