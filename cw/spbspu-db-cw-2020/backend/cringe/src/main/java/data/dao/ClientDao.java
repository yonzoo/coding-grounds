package data.dao;

import data.config.DatabaseConfig;
import data.model.Client;
import data.model.Manager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static data.dao.helper.Helper.executeStatement;
import static data.utils.constants.Queries.*;

public class ClientDao implements UserDao<Client> {
  private static ClientDao instance;
  private Connection connection;

  private ClientDao() {
    connection = DatabaseConfig.getInstance().getConnection();
  }

  public static synchronized ClientDao getInstance() {
    if (instance == null) {
      instance = new ClientDao();
    }
    return instance;
  }

  @Override
  public Client create(Client model) {
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CLIENT_QUERY,
          Statement.RETURN_GENERATED_KEYS);
      preparedStatement.setString(1, model.getName());
      preparedStatement.setDate(2, model.getBirthDate());
      preparedStatement.setString(3, model.getDocumentNumber());
      preparedStatement.setInt(4, model.getCredentials().getId());
      preparedStatement.setString(5, model.getPhoneNumber());
      preparedStatement.setString(6, model.getEmail());
      return selectById(executeStatement(preparedStatement));
    } catch (SQLException ex) {
      ex.printStackTrace();
    }
    return null;
  }

  @Override
  public Client selectById(int id) throws SQLException {
    PreparedStatement preparedStatement = connection.prepareStatement(GET_CLIENT_BY_ID_QUERY);
    preparedStatement.setInt(1, id);
    ResultSet rs = preparedStatement.executeQuery();
    if (rs.next()) {
      return parseClient(rs);
    }
    return null;
  }

  public static Client parseClient(ResultSet rs) throws SQLException {
    int clientId = rs.getInt("ClientId");
    int accountId = rs.getInt("AccountId");
    String name = rs.getString("Name");
    Date birthDate = rs.getDate("BirthDate");
    String phoneNumber = rs.getString("PhoneNumber");
    String email = rs.getString("Email");
    String documentNumber = rs.getString("DocumentNumber");
    return new Client(clientId, accountId, name, birthDate, phoneNumber, email, documentNumber);
  }

  @Override
  public List<Client> selectAll() throws SQLException {
    PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_CLIENTS_QUERY);
    ResultSet rs = preparedStatement.executeQuery();
    List<Client> result = new ArrayList<>();
    while (rs.next()) {
      result.add(parseClient(rs));
    }
    return result;
  }

  @Override
  public Client updateById(int id, Client model) throws SQLException {
    Client oldClient = selectById(id);
    PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CLIENT_QUERY);
    preparedStatement.setString(1, model.getName() == null ? oldClient.getName() : model.getName());
    preparedStatement.setDate(2, model.getBirthDate() == null ? oldClient.getBirthDate() : model.getBirthDate());
    preparedStatement.setString(3, model.getPhoneNumber() == null ? oldClient.getPhoneNumber() : model.getPhoneNumber());
    preparedStatement.setString(4, model.getEmail() == null ? oldClient.getEmail() : model.getEmail());
    preparedStatement.setString(5, model.getDocumentNumber() == null ? oldClient.getDocumentNumber() : model.getDocumentNumber());
    preparedStatement.setInt(6, id);
    int updatedClients = preparedStatement.executeUpdate();
    if (updatedClients == 0) throw new SQLException("Client was not found");
    return selectById(id);
  }

  @Override
  public void delete(int id) throws SQLException {
    PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CLIENT_QUERY);
    preparedStatement.setInt(1, id);
    int deletedRows = preparedStatement.executeUpdate();
    if (deletedRows == 0) throw new SQLException("Could not delete client with provided id");
  }

  @Override
  public Client selectByAccountId(int accountId) throws SQLException {
    PreparedStatement preparedStatement = connection.prepareStatement(GET_CLIENT_BY_ACCOUNT_ID_QUERY);
    preparedStatement.setInt(1, accountId);
    ResultSet rs = preparedStatement.executeQuery();
    if (rs.next()) {
      return parseClient(rs);
    }
    return null;
  }
}
