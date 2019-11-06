package com.keksec.models.exceptions;

public class AccountNotFoundException extends Exception {
    public AccountNotFoundException() {
        super("Account was not found");
    }
}
