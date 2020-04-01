package app.data.local.models;

import java.util.UUID;

/**
 * Class is a model which encapsulates important product information
 */
public class Product {
    public int id = -1; //by default id has undefined status(-1) which is being set when db creates an instance with generated id
    public final String prodid;
    public final String title;
    public final double cost;

    /**
     * Product constructor
     * @param title is the title of the created product
     * @param cost is the cost of the created product
     */
    public Product(String title, double cost) {
        this.prodid = generateProductId();
        this.title = title;
        this.cost = cost;
    }

    /**
     * Product constructor
     * @param id is the database id of the created product
     * @param prodid is the id of the created product
     * @param title is the title of the created product
     * @param cost is the cost of the created product
     */
    public Product(int id, String prodid, String title, double cost) {
        this.id = id;
        this.prodid = prodid;
        this.title = title;
        this.cost = cost;
    }

    private String generateProductId() {
        return UUID.randomUUID().toString();
    }
}
