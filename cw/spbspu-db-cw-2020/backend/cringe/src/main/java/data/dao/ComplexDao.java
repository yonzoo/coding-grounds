package data.dao;

import data.config.DatabaseConfig;
import data.model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static data.dao.ClientDao.parseClient;
import static data.dao.DiscountDao.parseDiscount;
import static data.dao.TourDao.parseTour;
import static data.dao.TourGroupDao.parseTourGroup;
import static data.utils.constants.Queries.*;

public class ComplexDao {
  private static ComplexDao instance;
  private Connection connection;

  private ComplexDao() {
    connection = DatabaseConfig.getInstance().getConnection();
  }

  public static synchronized ComplexDao getInstance() {
    if (instance == null) {
      instance = new ComplexDao();
    }
    return instance;
  }

  public List<Client> getFromNoTour() throws SQLException {
    return getClients(GET_FROM_NO_TOUR_QUERY);
  }

  public List<Client> getFromManyTours() throws SQLException {
    return getClients(GET_FROM_MANY_TOURS_QUERY);
  }

  public List<Tour> getFromNextTour() throws SQLException {
    return getTours(connection, GET_FROM_NEXT_TOUR_QUERY);
  }

  public List<Tour> getCheapTour() throws SQLException {
    return getTours(connection, GET_FROM_CHEAP_TOUR_QUERY);
  }

  public int getSalesSum() throws SQLException {
    PreparedStatement preparedStatement = connection.prepareStatement(GET_FROM_SALES_SUM_QUERY);
    ResultSet rs = preparedStatement.executeQuery();
    if (rs.next()) {
      return rs.getInt("SalesSum");
    }
    return 0;
  }

  public List<Tour> getSummerTours() throws SQLException {
    return getTours(connection, GET_SUMMER_TOURS_QUERY);
  }

  public List<Tour> getYearMostPopularTour() throws SQLException {
    return getTours(connection, GET_YEAR_MOST_POPULAR_TOUR_QUERY);
  }

  public List<Client> getTourParticipantsByTourDate(Date date) throws SQLException {
    PreparedStatement preparedStatement = connection.prepareStatement(GET_TOUR_PARTICIPANTS_BY_DATE_QUERY);
    preparedStatement.setDate(1, date);
    ResultSet rs = preparedStatement.executeQuery();
    List<Client> result = new ArrayList<>();
    while (rs.next()) {
      result.add(parseClient(rs));
    }
    return result;
  }

  public List<TourGroup> getTourTourGroups(int tourId) throws SQLException {
    PreparedStatement preparedStatement = connection.prepareStatement(GET_TOUR_TOUR_GROUPS_QUERY);
    preparedStatement.setInt(1, tourId);
    ResultSet rs = preparedStatement.executeQuery();
    List<TourGroup> result = new ArrayList<>();
    while (rs.next()) {
      result.add(parseTourGroup(rs));
    }
    return result;
  }

  public List<Tour> getToursWithApprovedRequests() throws SQLException {
    return getTours(connection, GET_TOURS_WITH_REQUESTS_QUERY);
  }

  public List<Tour> getToursWithoutAnyRequests() throws SQLException {
    return getTours(connection, GET_TOURS_WITHOUT_REQUESTS_QUERY);
  }

  public List<TourGroup> getVacantTourGroupsQuery() throws SQLException {
    return getTourGroups(GET_VACANT_TOUR_GROUPS_QUERY);
  }

  public List<TourGroup> getTourGroupsByTourDate(Date tourDate) throws SQLException {
    PreparedStatement preparedStatement = connection.prepareStatement(GET_TOUR_GROUPS_BY_TOUR_DATE);
    preparedStatement.setDate(1, tourDate);
    ResultSet rs = preparedStatement.executeQuery();
    List<TourGroup> result = new ArrayList<>();
    while (rs.next()) {
      result.add(parseTourGroup(rs));
    }
    return result;
  }

  public List<Discount> getTourDiscounts(int tourId) throws SQLException {
    PreparedStatement preparedStatement = connection.prepareStatement(GET_TOUR_DISCOUNTS);
    preparedStatement.setInt(1, tourId);
    ResultSet rs = preparedStatement.executeQuery();
    List<Discount> result = new ArrayList<>();
    while (rs.next()) {
      result.add(parseDiscount(rs));
    }
    return result;
  }

  public List<Tour> getClientToursByDocumentNumber(int documentNumber) throws SQLException {
    PreparedStatement preparedStatement = connection.prepareStatement(GET_CLIENT_TOURS_BY_DOCUMENT_NUMBER);
    preparedStatement.setInt(1, documentNumber);
    ResultSet rs = preparedStatement.executeQuery();
    List<Tour> result = new ArrayList<>();
    while (rs.next()) {
      result.add(parseTour(rs));
    }
    return result;
  }

  public List<Sale> getMonthSales() throws SQLException {
    return getSales(GET_MONTH_SALES);
  }

  public List<Sale> getYearSales() throws SQLException {
    return getSales(GET_YEAR_SALES);
  }

  private List<Sale> getSales(String getYearSales) throws SQLException {
    PreparedStatement preparedStatement = connection.prepareStatement(getYearSales);
    ResultSet rs = preparedStatement.executeQuery();
    List<Sale> result = new ArrayList<>();
    while (rs.next()) {
      result.add(parseSale(rs));
    }
    return result;
  }

  private Sale parseSale(ResultSet rs) throws SQLException {
    int tourId = rs.getInt("TourId");
    String tourName = rs.getString("TourName");
    int salesSum = rs.getInt("SalesSum");
    return new Sale(tourId, tourName, salesSum);
  }

  private Tour getTour(String getTourQuery) throws SQLException {
    PreparedStatement preparedStatement = connection.prepareStatement(getTourQuery);
    ResultSet rs = preparedStatement.executeQuery();
    if (rs.next()) {
      return parseTour(rs);
    }
    return null;
  }

  static List<Tour> getTours(Connection connection, String getTourQuery) throws SQLException {
    PreparedStatement preparedStatement = connection.prepareStatement(getTourQuery);
    ResultSet rs = preparedStatement.executeQuery();
    List<Tour> result = new ArrayList<>();
    while (rs.next()) {
      result.add(parseTour(rs));
    }
    return result;
  }

  private List<TourGroup> getTourGroups(String getFromNextTourGroupQuery) throws SQLException {
    PreparedStatement preparedStatement = connection.prepareStatement(getFromNextTourGroupQuery);
    ResultSet rs = preparedStatement.executeQuery();
    List<TourGroup> result = new ArrayList<>();
    while (rs.next()) {
      result.add(parseTourGroup(rs));
    }
    return result;
  }

  private List<Client> getClients(String getFromManyToursQuery) throws SQLException {
    PreparedStatement preparedStatement = connection.prepareStatement(getFromManyToursQuery);
    ResultSet rs = preparedStatement.executeQuery();
    List<Client> result = new ArrayList<>();
    while (rs.next()) {
      result.add(parseClient(rs));
    }
    return result;
  }
}
