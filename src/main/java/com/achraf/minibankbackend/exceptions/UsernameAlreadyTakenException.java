package com.achraf.minibankbackend.exceptions;

import java.io.Serial;

public class UsernameAlreadyTakenException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    public UsernameAlreadyTakenException(){
        super("Username already taken!");
    }
}

