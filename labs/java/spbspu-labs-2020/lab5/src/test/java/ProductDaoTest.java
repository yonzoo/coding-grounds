import app.data.local.dao.ProductDao;
import org.junit.Test;

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
}
