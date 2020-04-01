import app.data.local.dao.ProductDao;
import app.data.repository.ProductRepository;
import org.junit.Test;

import java.sql.SQLException;

public class ProductRepositoryTest {
  ProductRepository repository = ProductRepository.getInstance();

  @Test
  public void productRepositoryMustBeSingleton() throws SQLException {
    assert repository.equals(ProductRepository.getInstance());
  }

  @Test
  public void repositoryMustAddCorrectProduct() throws SQLException {
    repository.add("correct", 21);
  }

  @Test(expected = SQLException.class)
  public void repositoryMustFailIfAddedProductHasEmptyName() throws SQLException {
    repository.add("", 21);
  }

  @Test(expected = SQLException.class)
  public void repositoryMustFailIfDeletedProductHasEmptyName() throws SQLException {
    repository.delete("");
  }

  @Test(expected = SQLException.class)
  public void repositoryMustFailIfProductToGetPriceOfHasEmptyName() throws SQLException {
    repository.getPrice("");
  }

  @Test(expected = SQLException.class)
  public void repositoryMustFailIfAddedProductHasNegativePrice() throws SQLException {
    repository.add("product", -1);
  }

  @Test(expected = SQLException.class)
  public void repositoryMustFailIfChangedProductNegativePrice() throws SQLException {
    repository.changePrice("product", -1);
  }

  @Test(expected = SQLException.class)
  public void repositoryMustFailIfMinRangeOfFilterIsLessThanZero() throws SQLException {
    repository.getProductsByPrice(-1, 100);
  }

  @Test(expected = SQLException.class)
  public void repositoryMustFailIfMaxRangeOfFilterIsLessThanZero() throws SQLException {
    repository.getProductsByPrice(100, -1);
  }

  @Test(expected = SQLException.class)
  public void repositoryMustFailIfMaxRangeIsLessThanMinRange() throws SQLException {
    repository.getProductsByPrice(220, 2);
  }
}
