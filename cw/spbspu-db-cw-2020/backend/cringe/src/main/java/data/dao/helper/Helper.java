package data.dao.helper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Helper {

  public static int executeStatement(PreparedStatement statement) throws SQLException {
    try {
      statement.executeUpdate();
    } catch (Exception ex) {
      throw new SQLException(ex.getLocalizedMessage());
    }
    try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
      if (generatedKeys.next()) {
        return generatedKeys.getInt(1);
      }
      else {
        throw new SQLException("No ID obtained");
      }
    }
  }
}
