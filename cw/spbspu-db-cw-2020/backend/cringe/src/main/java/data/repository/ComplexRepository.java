package data.repository;

import data.dao.ComplexDao;
import data.model.*;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class ComplexRepository {
  private static ComplexRepository instance;
  private UserRepository userRepository;
  private ComplexDao complexDao;

  private ComplexRepository(
      ComplexDao complexDao,
      UserRepository userRepository
  ) {
    this.complexDao = complexDao;
    this.userRepository = userRepository;
  }

  public static synchronized ComplexRepository getInstance(
      ComplexDao complexDao,
      UserRepository userRepository
  ) {
    if (instance == null) {
      instance = new ComplexRepository(complexDao, userRepository);
    }
    return instance;
  }

  public List<Client> getFromNoTour() throws SQLException {
    List<Client> clientsWithoutCredentials = complexDao.getFromNoTour();
    return clientsWithoutCredentials.stream().map(client -> {
      try {
        return userRepository.findClientByAccountId(client.getAccountId());
      } catch (SQLException ex) {
        ex.printStackTrace();
      }
      return null;
    }).collect(Collectors.toList());
  }

  public List<Client> getFromManyTours() throws SQLException {
    List<Client> clientsWithoutCredentials = complexDao.getFromManyTours();
    return clientsWithoutCredentials.stream().map(client -> {
      try {
        return userRepository.findClientByAccountId(client.getAccountId());
      } catch (SQLException ex) {
        ex.printStackTrace();
      }
      return null;
    }).collect(Collectors.toList());
  }

  public List<Tour> getFromNextTour() throws SQLException {
    return complexDao.getFromNextTour();
  }

  public List<Tour> getCheapTour() throws SQLException {
    return complexDao.getCheapTour();
  }

  public int getSalesSum() throws SQLException {
    return complexDao.getSalesSum();
  }

  public List<Tour> getSummerTours() throws SQLException {
    return complexDao.getSummerTours();
  }

  public List<Tour> getYearMostPopularTour() throws SQLException {
    return complexDao.getYearMostPopularTour();
  }

  public List<Client> getTourParticipantsByTourDate(Date date) throws SQLException {
   return complexDao.getTourParticipantsByTourDate(date);
  }

  public List<TourGroup> getTourTourGroups(int tourId) throws SQLException {
    return complexDao.getTourTourGroups(tourId);
  }

  public List<Tour> getToursWithApprovedRequests() throws SQLException {
    return complexDao.getToursWithApprovedRequests();
  }

  public List<Tour> getToursWithoutAnyRequests() throws SQLException {
    return complexDao.getToursWithoutAnyRequests();
  }

  public List<TourGroup> getVacantTourGroupsQuery() throws SQLException {
    return complexDao.getVacantTourGroupsQuery();
  }

  public List<TourGroup> getTourGroupsByTourDate(Date tourDate) throws SQLException {
    return complexDao.getTourGroupsByTourDate(tourDate);
  }

  public List<Discount> getTourDiscounts(int tourId) throws SQLException {
    return complexDao.getTourDiscounts(tourId);
  }

  public List<Tour> getClientToursByDocumentNumber(int documentNumber) throws SQLException {
    return complexDao.getClientToursByDocumentNumber(documentNumber);
  }

  public List<Sale> getMonthSales() throws SQLException {
    return complexDao.getMonthSales();
  }

  public List<Sale> getYearSales() throws SQLException {
    return complexDao.getYearSales();
  }
}
