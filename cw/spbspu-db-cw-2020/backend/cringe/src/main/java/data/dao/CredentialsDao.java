package data.dao;

import data.config.DatabaseConfig;
import data.model.Client;
import data.model.Credentials;
import data.model.Role;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static data.dao.helper.Helper.executeStatement;
import static data.utils.constants.Queries.*;

public class CredentialsDao implements Dao<Credentials> {
  private static CredentialsDao instance;
  private Connection connection;

  private CredentialsDao() {
    connection = DatabaseConfig.getInstance().getConnection();
  }

  public static synchronized CredentialsDao getInstance() {
    if (instance == null) {
      instance = new CredentialsDao();
    }
    return instance;
  }

  @Override
  public Credentials create(Credentials model) throws SQLException {
      PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ACCOUNT_QUERY,
          Statement.RETURN_GENERATED_KEYS);
      preparedStatement.setString(1, model.getLogin());
      preparedStatement.setString(2, model.getPassword());
      preparedStatement.setString(3, model.getRole().toString());
      return selectById(executeStatement(preparedStatement));
  }

  @Override
  public Credentials selectById(int id) throws SQLException {
    PreparedStatement preparedStatement = connection.prepareStatement(GET_ACCOUNT_BY_ID_QUERY,
        Statement.RETURN_GENERATED_KEYS);
    preparedStatement.setInt(1, id);
    ResultSet rs = preparedStatement.executeQuery();
    if (rs.next()) {
      return parseCredentials(rs);
    }
    return null;
  }

  public Credentials selectByLogin(String login) throws SQLException {
    PreparedStatement preparedStatement = connection.prepareStatement(GET_ACCOUNT_BY_LOGIN_QUERY,
        Statement.RETURN_GENERATED_KEYS);
    preparedStatement.setString(1, login);
    ResultSet rs = preparedStatement.executeQuery();
    if (rs.next()) {
      return parseCredentials(rs);
    }
    return null;
  }

  public static Credentials parseCredentials(ResultSet rs) throws SQLException {
    int accountId = rs.getInt("AccountId");
    String accountLogin = rs.getString("Login");
    String accountPassword = rs.getString("Password");
    String accountRole = rs.getString("Role");
    return new Credentials(accountId, accountLogin, accountPassword, Role.valueOf(accountRole));
  }

  @Override
  public List<Credentials> selectAll() throws SQLException {
    PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_CREDENTIALS_QUERY);
    ResultSet rs = preparedStatement.executeQuery();
    List<Credentials> result = new ArrayList<>();
    while (rs.next()) {
      result.add(parseCredentials(rs));
    }
    return result;
  }

  @Override
  public Credentials updateById(int id, Credentials model) throws SQLException {
    Credentials oldCredentials = selectById(id);
    PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ACCOUNT_QUERY);
    preparedStatement.setString(1, model.getLogin() == null ?
        oldCredentials.getLogin() : model.getLogin());
    preparedStatement.setString(2, model.getPassword() == null ?
        oldCredentials.getPassword() : model.getPassword());
    preparedStatement.setInt(3, id);
    int updatedClients = preparedStatement.executeUpdate();
    if (updatedClients == 0) throw new SQLException("Credentials were not found");
    return selectById(id);
  }

  @Override
  public void delete(int id) throws SQLException {
    PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ACCOUNT_QUERY);
    preparedStatement.setInt(1, id);
    int deletedRows = preparedStatement.executeUpdate();
    if (deletedRows == 0) throw new SQLException("Could not delete credentials with provided id");
  }
}
