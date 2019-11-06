package com.keksec;

import com.keksec.controllers.MainController;

public class Main {

    public static void main(String[] args) {
        System.out.println("-----INITIAL TRANSACTIONS AND ACCOUNTS-----");
        MainController.printTransactions();
        MainController.printAccounts();
        MainController.start();
        System.out.println("-----TRANSACTIONS WERE PERFORMED-----");
        MainController.printAccounts();
    }
}
