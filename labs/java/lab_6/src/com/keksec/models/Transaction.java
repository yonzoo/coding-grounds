package com.keksec.models;

import com.keksec.models.exceptions.TransactionFailedException;
import com.keksec.models.exceptions.TransactionNotFoundException;

import java.util.UUID;

public class Transaction {
    private boolean blocked = false;
    private String whyBlocked = "";
    private Account senderAccount, receiverAccount;
    private String transactionKey = generateTransactionKey();
    private double moneySent;

    public Transaction(Account senderAccount, Account receiverAccount, double moneySent) {
        this.senderAccount = senderAccount;
        this.receiverAccount = receiverAccount;
        this.moneySent = moneySent;
    }

    private String generateTransactionKey() {
        return UUID.randomUUID().toString();
    }

    public int getSenderAccountId() {
        return senderAccount.getId();
    }

    public int getReceiverAccountId() {
        return receiverAccount.getId();
    }

    public String getTransactionKey() {
        return transactionKey;
    }

    public double getMoneySent() {
        return moneySent;
    }

    public void setBlock(boolean blocked) {
        this.blocked = blocked;
    }

    public void setWhyBlocked(String whyBlocked) {
        this.whyBlocked = whyBlocked;
    }

    public void perform() {
        if (!blocked) {
            new Thread(() -> {
                synchronized (this) {
                    try {
                        senderAccount.setMoneyAmount(senderAccount.getMoneyAmount() - moneySent, transactionKey);
                        receiverAccount.setMoneyAmount(receiverAccount.getMoneyAmount() + moneySent, transactionKey);
                    } catch (TransactionNotFoundException e) {
                        e.printStackTrace();
                    } catch (TransactionFailedException e) {
                        blocked = true;
                        setWhyBlocked("Transaction was blocked because of potentially dangerous operation");
                        e.printStackTrace();
                    }
                }
            }).start();
        } else {
            System.out.println("Impossible to perform transaction with key: " + transactionKey + " because it is blocked");
            System.out.println(whyBlocked);
        }
    }
}
