package data.dao;

import data.config.DatabaseConfig;
import data.model.Manager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static data.dao.helper.Helper.executeStatement;
import static data.utils.constants.Queries.*;

public class ManagerDao implements UserDao<Manager> {
  private static ManagerDao instance;
  private Connection connection;

  private ManagerDao() {
    connection = DatabaseConfig.getInstance().getConnection();
  }

  public static synchronized ManagerDao getInstance() {
    if (instance == null) {
      instance = new ManagerDao();
    }
    return instance;
  }

  @Override
  public Manager create(Manager model) {
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(INSERT_MANAGER_QUERY,
          Statement.RETURN_GENERATED_KEYS);
      preparedStatement.setInt(1, model.getCredentials().getId());
      preparedStatement.setString(2, model.getName());
      preparedStatement.setString(3, model.getPhoneNumber());
      preparedStatement.setString(4, model.getEmail());
      return selectById(executeStatement(preparedStatement));
    } catch (SQLException ex) {
      ex.printStackTrace();
    }
    return null;
  }

  @Override
  public Manager selectById(int id) throws SQLException {
    PreparedStatement preparedStatement = connection.prepareStatement(GET_MANAGER_BY_ID_QUERY);
    preparedStatement.setInt(1, id);
    ResultSet rs = preparedStatement.executeQuery();
    if (rs.next()) {
      return parseManager(rs);
    }
    return null;
  }

  public static Manager parseManager(ResultSet rs) throws SQLException {
    int managerId = rs.getInt("ManagerId");
    int accountId = rs.getInt("AccountId");
    String name = rs.getString("Name");
    String phoneNumber = rs.getString("PhoneNumber");
    String email = rs.getString("Email");
    return new Manager(managerId, accountId, name, phoneNumber, email);
  }

  @Override
  public List<Manager> selectAll() throws SQLException {
    PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_MANAGERS_QUERY);
    ResultSet rs = preparedStatement.executeQuery();
    List<Manager> result = new ArrayList<>();
    while (rs.next()) {
      result.add(parseManager(rs));
    }
    return result;
  }

  @Override
  public Manager updateById(int id, Manager model) throws SQLException {
    Manager oldManager = selectById(id);
    PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_MANAGER_QUERY);
    preparedStatement.setString(1, model.getName() == null ? oldManager.getName() : model.getName());
    preparedStatement.setString(2, model.getPhoneNumber() == null ? oldManager.getPhoneNumber() : model.getPhoneNumber());
    preparedStatement.setString(3, model.getEmail() == null ? oldManager.getEmail() : model.getEmail());
    preparedStatement.setInt(4, id);
    int updatedManagers = preparedStatement.executeUpdate();
    if (updatedManagers == 0) throw new SQLException("Manager was not found");
    return selectById(id);
  }

  @Override
  public void delete(int id) throws SQLException {
    PreparedStatement preparedStatement = connection.prepareStatement(DELETE_MANAGER_QUERY);
    preparedStatement.setInt(1, id);
    int deletedRows = preparedStatement.executeUpdate();
    if (deletedRows == 0) throw new SQLException("Could not delete manager with provided id");
  }

  @Override
  public Manager selectByAccountId(int accountId) throws SQLException {
    PreparedStatement preparedStatement = connection.prepareStatement(GET_MANAGER_BY_ACCOUNT_ID_QUERY);
    preparedStatement.setInt(1, accountId);
    ResultSet rs = preparedStatement.executeQuery();
    if (rs.next()) {
      return parseManager(rs);
    }
    return null;
  }
}
