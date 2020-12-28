package data.dao;

import data.config.DatabaseConfig;
import data.model.ClientTourGroup;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static data.dao.helper.Helper.executeStatement;
import static data.utils.constants.Queries.*;

public class ClientTourGroupDao {
  private static ClientTourGroupDao instance;
  private Connection connection;

  private ClientTourGroupDao() {
    connection = DatabaseConfig.getInstance().getConnection();
  }

  public static synchronized ClientTourGroupDao getInstance() {
    if (instance == null) {
      instance = new ClientTourGroupDao();
    }
    return instance;
  }

  public void create(ClientTourGroup model) throws SQLException {
    PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CLIENT_TOUR_GROUP_QUERY);
    preparedStatement.setInt(1, model.getClientId());
    preparedStatement.setInt(2, model.getTourGroupId());
    try {
      preparedStatement.executeUpdate();
    } catch (Exception ex) {
      throw new SQLException("Already exists");
    }
  }

  public static ClientTourGroup parseClientTourGroup(ResultSet rs) throws SQLException {
    int clientId = rs.getInt("ClientId");
    int tourId = rs.getInt("TourGroupId");
    return new ClientTourGroup(clientId, tourId);
  }

  public ClientTourGroup selectById(int id) throws SQLException {
    PreparedStatement preparedStatement = connection.prepareStatement(GET_CLIENT_TOUR_GROUP_BY_ID_QUERY);
    preparedStatement.setInt(1, id);
    ResultSet rs = preparedStatement.executeQuery();
    if (rs.next()) {
      return parseClientTourGroup(rs);
    }
    return null;
  }

  public ClientTourGroup selectClientTourGroupByClientIdAndTourGroupId(int clientId, int tourId) throws SQLException {
    PreparedStatement preparedStatement = connection.prepareStatement(GET_CLIENT_TOUR_GROUP_BY_CLIENT_ID_AND_TOUR_GROUP_ID_QUERY);
    preparedStatement.setInt(1, clientId);
    preparedStatement.setInt(2, tourId);
    ResultSet rs = preparedStatement.executeQuery();
    if (rs.next()) {
      return parseClientTourGroup(rs);
    }
    return null;
  }

  public List<ClientTourGroup> selectAllByClientId(int clientId) throws SQLException {
    return getClientTourGroups(GET_CLIENT_TOUR_GROUPS_BY_CLIENT_ID_QUERY);
  }

  public List<ClientTourGroup> selectAllByTourGroupId(int tourId) throws SQLException {
    return getClientTourGroups(GET_CLIENTS_TOUR_GROUPS_BY_TOUR_GROUP_ID_QUERY);
  }

  private List<ClientTourGroup> getClientTourGroups(String getClientsTourGroupsByClientIdQuery) throws SQLException {
    PreparedStatement preparedStatement = connection.prepareStatement(getClientsTourGroupsByClientIdQuery);
    ResultSet rs = preparedStatement.executeQuery();
    List<ClientTourGroup> result = new ArrayList<>();
    while (rs.next()) {
      result.add(parseClientTourGroup(rs));
    }
    return result;
  }

  public void delete(int clientId, int tourGroupId) throws SQLException {
    PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CLIENT_TOUR_GROUP_QUERY);
    preparedStatement.setInt(1, clientId);
    preparedStatement.setInt(2, tourGroupId);
    int deletedRows = preparedStatement.executeUpdate();
    if (deletedRows == 0) throw new SQLException("Could not delete clientTourGroup with provided id");
  }
}
