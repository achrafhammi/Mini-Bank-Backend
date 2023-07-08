package com.achraf.minibankbackend.exceptions;

import java.io.Serial;

public class LoginException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    public LoginException(){
        super("Username/Password don't exist!");
    }
}
