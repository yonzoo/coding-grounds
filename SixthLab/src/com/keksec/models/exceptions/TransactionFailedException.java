package com.keksec.models.exceptions;

public class TransactionFailedException extends Exception {
    public TransactionFailedException() {
        super("Transaction failed, due to possible dangerous operation");
    }
}
