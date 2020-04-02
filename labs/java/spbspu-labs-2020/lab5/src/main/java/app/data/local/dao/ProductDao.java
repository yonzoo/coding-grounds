package app.data.local.dao;

import app.data.local.models.Product;
import app.data.local.utils.ConnectionConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static app.data.local.utils.ProductQueries.*;

/**
 * Class is singleton which creates connection and performs data operations with the database
 */
public class ProductDao {
  private static ProductDao instance;
  private static final String NO_PRODUCT_MESSAGE = "No product was found";

  private Connection con = null;
  private PreparedStatement prepareStat = null;

  /**
   * @return an instance of ProductDao
   */
  public static synchronized ProductDao getInstance() {
    if (instance == null) {
      instance = new ProductDao();
    }
    return instance;
  }

  /**
   * @return true if dao successfully connected to the database
   * used in unit tests
   */
  public boolean isConnected() {
    return con != null;
  }

  private ProductDao() {
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

  private void makeJDBCConnection() {
    try {
      Class.forName("com.mysql.jdbc.Driver");
    } catch (ClassNotFoundException e) {
      log("Sorry, couldn't find JDBC driver. Make sure you have added JDBC Maven Dependency Correctly");
      e.printStackTrace();
      return;
    }
    try {
      Properties p = new Properties();
      p.setProperty("user", ConnectionConfig.USER);
      p.setProperty("password", ConnectionConfig.PASSWORD);
      p.setProperty("useUnicode","true");
      p.setProperty("characterEncoding","cp1251");
      con = DriverManager.getConnection(ConnectionConfig.URL, p);
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

  private void clearTable() throws SQLException {
    prepareStat = con.prepareStatement(CLEAR_TABLE_QUERY);
    prepareStat.executeUpdate();
    log("Table was cleared successfully");
  }

  private void createTable() throws SQLException {
    prepareStat = con.prepareStatement(CREATE_TABLE_QUERY);
    prepareStat.executeUpdate();
    log("New table was created successfully");
  }

  /**
   * Method adds a product to the database
   * @param product is a product which is to be added to the database
   * @throws SQLException when the database fails to insert an entry
   */
  public void addProductToDB(Product product) throws SQLException {
    prepareStat = con.prepareStatement(INSERT_PRODUCT_QUERY);
    prepareStat.setString(1, product.prodid);
    prepareStat.setString(2, product.title);
    prepareStat.setDouble(3, product.cost);
    prepareStat.executeUpdate();
    log(product.title + " added successfully");
  }

  /**
   * Method deletes a product from the database
   * @param title is the title of the product which is to be deleted from the database
   * @throws SQLException when the database fails to delete an entry
   */
  public void deleteProductFromDb(String title) throws SQLException {
    prepareStat = con.prepareStatement(DELETE_PRODUCT_QUERY);
    prepareStat.setString(1, title);
    int deletedRows = prepareStat.executeUpdate();
    if (deletedRows > 0)
      log(title + " deleted successfully");
    else
      throw new SQLException(NO_PRODUCT_MESSAGE);
  }

  /**
   * Method returns list of products from database
   * @throws SQLException when the database fails to return products
   */
  public List<Product> getProductsFromDB() throws SQLException {
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

  /**
   * Method returns list of products from database
   * @param givenTitle is the title of the product to get price of
   * @throws SQLException when the database fails to return a product with given title
   */
  public Product getPrice(String givenTitle) throws SQLException {
    prepareStat = con.prepareStatement(GET_PRICE_QUERY);
    prepareStat.setString(1, givenTitle);
    Product product = null;
    ResultSet rs = prepareStat.executeQuery();
    if (rs.next()) {
      int id = rs.getInt("id");
      String prodid = rs.getString("prodid");
      String title = rs.getString("title");
      double cost = rs.getDouble("cost");
      product = new Product(id, prodid, title, cost);
    }
    if (product == null) throw new SQLException(NO_PRODUCT_MESSAGE);
    return product;
  }

  /**
   * Method returns list of products from database
   * @param rangeMin is minimum threshold of a price range
   * @param rangeMax is maximum threshold of a price range
   * @throws SQLException when the database fails to return products with provided range
   */
  public List<Product> getProductsByPrice(double rangeMin, double rangeMax) throws SQLException {
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

  /**
   * Method changes a price of a product in the database
   * @param title is the title of the product to change price of
   * @param newPrice is the new price of the changed product
   * @throws SQLException when the database fails to update price
   */
  public void changePrice(String title, double newPrice) throws SQLException {
    prepareStat = con.prepareStatement(CHANGE_PRICE_QUERY);
    prepareStat.setDouble(1, newPrice);
    prepareStat.setString(2, title);
    int updatedProducts = prepareStat.executeUpdate();
    if (updatedProducts == 0) throw new SQLException(NO_PRODUCT_MESSAGE);
    log(title + " price was updated successfully");
  }

  private static void log(String string) {
    System.out.println(string);
  }
}