package app.data.repository;

import app.data.local.dao.ProductDao;
import app.data.local.models.Product;

import java.sql.SQLException;
import java.util.List;

import static app.data.repository.utils.ErrorMessages.*;

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
   *
   * @param title       is title of the product to be added
   * @param priceString is price of the product to be added
   * @throws SQLException             if dao throws SQLException
   * @throws IllegalArgumentException if price is invalid
   * @throws NumberFormatException    if price has invalid format
   */
  public void add(String title, String priceString) throws SQLException {
    if (title.isEmpty() || priceString.isEmpty())
      throw new IllegalArgumentException(NAME_OR_PRICE_EMPTY);
    try {
      double price = Double.parseDouble(priceString);
      if (price < 0)
        throw new IllegalArgumentException(PRICE_NEGATIVE);
      Product product = new Product(title, price);
      dao.addProductToDB(product);
    } catch (NumberFormatException e) {
      throw new NumberFormatException(PRICE_FORMAT_ERROR);
    }
  }

  /**
   * Method deletes product via dao
   *
   * @param title is title of the product to be deleted
   * @throws SQLException             if dao throws SQLException
   * @throws IllegalArgumentException if title is empty
   */
  public void delete(String title) throws SQLException {
    if (title.isEmpty())
      throw new IllegalArgumentException(NAME_EMPTY);
    dao.deleteProductFromDb(title);
  }

  /**
   * Method fills the database with n random entries
   *
   * @param amount is amount of random elements to be added
   * @throws IllegalArgumentException if amount is invalid
   * @throws NumberFormatException    if amount has invalid format
   */
  public void fill(String amount) {
    if (amount.isEmpty())
      throw new IllegalArgumentException(FILL_EMPTY);
    try {
      int n = Integer.parseInt(amount);
      if (n < 0)
        throw new IllegalArgumentException(FILL_NEGATIVE);
      for (int i = 0; i < n; i++) {
        Product product = new Product("prod" + (int) (Math.floor(i + Math.random() * 100000)), Math.floor(i * Math.random() * 1000));
        try {
          dao.addProductToDB(product);
        } catch (SQLException ex) {
          i--;
        }
      }
    } catch (NumberFormatException e) {
      throw new NumberFormatException(FILL_FORMAT_ERROR);
    }
  }

  /**
   * Method gets product price via dao
   *
   * @param title is title of the product to get price of
   * @throws SQLException             if dao throws SQLException
   * @throws IllegalArgumentException if title is empty
   */
  public Product getPrice(String title) throws SQLException {
    if (title.isEmpty())
      throw new IllegalArgumentException(NAME_EMPTY);
    return dao.getPrice(title);
  }

  /**
   * Method gets product price via dao
   *
   * @param minString is minimum threshold of a price range
   * @param maxString is maximum threshold of a price range
   * @throws SQLException             if dao throws SQLException or range is invalid
   * @throws IllegalArgumentException if range is invalid
   * @throws NumberFormatException    if range has invalid format
   */
  public List<Product> getProductsByPrice(String minString, String maxString) throws SQLException {
    if (minString.isEmpty() || maxString.isEmpty())
      throw new IllegalArgumentException(FILTER_EMPTY);
    try {
      double min = Double.parseDouble(minString);
      double max = Double.parseDouble(maxString);
      if (min < 0 || max < 0)
        throw new IllegalArgumentException(FILTER_NEGATIVE);
      if (max < min)
        throw new IllegalArgumentException(FILTER_LOGIC_ERROR);
      return dao.getProductsByPrice(min, max);
    } catch (NumberFormatException e) {
      throw new NumberFormatException(FILTER_FORMAT_ERROR);
    }
  }

  /**
   * Method gets product price via dao
   *
   * @param title       is the title of the product to change price of
   * @param priceString is the new price of the changed product
   * @throws SQLException             if dao throws SQLException or price is invalid
   * @throws IllegalArgumentException if title or price is invalid
   * @throws NumberFormatException    if price has invalid format
   */
  public void changePrice(String title, String priceString) throws SQLException {
    if (title.isEmpty() || priceString.isEmpty())
      throw new IllegalArgumentException(NAME_OR_PRICE_EMPTY);
    try {
      double price = Double.parseDouble(priceString);
      if (price < 0) throw new IllegalArgumentException(PRICE_NEGATIVE);
      dao.changePrice(title, price);
    } catch (NumberFormatException e) {
      throw new NumberFormatException(PRICE_FORMAT_ERROR);
    }
  }
}
