package com.achraf.minibankbackend.exceptions;

public class InsufficientAmountException extends RuntimeException{
    public InsufficientAmountException() {
        super("Amount from account that made operation is insufficient to make this operation");
    }
}

