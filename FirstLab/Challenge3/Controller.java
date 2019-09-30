package Challenge3;

import java.io.*;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class Controller {
    private static BookList bookList;
    private static File file;
    private static Scanner scn = new Scanner(System.in);

    static {
        bookList = new BookList();
        file = new File("./booklist.ser");
        try {
            FileInputStream fin = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fin);
            file.createNewFile();
            bookList = (BookList) ois.readObject();
        } catch (FileNotFoundException ex) {
            System.out.println("Your file was not found, making a new one...");
            ex.printStackTrace();
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    public static void writeObjectToFile(BookList bookList) {
        try {
            final FileOutputStream fos = new FileOutputStream("./booklist.ser");
            final ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(bookList);
            System.out.println("Your data was successfully saved");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void startMenu() {
        renderMenu();
        String action = scn.nextLine();
        String cmd = "";
        switch (action) {
            case "1":
                renderCatalogue();
                resetMenu();
                break;
            case "2":
                findBook();
                resetMenu();
                break;
            case "3":
                addBook();
                resetMenu();
                break;
            case "4":
                removeBook();
                resetMenu();
                break;
            case "5":
                editBook();
                resetMenu();
                break;
            case "F":
                System.out.println("This is so sad");
                resetMenu();
                break;
        }
    }

    private void resetMenu() {
        System.out.println("Press B to return to main menu");
        String cmd = "";
        while (!cmd.equals("B")) cmd = scn.nextLine();
        startMenu();
    }

    private String askData(String key) {
        System.out.println(String.format("Please enter book's %s", key));
        String input = "";
        if (key.equals("year")) {
            while (true) {
                input = scn.nextLine();
                try {
                    int year = Integer.parseInt(input);
                    if (year >= 0) break;
                    else System.out.println("Year must be >= 0");
                } catch (NumberFormatException ex) {
                    System.out.println("Year must be integer");
                }
            }
        } else
            while (input.length() == 0) input = scn.nextLine();
        return input;
    }

    private void findBook() {
        String field = "", val = "";
        while (!field.equals("Id") && !field.equals("Name") && !field.equals("Author") && !field.equals("Year")) {
            System.out.println("Please enter search parameter(Id, Name, Author, Year)...");
            field = scn.nextLine();
        }
        System.out.println("Please enter search value...");
        if (field.equals("Year")) {
            while (true) {
                val = scn.nextLine();
                try {
                    int year = Integer.parseInt(val);
                    if (year >= 0) break;
                    else System.out.println("Year must be >= 0");
                } catch (NumberFormatException ex) {
                    System.out.println("Year must be integer");
                }
            }
        } else {
            while (val.length() == 0)
                val = scn.nextLine();
        }
        boolean bookFound = false;
        for (Book book : bookList.getBooks()) {
            if (field.equals("Id")) {
                if (book.getId().equals(val)) {
                    bookFound = true;
                }
            } else if (field.equals("Name")) {
                if (book.getName().equals(val)) {
                    bookFound = true;
                }
            } else if (field.equals("Author")) {
                if (book.getAuthor().equals(val)) {
                    bookFound = true;
                }
            } else {
                if (book.getDate() == Integer.parseInt(val)) {
                    bookFound = true;
                }
            }
            if (bookFound) {
                System.out.println("Here is your book, sir");
                renderBookInfo(book);
            }
        }
        if (!bookFound) System.out.println("Sorry book was not found");
    }

    private void addBook() {
        String bookId = askData("id"), bookName = askData("name"), bookAuthor = askData("author");
        int bookYear = Integer.parseInt(askData("year"));
        Book book = new Book(bookId, bookAuthor, bookName, bookYear);
        bookList.addBook(book);
        writeObjectToFile(bookList);
    }

    private void removeBook() {
        System.out.println("Please enter id of book you want to remove...");
        String id = "";
        while (id.length() == 0) id = scn.nextLine();
        bookList.removeBook(id);
        writeObjectToFile(bookList);
    }

    private void editBook() {
        System.out.println("Please enter id of book you want to remove...");
        String id = "";
        while (id.length() == 0) id = scn.nextLine();
        bookList.editBook(id);
        writeObjectToFile(bookList);
    }

    private void renderCatalogue() {
        if (bookList.getBooks().size() == 0) {
            System.out.println("There are no books in the catalogue");
        }
        for (Book book : bookList.getBooks()) {
            renderBookInfo(book);
        }
    }

    private void renderBookInfo(Book book) {
        System.out.println("----------------------");
        System.out.println("Id: " + book.getId());
        System.out.println("Author: " + book.getAuthor());
        System.out.println("Name: " + book.getName());
        System.out.println("Year: " + book.getDate());
        System.out.println("----------------------");
    }


    private void renderMenu() {
        System.out.println("---Hello alien---");
        System.out.println("Press 1 to see all books in catalogue");
        System.out.println("Press 2 to search a specific book");
        System.out.println("Press 3 to add a book");
        System.out.println("Press 4 to remove a book");
        System.out.println("Press 5 to edit a book");
        System.out.println("Press F to pay respects");
    }
}
