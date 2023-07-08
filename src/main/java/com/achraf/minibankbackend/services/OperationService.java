package com.achraf.minibankbackend.services;

import com.achraf.minibankbackend.models.Operation;

import java.util.Set;

public interface OperationService {
    public Operation createOperation(Operation newOperation);
    public Set<Operation> getAllOperations(); // for admin
    public Operation getOperation(Long ID);
    public Operation updateOperation(Long ID, Operation updatedOperation);
    public void deleteOperation(Long ID);
}
