package data.dao;

import data.config.DatabaseConfig;
import data.model.TourGroup;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static data.dao.helper.Helper.executeStatement;
import static data.utils.constants.Queries.*;

public class TourGroupDao implements Dao<TourGroup> {
  private static TourGroupDao instance;
  private Connection connection;

  private TourGroupDao() {
    connection = DatabaseConfig.getInstance().getConnection();
  }

  public static synchronized TourGroupDao getInstance() {
    if (instance == null) {
      instance = new TourGroupDao();
    }
    return instance;
  }

  @Override
  public TourGroup create(TourGroup model) {
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(INSERT_TOUR_GROUP_QUERY,
          Statement.RETURN_GENERATED_KEYS);
      preparedStatement.setString(1, model.getName());
      preparedStatement.setString(2, model.getDescription());
      preparedStatement.setInt(3, model.getTourId());
      preparedStatement.setInt(4, model.getMaxAmount());
      return selectById(executeStatement(preparedStatement));
    } catch (SQLException ex) {
      ex.printStackTrace();
    }
    return null;
  }

  public static TourGroup parseTourGroup(ResultSet rs) throws SQLException {
    int tourGroupId = rs.getInt("TourGroupId");
    String name = rs.getString("Name");
    String description = rs.getString("Description");
    int tourId = rs.getInt("TourId");
    int maxAmount = rs.getInt("MaxAmount");
    return new TourGroup(tourGroupId, tourId, name, description, maxAmount);
  }

  @Override
  public TourGroup selectById(int id) throws SQLException {
    PreparedStatement preparedStatement = connection.prepareStatement(GET_TOUR_GROUP_BY_ID_QUERY);
    preparedStatement.setInt(1, id);
    ResultSet rs = preparedStatement.executeQuery();
    if (rs.next()) {
      return parseTourGroup(rs);
    }
    return null;
  }

  @Override
  public List<TourGroup> selectAll() throws SQLException {
    PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_TOUR_GROUPS_QUERY);
    ResultSet rs = preparedStatement.executeQuery();
    List<TourGroup> result = new ArrayList<>();
    while (rs.next()) {
      result.add(parseTourGroup(rs));
    }
    return result;
  }

  public List<TourGroup> selectByClientId(int clientId) throws SQLException {
    PreparedStatement preparedStatement = connection.prepareStatement(GET_TOUR_GROUPS_BY_CLIENT_ID_QUERY);
    preparedStatement.setInt(1, clientId);
    ResultSet rs = preparedStatement.executeQuery();
    List<TourGroup> result = new ArrayList<>();
    while (rs.next()) {
      result.add(parseTourGroup(rs));
    }
    return result;
  }

  @Override
  public TourGroup updateById(int id, TourGroup model) throws SQLException {
    TourGroup oldTourGroup = selectById(id);
    PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_TOUR_GROUP_QUERY);
    preparedStatement.setInt(1, model.getTourId());
    preparedStatement.setString(2,
        model.getName() == null ? oldTourGroup.getName() : model.getName()
    );
    preparedStatement.setString(3,
        model.getDescription() == null ? oldTourGroup.getDescription() : model.getDescription()
    );
    preparedStatement.setInt(4,
        model.getMaxAmount() == 0 ? oldTourGroup.getMaxAmount() : model.getMaxAmount()
    );
    preparedStatement.setInt(5, id);
    int updatedTourGroups = preparedStatement.executeUpdate();
    if (updatedTourGroups == 0) throw new SQLException("TourGroup was not found");
    return selectById(id);
  }

  @Override
  public void delete(int id) throws SQLException {
    PreparedStatement preparedStatement = connection.prepareStatement(DELETE_TOUR_GROUP_QUERY);
    preparedStatement.setInt(1, id);
    int deletedRows = preparedStatement.executeUpdate();
    if (deletedRows == 0) throw new SQLException("Could not delete tourGroup with provided id");
  }
}
