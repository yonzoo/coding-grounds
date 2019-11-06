package com.keksec.io;

import com.keksec.models.AccountsList;
import com.keksec.models.Transaction;
import com.keksec.models.Account;
import com.keksec.models.TransactionList;
import com.keksec.models.exceptions.AccountNotFoundException;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class Reader {
    private AccountsList accounts;
    private TransactionList transactions;
    private static Scanner scn = new Scanner(System.in);

    public Reader(AccountsList accounts) {
        this.accounts = accounts;
        transactions = new TransactionList();
    }

    public TransactionList read() {
        String field = "";
        while (!field.equals("1") && !field.equals("2")) {
            System.out.println("Press 1 to read transactions from .txt file");
            System.out.println("Press 2 to read transactions from .xml file");
            field = scn.nextLine();
        }
        try {
            if (field.equals("1")) transactions = readTXTFile();
            else transactions = readXMLFile();
        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }
        return transactions;
    }

    public TransactionList readTXTFile() throws IOException {
        Path path = Paths.get("./resources/transactions.txt");
        Stream<String> lines = Files.lines(path);
        lines.forEach(s -> {
            List<String> props = Arrays.asList(s.replaceAll(" ", "").split(","));
            String receiverAccountId = props.get(0).split(":")[1];
            String senderAccountId = props.get(1).split(":")[1];
            String moneyAmount = props.get(2).split(":")[1];
            initTransaction(senderAccountId, receiverAccountId, moneyAmount);
        });
        return transactions;
    }

    private TransactionList readXMLFile() throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        factory.newSAXParser().parse(new File("./resources/transactions.xml"), new XMLHandler());
        return transactions;
    }

    private class XMLHandler extends DefaultHandler {
        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            if (qName.equals("Transaction")) {
                initTransaction(attributes.getValue("senderAccountId"), attributes.getValue("receiverAccountId"), attributes.getValue("moneySent"));
            }
        }
    }

    public void initTransaction(String senderAccountId, String receiverAccountId, String moneyAmount) {
        try {
            Account senderAccount = accounts.findById(Integer.parseInt(senderAccountId));
            Account receiverAccount = accounts.findById(Integer.parseInt(receiverAccountId));
            Transaction transaction = new Transaction(senderAccount, receiverAccount, Double.parseDouble(moneyAmount));
            if (senderAccount.getMoneyAmount() - Double.parseDouble(moneyAmount) < 0) {
                transaction.setBlock(true);
                transaction.setWhyBlocked("Transaction was blocked because sender account does not have enough money");
            }
            transactions.add(transaction);
            senderAccount.addTransaction(transaction);
            receiverAccount.addTransaction(transaction);
        } catch (AccountNotFoundException e) {
            e.printStackTrace();
        }
    }
}
