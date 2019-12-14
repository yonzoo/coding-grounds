package com.keksec.models;

import com.keksec.models.exceptions.TransactionNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class TransactionList {
    private List<Transaction> transactionList;

    public TransactionList() {
        transactionList = new ArrayList<>();
    }

    public TransactionList(List<Transaction> transactionList) {
        this.transactionList = transactionList;
    }

    public List<Transaction> getTransactions() {
        return transactionList;
    }

    public int getTransactionIndex(String transactionKey) throws TransactionNotFoundException {
        int index = -1;
        for (int i = 0; i < transactionList.size(); i++) {
            if (transactionList.get(i).getTransactionKey().equals(transactionKey)) index = i;
        }
        if (index == -1)
            throw new TransactionNotFoundException("Transaction with key: " + transactionKey + " was not found");
        return index;
    }

    public Transaction get(int transactionIndex) throws TransactionNotFoundException {
        if (transactionIndex < 0 || transactionIndex >= transactionList.size())
            throw new TransactionNotFoundException("Transaction with index: " + transactionIndex + " was not found in Transaction List");
        return transactionList.get(transactionIndex);
    }

    public void add(Transaction transaction) {
        transactionList.add(transaction);
    }
}
