package com.keksec.controllers;

import com.keksec.io.OutputProcessor;
import com.keksec.io.Reader;
import com.keksec.models.AccountsList;
import com.keksec.models.Transaction;
import com.keksec.models.Account;
import com.keksec.models.TransactionList;

import java.util.Scanner;

public class MainController {
    private static AccountsList accounts;
    private static TransactionList transactions;

    static {
        Account walter = new Account(1000000, 1, "Walter White");
        Account jessie = new Account(390.21, 2, "Jessie Pinkman");
        Account saul = new Account(6969, 3, "Saul Goodman");
        Account randomDealer = new Account(50900, 4, "Top Secret");
        accounts = new AccountsList();
        accounts.add(walter);
        accounts.add(jessie);
        accounts.add(saul);
        accounts.add(randomDealer);
        Reader reader = new Reader(accounts);
        transactions = reader.read();
    }

    public static void start() {
        transactions.getTransactions().forEach(Transaction::perform);
    }

    public static void printAccounts() {
        OutputProcessor.printAccounts(accounts);
    }

    public static void printTransactions() {
        OutputProcessor.printTransactions(transactions);
    }
}
