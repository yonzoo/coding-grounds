package data.dao;

import data.config.DatabaseConfig;
import data.model.Request;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static data.dao.helper.Helper.executeStatement;
import static data.utils.constants.Queries.*;

public class RequestDao implements Dao<Request> {
  private static RequestDao instance;
  private Connection connection;

  private RequestDao() {
    connection = DatabaseConfig.getInstance().getConnection();
  }

  public static synchronized RequestDao getInstance() {
    if (instance == null) {
      instance = new RequestDao();
    }
    return instance;
  }

  @Override
  public Request create(Request model) throws SQLException {
    PreparedStatement preparedStatement = connection.prepareStatement(INSERT_REQUEST_QUERY,
        Statement.RETURN_GENERATED_KEYS);
    preparedStatement.setInt(1, model.getTourId());
    preparedStatement.setBoolean(2, false);
    preparedStatement.setInt(3, model.getClientId());
    preparedStatement.setDate(4, new Date(new java.util.Date().getTime()));
    return selectById(executeStatement(preparedStatement));
  }

  public static Request parseRequest(ResultSet rs) throws SQLException {
    int requestId = rs.getInt("RequestId");
    int tourId = rs.getInt("TourId");
    int clientId = rs.getInt("ClientId");
    boolean isApproved = rs.getBoolean("IsApproved");
    Date date = rs.getDate("Date");
    return new Request(requestId, clientId, tourId, isApproved, date);
  }

  @Override
  public Request selectById(int id) throws SQLException {
    PreparedStatement preparedStatement = connection.prepareStatement(GET_REQUEST_BY_ID_QUERY);
    preparedStatement.setInt(1, id);
    ResultSet rs = preparedStatement.executeQuery();
    if (rs.next()) {
      return parseRequest(rs);
    }
    return null;
  }

  public List<Request> selectByClientIdAndTourId(int clientId, int tourId) throws SQLException {
    PreparedStatement preparedStatement =
        connection.prepareStatement(GET_REQUESTS_BY_CLIENT_ID_AND_TOUR_ID_QUERY);
    preparedStatement.setInt(1, clientId);
    preparedStatement.setInt(2, tourId);
    ResultSet rs = preparedStatement.executeQuery();
    List<Request> result = new ArrayList<>();
    while (rs.next()) {
      result.add(parseRequest(rs));
    }
    return result;
  }

  public List<Request> selectByClientId(int clientId) throws SQLException {
    PreparedStatement preparedStatement =
        connection.prepareStatement(GET_REQUESTS_BY_CLIENT_ID_QUERY);
    preparedStatement.setInt(1, clientId);
    ResultSet rs = preparedStatement.executeQuery();
    List<Request> result = new ArrayList<>();
    while (rs.next()) {
      result.add(parseRequest(rs));
    }
    return result;
  }


  @Override
  public List<Request> selectAll() throws SQLException {
    PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_REQUESTS_QUERY);
    ResultSet rs = preparedStatement.executeQuery();
    List<Request> result = new ArrayList<>();
    while (rs.next()) {
      result.add(parseRequest(rs));
    }
    return result;
  }

  @Override
  public Request updateById(int id, Request model) throws SQLException {
    PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_REQUEST_QUERY);
    preparedStatement.setBoolean(1, model.isApproved());
    preparedStatement.setInt(2, id);
    int updatedRequests = preparedStatement.executeUpdate();
    if (updatedRequests == 0) throw new SQLException("Request was not found");
    return selectById(id);
  }

  @Override
  public void delete(int id) throws SQLException {
    PreparedStatement preparedStatement = connection.prepareStatement(DELETE_REQUEST_QUERY);
    preparedStatement.setInt(1, id);
    int deletedRows = preparedStatement.executeUpdate();
    if (deletedRows == 0) throw new SQLException("Could not delete request with provided id");
  }
}
