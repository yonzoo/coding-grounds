package lab_1.task_3;

import java.io.Serializable;
import java.time.LocalDate;

public class Book implements Serializable {

    private String id;
    private String author;
    private String name;
    private int year;

    public Book() {

    }

    public Book(final String id, final String author, final String name, final int year) {
        this.id = id;
        this.author = author;
        this.name = name;
        this.year = year;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public void setAuthor(final String author) {
        this.author = author;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setDate(final int year) {
        this.year = year;
    }

    public String getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getName() {
        return name;
    }

    public int getDate() {
        return year;
    }
}
