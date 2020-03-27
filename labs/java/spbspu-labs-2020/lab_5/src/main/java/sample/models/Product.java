package sample.models;

import java.util.UUID;

public class Product {
    public int id = -1; //by default id has undefined status(-1) which is being set when db creates an instance with generated id
    public final String prodid;
    public final String title;
    public final double cost;

    public Product(String title, double cost) {
        this.prodid = generateProductId();
        this.title = title;
        this.cost = cost;
    }

    public Product(int id, String prodid, String title, double cost) {
        this.id = id;
        this.prodid = prodid;
        this.title = title;
        this.cost = cost;
    }

    public String toString() {
        return "Id: " + id + "\n" +
                "Prodid: " + prodid + "\n" +
                "Title: " + title + "\n" +
                "Cost: " + cost + "\n";
    }

    private String generateProductId() {
        return UUID.randomUUID().toString();
    }
}
