package data.dao;

import data.config.DatabaseConfig;
import data.model.ClientTour;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static data.dao.helper.Helper.executeStatement;
import static data.utils.constants.Queries.*;

public class ClientTourDao {
  private static ClientTourDao instance;
  private Connection connection;

  private ClientTourDao() {
    connection = DatabaseConfig.getInstance().getConnection();
  }

  public static synchronized ClientTourDao getInstance() {
    if (instance == null) {
      instance = new ClientTourDao();
    }
    return instance;
  }

  public void create(ClientTour model) throws SQLException {
    PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CLIENT_TOUR_QUERY);
    preparedStatement.setInt(1, model.getClientId());
    preparedStatement.setInt(2, model.getTourId());
    try {
      preparedStatement.executeUpdate();
    } catch (Exception ex) {
      throw new SQLException("Already exists");
    }
  }

  public static ClientTour parseClientTour(ResultSet rs) throws SQLException {
    int clientId = rs.getInt("ClientId");
    int tourId = rs.getInt("TourId");
    return new ClientTour(clientId, tourId);
  }

  public ClientTour selectClientTourByClientIdAndTourId(int clientId, int tourId) throws SQLException {
    PreparedStatement preparedStatement = connection.prepareStatement(GET_CLIENT_TOUR_BY_CLIENT_ID_AND_TOUR_ID_QUERY);
    preparedStatement.setInt(1, clientId);
    preparedStatement.setInt(2, tourId);
    ResultSet rs = preparedStatement.executeQuery();
    if (rs.next()) {
      return parseClientTour(rs);
    }
    return null;
  }

  public List<ClientTour> selectAllByClientId(int clientId) throws SQLException {
    return getClientTours(GET_CLIENTS_TOURS_BY_CLIENT_ID_QUERY);
  }

  public List<ClientTour> selectAllByTourId(int tourId) throws SQLException {
    return getClientTours(GET_CLIENTS_TOURS_BY_TOUR_ID_QUERY);
  }

  private List<ClientTour> getClientTours(String getClientsToursByClientIdQuery) throws SQLException {
    PreparedStatement preparedStatement = connection.prepareStatement(getClientsToursByClientIdQuery);
    ResultSet rs = preparedStatement.executeQuery();
    List<ClientTour> result = new ArrayList<>();
    while (rs.next()) {
      result.add(parseClientTour(rs));
    }
    return result;
  }

  public void delete(int clientId, int tourId) throws SQLException {
    PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CLIENT_TOUR_QUERY);
    preparedStatement.setInt(1, clientId);
    preparedStatement.setInt(2, tourId);
    int deletedRows = preparedStatement.executeUpdate();
    if (deletedRows == 0) throw new SQLException("Could not delete clientTour with provided id");
  }
}
