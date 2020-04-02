import app.data.repository.ProductRepository;
import org.junit.Test;

import java.sql.SQLException;

public class ProductRepositoryTest {
  ProductRepository repository = ProductRepository.getInstance();

  @Test
  public void productRepositoryMustBeSingleton() {
    assert repository.equals(ProductRepository.getInstance());
  }

  @Test
  public void repositoryMustAddCorrectProduct() throws SQLException {
    repository.add("correct", "21");
  }

  @Test(expected = IllegalArgumentException.class)
  public void repositoryMustFailIfAddedProductHasEmptyName() throws SQLException {
    repository.add("", "21");
  }

  @Test(expected = IllegalArgumentException.class)
  public void repositoryMustFailIfAddedProductHasNegativePrice() throws SQLException {
    repository.add("product", "-1");
  }

  @Test(expected = NumberFormatException.class)
  public void repositoryMustFailIfAddedProductPriceHasInvalidFormat() throws SQLException {
    repository.add("hello", "abcd");
  }

  @Test(expected = IllegalArgumentException.class)
  public void repositoryMustFailIfDeletedProductHasEmptyName() throws SQLException {
    repository.delete("");
  }

  @Test(expected = IllegalArgumentException.class)
  public void repositoryMustFailIfProductToGetPriceOfHasEmptyName() throws SQLException {
    repository.getPrice("");
  }

  @Test(expected = IllegalArgumentException.class)
  public void repositoryMustFailIfChangedProductHasNegativePrice() throws SQLException {
    repository.changePrice("product", "-1");
  }

  @Test(expected = NumberFormatException.class)
  public void repositoryMustFailIfChangedProductPriceHasInvalidFormat() throws SQLException {
    repository.changePrice("product", "hello");
  }

  @Test(expected = IllegalArgumentException.class)
  public void repositoryMustFailIfMinValueOfFilterIsLessThanZero() throws SQLException {
    repository.getProductsByPrice("-1", "100");
  }

  @Test(expected = NumberFormatException.class)
  public void repositoryMustFailIfMinValueOfFilterHasInvalidFormat() throws SQLException {
    repository.getProductsByPrice("hello", "100");
  }

  @Test(expected = IllegalArgumentException.class)
  public void repositoryMustFailIfMaxValueOfFilterIsLessThanZero() throws SQLException {
    repository.getProductsByPrice("100", "-1");
  }

  @Test(expected = NumberFormatException.class)
  public void repositoryMustFailIfMaxValueOfFilterHasInvalidFormat() throws SQLException {
    repository.getProductsByPrice("100", "hello");
  }

  @Test(expected = IllegalArgumentException.class)
  public void repositoryMustFailIfMaxValueIsLessThanMinRange() throws SQLException {
    repository.getProductsByPrice("220", "2");
  }
}
