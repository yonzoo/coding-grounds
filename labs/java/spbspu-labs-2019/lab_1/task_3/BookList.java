package lab_1.task_3;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class BookList implements Serializable {
    private ArrayList<Book> books;

    transient private Scanner scn = new Scanner(System.in);

    public BookList() {
        this.books = new ArrayList<Book>() {
        };
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void editBook(String id) {
        boolean bookFound = false;
        for (Book book : books) {
            if (book.getId().equals(id)) {
                bookFound = true;
                Scanner scn = new Scanner(System.in);
                String field = "";
                boolean req = true;
                while (req) {
                    System.out.println("Please enter parameter you want to change(Id, Name, Author, Year)...");
                    field = "";
                    while (!field.equals("Id") && !field.equals("Name") && !field.equals("Author") && !field.equals("Year"))
                        field = scn.nextLine();
                    switch (field) {
                        case "Id":
                            System.out.println("Please enter new id");
                            book.setId(scn.nextLine());
                            break;
                        case "Name":
                            System.out.println("Please enter new name");
                            book.setName(scn.nextLine());
                            break;
                        case "Author":
                            System.out.println("Please enter new author");
                            book.setAuthor(scn.nextLine());
                            break;
                        case "Year":
                            System.out.println("Please enter new date");
                            book.setDate(scn.nextInt());
                            break;
                    }
                    System.out.println("Do you want to change another parameter? Press y/n...");
                    String cmd = "";
                    while (!cmd.equals("y") && !cmd.equals("n"))
                        cmd = scn.nextLine();
                    req = cmd.equals("y");
                }
                break;
            }
        }
        if (!bookFound) System.out.println("Book was not found");
    }

    public void removeBook(String id) {
        boolean bookFound = false;
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getId().equals(id)) {
                bookFound = true;
                books.remove(i);
                break;
            }
        }
        if (!bookFound) System.out.println("Book was not found");
        else System.out.println("Book was removed");
    }

    ArrayList<Book> getBooks() {
        return books;
    }
}
