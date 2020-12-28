package data.dao;

import java.sql.SQLException;

public interface UserDao<T> extends Dao<T> {
  T selectByAccountId(int accountId) throws SQLException;
}
