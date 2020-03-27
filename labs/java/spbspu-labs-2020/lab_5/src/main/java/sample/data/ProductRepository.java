package sample.data;

import sample.models.Product;

import java.sql.SQLException;
import java.util.List;

public class ProductRepository {
    public static void executeAdd(String title, double price) throws SQLException {
        if (price < 0) throw new IllegalArgumentException("Price cannot be less than 0");
        Product product = new Product(title, price);
        ProductDao.addProductToDB(product);
    }

    public static void executeDelete(String title) throws SQLException {
        ProductDao.deleteProductFromDb(title);
    }

    public static void executeFill(int n) {
        if (n < 0) throw new IllegalArgumentException("N cannot be less than 0");
        for (int i = 0; i < n; i++) {
            Product product = new Product("prod" + (int)(Math.floor(i + Math.random() * 100000)), Math.floor(i * Math.random() * 1000));
            try {
                ProductDao.addProductToDB(product);
            } catch (Exception ex) {
                i--;
            }
        }
    }

    public static void executeChangePrice(String title, double price) throws SQLException {
        if (price < 0) throw new IllegalArgumentException("Price cannot be less than 0");
        ProductDao.changePrice(title, price);
    }
}
