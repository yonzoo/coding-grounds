import app.data.local.dao.ProductDao;
import app.data.local.models.Product;
import org.junit.Test;

import java.sql.SQLException;

public class ProductDaoTest {
  ProductDao dao = ProductDao.getInstance();

  @Test
  public void productDaoMustSuccessfullySetupConnection() {
    assert dao.isConnected();
  }

  @Test
  public void productDaoMustBeSingleton() {
    assert dao.equals(ProductDao.getInstance());
  }

  @Test(expected = SQLException.class)
  public void productDaoMustFailIfAddedProductIsDuplicate() throws SQLException {
    Product product = new Product("hello", 100);
    dao.addProductToDB(product);
    Product duplicate = new Product("hello", 200);
    dao.addProductToDB(duplicate);
  }

  @Test(expected = SQLException.class)
  public void productDaoMustFailIfDeletedProductDoesNotExist() throws SQLException {
    dao.deleteProductFromDb("random product title");
  }

  @Test(expected = SQLException.class)
  public void productDaoMustFailIfProductToGetPriceOfDoesNotExist() throws SQLException {
    dao.getPrice("random product title");
  }

  @Test(expected = SQLException.class)
  public void productDaoMustFailIfProductToChangePriceDoesNotExist() throws SQLException {
    dao.changePrice("random product title", 200);
  }
}
