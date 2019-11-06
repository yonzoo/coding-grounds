package com.keksec.models;

import com.keksec.models.exceptions.TransactionFailedException;
import com.keksec.models.exceptions.TransactionNotFoundException;

import java.util.List;

public class Account {
    private double moneyAmount;
    private int id;
    private String name;
    private TransactionList transactionList;

    public Account(double moneyAmount, int id, String name) {
        this.moneyAmount = moneyAmount;
        this.id = id;
        this.name = name;
        transactionList = new TransactionList();
    }

    public Account(double moneyAmount, int id, String name, List<Transaction> tList) {
        this.moneyAmount = moneyAmount;
        this.id = id;
        this.name = name;
        this.transactionList = new TransactionList(tList);
    }

    public double getMoneyAmount() {
        return moneyAmount;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void addTransaction(Transaction transaction) {
        transactionList.add(transaction);
    }

    public void setMoneyAmount(double newMoneyAmount, String transactionKey) throws TransactionNotFoundException, TransactionFailedException {
        int transactionIndex = transactionList.getTransactionIndex(transactionKey);
        checkNewMoneyAmountValidity(newMoneyAmount, transactionList.get(transactionIndex));
        this.moneyAmount = newMoneyAmount;
    }

    private void checkNewMoneyAmountValidity(double newMoneyAmount, Transaction transaction) throws TransactionFailedException {
        if (transaction.getSenderAccountId() == id) {
            if (newMoneyAmount != moneyAmount - transaction.getMoneySent()) throw new TransactionFailedException();
        }
        else if (transaction.getReceiverAccountId() == id) {
            if (newMoneyAmount != moneyAmount + transaction.getMoneySent()) throw new TransactionFailedException();
        }
        else {
            throw new TransactionFailedException();
        }
    }
}
