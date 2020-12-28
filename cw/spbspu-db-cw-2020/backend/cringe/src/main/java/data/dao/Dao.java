package data.dao;

import java.sql.SQLException;
import java.util.List;

public interface Dao<T> {
  T create(T model) throws SQLException;
  T selectById(int id) throws SQLException;
  List<T> selectAll() throws SQLException;
  T updateById(int id, T model) throws SQLException;
  void delete(int id) throws SQLException;
}
