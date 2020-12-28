package data.dao;

import data.config.DatabaseConfig;
import data.model.Discount;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static data.dao.helper.Helper.executeStatement;
import static data.utils.constants.Queries.*;

public class DiscountDao implements Dao<Discount> {
  private static DiscountDao instance;
  private Connection connection;

  private DiscountDao() {
    connection = DatabaseConfig.getInstance().getConnection();
  }

  public static synchronized DiscountDao getInstance() {
    if (instance == null) {
      instance = new DiscountDao();
    }
    return instance;
  }

  @Override
  public Discount create(Discount model) {
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(INSERT_DISCOUNT_QUERY,
          Statement.RETURN_GENERATED_KEYS);
      preparedStatement.setInt(1, model.getDiscountAmount());
      preparedStatement.setInt(2, model.getTourId());
      return selectById(executeStatement(preparedStatement));
    } catch (SQLException ex) {
      ex.printStackTrace();
    }
    return null;
  }

  public static Discount parseDiscount(ResultSet rs) throws SQLException {
    int discountId = rs.getInt("DiscountId");
    int tourId = rs.getInt("TourId");
    int discountAmount = rs.getInt("DiscountAmount");
    return new Discount(discountId, tourId, discountAmount);
  }

  @Override
  public Discount selectById(int id) throws SQLException {
    PreparedStatement preparedStatement = connection.prepareStatement(GET_DISCOUNT_BY_ID_QUERY);
    preparedStatement.setInt(1, id);
    ResultSet rs = preparedStatement.executeQuery();
    if (rs.next()) {
      return parseDiscount(rs);
    }
    return null;
  }

  public List<Discount> selectAllDiscountsByTourId(int tourId) throws SQLException {
    PreparedStatement preparedStatement = connection.prepareStatement(GET_DISCOUNTS_BY_TOUR_ID_QUERY);
    preparedStatement.setInt(1, tourId);
    ResultSet rs = preparedStatement.executeQuery();
    List<Discount> result = new ArrayList<>();
    while (rs.next()) {
      result.add(parseDiscount(rs));
    }
    return result;
  }

  @Override
  public List<Discount> selectAll() throws SQLException {
    PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_DISCOUNTS_QUERY);
    ResultSet rs = preparedStatement.executeQuery();
    List<Discount> result = new ArrayList<>();
    while (rs.next()) {
      result.add(parseDiscount(rs));
    }
    return result;
  }

  @Override
  public Discount updateById(int id, Discount model) throws SQLException {
    Discount oldDiscount = selectById(id);
    PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_DISCOUNT_QUERY);
    preparedStatement.setInt(1,
        model.getDiscountAmount() == 0 ? oldDiscount.getDiscountAmount() : model.getDiscountAmount()
    );
    preparedStatement.setInt(2, model.getTourId());
    preparedStatement.setInt(3, id);
    int updatedDiscounts = preparedStatement.executeUpdate();
    if (updatedDiscounts == 0) throw new SQLException("Discount was not found");
    return selectById(id);
  }

  @Override
  public void delete(int id) throws SQLException {
    PreparedStatement preparedStatement = connection.prepareStatement(DELETE_DISCOUNT_QUERY);
    preparedStatement.setInt(1, id);
    int deletedRows = preparedStatement.executeUpdate();
    if (deletedRows == 0) throw new SQLException("Could not delete discount with provided id");
  }

  public void deleteAllDiscountsByTourId(int tourId) throws SQLException {
    PreparedStatement preparedStatement = connection.prepareStatement(DELETE_DISCOUNTS_BY_TOUR_ID_QUERY);
    preparedStatement.setInt(1, tourId);
    int deletedRows = preparedStatement.executeUpdate();
    if (deletedRows == 0) throw new SQLException("Could not delete discounts with provided tour id");
  }
}
