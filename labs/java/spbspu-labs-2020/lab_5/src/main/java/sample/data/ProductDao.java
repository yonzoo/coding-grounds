package sample.data;

import sample.utilities.ConnectionConfig;

import sample.models.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static sample.utilities.ProductQueries.*;

public class ProductDao {

  static Connection con = null;
  static PreparedStatement prepareStat = null;

  static {
    try {
      makeJDBCConnection();
      DatabaseMetaData dbm = con.getMetaData();
      ResultSet tables = dbm.getTables(null, null, "GOODS", null);
      if (tables.next()) {
        log("---------- Clearing table ----------");
        clearTable();
      } else {
        log("---------- Creating new table ----------");
        createTable();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public static void finish() {
    try {
      prepareStat.close();
      con.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  private static void makeJDBCConnection() {
    try {
      Class.forName("com.mysql.jdbc.Driver");
    } catch (ClassNotFoundException e) {
      log("Sorry, couldn't found JDBC driver. Make sure you have added JDBC Maven Dependency Correctly");
      e.printStackTrace();
      return;
    }
    try {
      con = DriverManager.getConnection(ConnectionConfig.URL, ConnectionConfig.USER, ConnectionConfig.PASSWORD);
      if (con != null) {
        log("Connection Successful!");
      } else {
        log("Failed to make connection!");
      }
    } catch (SQLException e) {
      log("MySQL Connection Failed!");
      e.printStackTrace();
    }
  }

  private static void clearTable() throws SQLException {
    prepareStat = con.prepareStatement(CLEAR_TABLE_QUERY);
    prepareStat.executeUpdate();
    log("Table was cleared successfully");
  }

  private static void createTable() throws SQLException {
    prepareStat = con.prepareStatement(CREATE_TABLE_QUERY);
    prepareStat.executeUpdate();
    log("New table was created successfully");
  }

  public static void addProductToDB(Product product) throws SQLException {
    prepareStat = con.prepareStatement(INSERT_PRODUCT_QUERY);
    prepareStat.setString(1, product.prodid);
    prepareStat.setString(2, product.title);
    prepareStat.setDouble(3, product.cost);
    prepareStat.executeUpdate();
    log(product.title + " added successfully");
  }

  public static void deleteProductFromDb(String title) throws SQLException {
    prepareStat = con.prepareStatement(DELETE_PRODUCT_QUERY);
    prepareStat.setString(1, title);
    int deletedRows = prepareStat.executeUpdate();
    if (deletedRows > 0)
      log(title + " deleted successfully");
    else
      log("Could not find element with title " + title);
  }

  public static List<Product> getProductsFromDB() throws SQLException {
    prepareStat = con.prepareStatement(GET_PRODUCTS_QUERY);
    ResultSet rs = prepareStat.executeQuery();
    List<Product> result = new ArrayList<>();
    while (rs.next()) {
      int id = rs.getInt("id");
      String prodid = rs.getString("prodid");
      String title = rs.getString("title");
      double cost = rs.getDouble("cost");
      result.add(new Product(id, prodid, title, cost));
    }
    return result;
  }

  public static List<Product> getPrice(String givenTitle) throws SQLException {
    prepareStat = con.prepareStatement(GET_PRICE_QUERY);
    prepareStat.setString(1, givenTitle);
    List<Product> result = new ArrayList<>();
    ResultSet rs = prepareStat.executeQuery();
    if (rs.next()) {
      int id = rs.getInt("id");
      String prodid = rs.getString("prodid");
      String title = rs.getString("title");
      double cost = rs.getDouble("cost");
      result.add(new Product(id, prodid, title, cost));
    }
    return result;
  }

  public static List<Product> getProductsByPrice(double rangeMin, double rangeMax) throws SQLException {
    prepareStat = con.prepareStatement(GET_PRODUCTS_BY_PRICE_QUERY);
    prepareStat.setDouble(1, rangeMin);
    prepareStat.setDouble(2, rangeMax);
    ResultSet rs = prepareStat.executeQuery();
    List<Product> result = new ArrayList<>();
    while (rs.next()) {
      int id = rs.getInt("id");
      String prodid = rs.getString("prodid");
      String title = rs.getString("title");
      double cost = rs.getDouble("cost");
      result.add(new Product(id, prodid, title, cost));
    }
    return result;
  }

  public static void changePrice(String title, double newPrice) throws SQLException {
    prepareStat = con.prepareStatement(CHANGE_PRICE_QUERY);
    prepareStat.setDouble(1, newPrice);
    prepareStat.setString(2, title);
    prepareStat.executeUpdate();
    log(title + " price was updated successfully");
  }

  private static void log(String string) {
    System.out.println(string);
  }
}