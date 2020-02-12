package data;

import model.Product;

import java.util.List;

public class DbRepository {
    public static void executeAdd(String title, double price) {
        Product product = new Product(title, price);
        DbHandler.addProductToDB(product);
    }

    public static void executeDelete(String title) {
        DbHandler.deleteProductFromDb(title);
    }

    public static void executeShowAll() {
        List<Product> products = DbHandler.getProductsFromDB();
        if (products != null) {
            if (products.size() > 0)
                products.forEach(product -> System.out.println(product.toString() + "\n"));
            else System.out.println("Products table is empty...");
        } else {
            System.err.println("Could not get products from db, please check stack trace...");
        }
    }

    public static void executeGetPrice(String title) {
        Double price = DbHandler.getPrice(title);
        if (price != null) {
            if (price != -1)
                System.out.println("Product with title " + title +  " has price: " + price);
            else System.out.println("No product with a title " + title + " was found");
        } else {
            System.err.println("Could not get price from db, please check stack trace...");
        }
    }

    public static void executeChangePrice(String title, double price) {
        DbHandler.changePrice(title, price);
    }

    public static void executeFilterByPrice(double rangeMin, double rangeMax) {
        List<Product> products = DbHandler.getProductsByPrice(rangeMin, rangeMax);
        System.out.println("Products filtered by price:\n");
        if (products != null) {
            if (products.size() > 0)
                products.forEach(product -> System.out.println(product.toString() + "\n"));
            else System.out.println("Could not find products satisfying condition...");
        } else {
            System.err.println("Could not get products from db, please check stack trace...");
        }
    }
}
