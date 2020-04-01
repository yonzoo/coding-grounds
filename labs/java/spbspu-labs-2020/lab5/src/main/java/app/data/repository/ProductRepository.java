package app.data.repository;

import app.data.local.dao.ProductDao;
import app.data.local.models.Product;

import java.sql.SQLException;
import java.util.List;

/**
 * Class is singleton which interacts with ProductsDao
 * Its goal is to perform possible middleware logic like validation or cashing
 */
public class ProductRepository {
  private static ProductRepository instance = new ProductRepository();
  private final ProductDao dao;

  /**
   * @return an instance of ProductRepository
   */
  public static synchronized ProductRepository getInstance() {
    if (instance == null) {
      instance = new ProductRepository();
    }
    return instance;
  }

  private ProductRepository() {
    dao = ProductDao.getInstance();
  }

  /**
   * @return list of products from dao
   * @throws SQLException if dao throws SQLException
   */
  public List<Product> getProducts() throws SQLException {
      return dao.getProductsFromDB();
  }

  /**
   * Method adds product via dao
   * @param title is title of the product to be added
   * @param price is price of the product to be added
   * @throws SQLException if dao throws SQLException or price is invalid
   */
  public void add(String title, double price) throws SQLException {
    if (title.isEmpty()) throw new SQLException("Title cannot be empty");
    if (price < 0) throw new SQLException("Price cannot be less than 0");
    Product product = new Product(title, price);
    dao.addProductToDB(product);
  }

  /**
   * Method deletes product via dao
   * @param title is title of the product to be deleted
   * @throws SQLException if dao throws SQLException
   */
  public void delete(String title) throws SQLException {
    if (title.isEmpty()) throw new SQLException("Title cannot be empty");
    dao.deleteProductFromDb(title);
  }

  /**
   * Method fills the database with n random entries
   * @param n is amount of random elements to be added
   * @throws SQLException if dao throws SQLException adding entry
   */
  public void fill(int n) throws SQLException {
    if (n < 0) throw new SQLException("N cannot be less than 0");
    for (int i = 0; i < n; i++) {
      Product product = new Product("prod" + (int) (Math.floor(i + Math.random() * 100000)), Math.floor(i * Math.random() * 1000));
      try {
        dao.addProductToDB(product);
      } catch (Exception ex) {
        i--;
      }
    }
  }

  /**
   * Method gets product price via dao
   * @param title is title of the product to get price of
   * @throws SQLException if dao throws SQLException
   */
  public Product getPrice(String title) throws SQLException {
    if (title.isEmpty()) throw new SQLException("Title cannot be empty");
    return dao.getPrice(title);
  }

  /**
   * Method gets product price via dao
   * @param min is minimum threshold of a price range
   * @param max is maximum threshold of a price range
   * @throws SQLException if dao throws SQLException or range is invalid
   */
  public List<Product> getProductsByPrice(double min, double max) throws SQLException {
    if (min < 0 || max < 0) throw new SQLException("Min and max range cannot be less than 0");
    if (max < min) throw new SQLException("Max range cannot be less than min range");
    return dao.getProductsByPrice(min, max);
  }

  /**
   * Method gets product price via dao
   * @param title is the title of the product to change price of
   * @param price is the new price of the changed product
   * @throws SQLException if dao throws SQLException or price is invalid
   */
  public void changePrice(String title, double price) throws SQLException {
    if (title.isEmpty()) throw new SQLException("Title cannot be empty");
    if (price < 0) throw new SQLException("Price cannot be less than 0");
    dao.changePrice(title, price);
  }
}
