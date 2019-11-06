package com.keksec.io;

import com.keksec.models.Account;
import com.keksec.models.AccountsList;
import com.keksec.models.Transaction;
import com.keksec.models.TransactionList;

import java.util.List;

public class OutputProcessor {
    public static void printAccounts(AccountsList accounts) {
        List<Account> acs = accounts.getAccounts();
        acs.forEach(account -> {
            System.out.print("\n");
            System.out.println("Name: " + account.getName());
            System.out.println("Id: " + account.getId());
            System.out.println("Money amount: " + account.getMoneyAmount());
            System.out.print("\n");
        });
    }

    public static void printTransactions(TransactionList transactions) {
        List<Transaction> trs = transactions.getTransactions();
        trs.forEach(transaction -> {
            System.out.print("\n");
            System.out.println("Sender id: " + transaction.getSenderAccountId());
            System.out.println("Receiver id: " + transaction.getReceiverAccountId());
            System.out.println("Money sent: " + transaction.getMoneySent());
            System.out.print("\n");
        });
    }
}
