package data.dao;

import data.config.DatabaseConfig;
import data.model.Tour;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static data.dao.helper.Helper.executeStatement;
import static data.utils.constants.Queries.*;

public class TourDao implements Dao<Tour> {
  private static TourDao instance;
  private Connection connection;

  private TourDao() {
    connection = DatabaseConfig.getInstance().getConnection();
  }

  public static synchronized TourDao getInstance() {
    if (instance == null) {
      instance = new TourDao();
    }
    return instance;
  }

  @Override
  public Tour create(Tour model) {
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(INSERT_TOUR_QUERY,
          Statement.RETURN_GENERATED_KEYS);
      preparedStatement.setString(1, model.getName());
      preparedStatement.setString(2, model.getDescription());
      preparedStatement.setDate(3, model.getStartDate());
      preparedStatement.setDate(4, model.getEndDate());
      preparedStatement.setInt(5, model.getPrice());
      return selectById(executeStatement(preparedStatement));
    } catch (SQLException ex) {
      ex.printStackTrace();
    }
    return null;
  }

  public static Tour parseTour(ResultSet rs) throws SQLException {
    int tourId = rs.getInt("TourId");
    String name = rs.getString("Name");
    String description = rs.getString("Description");
    Date startDate = rs.getDate("StartDate");
    Date endDate = rs.getDate("EndDate");
    int price = rs.getInt("Price");
    return new Tour(tourId, name, description, startDate, endDate, price);
  }

  public List<Tour> selectByClientId(int clientId) throws SQLException {
    PreparedStatement preparedStatement = connection.prepareStatement(GET_TOURS_BY_CLIENT_ID_QUERY);
    preparedStatement.setInt(1, clientId);
    ResultSet rs = preparedStatement.executeQuery();
    List<Tour> result = new ArrayList<>();
    while (rs.next()) {
      result.add(parseTour(rs));
    }
    return result;
  }

  @Override
  public Tour selectById(int id) throws SQLException {
    PreparedStatement preparedStatement = connection.prepareStatement(GET_TOUR_BY_ID_QUERY);
    preparedStatement.setInt(1, id);
    ResultSet rs = preparedStatement.executeQuery();
    if (rs.next()) {
      return parseTour(rs);
    }
    return null;
  }

  @Override
  public List<Tour> selectAll() throws SQLException {
    return ComplexDao.getTours(connection, GET_ALL_TOURS_QUERY);
  }

  @Override
  public Tour updateById(int id, Tour model) throws SQLException {
    Tour oldTour = selectById(id);
    PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_TOUR_QUERY);
    preparedStatement.setString(1,
        model.getName() == null ? oldTour.getName() : model.getName()
    );
    preparedStatement.setString(2,
        model.getDescription() == null ? oldTour.getDescription() : model.getDescription()
    );
    preparedStatement.setDate(3,
        model.getStartDate()== null ? oldTour.getStartDate() : model.getStartDate()
    );
    preparedStatement.setDate(4,
        model.getEndDate()== null ? oldTour.getEndDate() : model.getEndDate()
    );
    preparedStatement.setInt(5,
        model.getPrice() == 0 ? oldTour.getPrice() : model.getPrice()
    );
    preparedStatement.setInt(6, id);
    int updatedTours = preparedStatement.executeUpdate();
    if (updatedTours == 0) throw new SQLException("Tour was not found");
    return selectById(id);
  }

  @Override
  public void delete(int id) throws SQLException {
    PreparedStatement preparedStatement = connection.prepareStatement(DELETE_TOUR_QUERY);
    preparedStatement.setInt(1, id);
    int deletedRows = preparedStatement.executeUpdate();
    if (deletedRows == 0) throw new SQLException("Could not delete tour with provided id");
  }
}
