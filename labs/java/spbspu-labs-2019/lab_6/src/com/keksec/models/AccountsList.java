package com.keksec.models;

import com.keksec.models.exceptions.AccountNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class AccountsList {
    private List<Account> accounts;

    public AccountsList() {
        accounts = new ArrayList<>();
    }

    public AccountsList(List<Account> aList) {
        this.accounts = aList;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void add(Account account) {
        accounts.add(account);
    }

    public Account get(int i) {
        return accounts.get(i);
    }

    public Account findById(int id) throws AccountNotFoundException {
        for (Account account : accounts) {
            if (account.getId() == id) return account;
        }
        throw new AccountNotFoundException();
    }

}
