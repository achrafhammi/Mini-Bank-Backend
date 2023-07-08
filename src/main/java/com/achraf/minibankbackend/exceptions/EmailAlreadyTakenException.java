package com.achraf.minibankbackend.exceptions;

import java.io.Serial;

public class EmailAlreadyTakenException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    public EmailAlreadyTakenException(){
        super("Email already taken!");
    }
}
