package data.repository;

import data.dao.ClientTourDao;
import data.dao.Dao;
import data.dao.DiscountDao;
import data.dao.TourDao;
import data.model.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TourRepository {
  private static TourRepository instance;
  private final Dao<Client> clientDao;
  private final TourDao tourDao;
  private final ClientTourDao clientTourDao;
  private final DiscountDao discountDao;

  private TourRepository(
      Dao<Client> clientDao,
      TourDao tourDao,
      ClientTourDao clientTourDao,
      DiscountDao discountDao
  ) {
    this.clientDao = clientDao;
    this.tourDao = tourDao;
    this.discountDao = discountDao;
    this.clientTourDao = clientTourDao;
  }

  public static synchronized TourRepository getInstance(
      Dao<Client> clientDao,
      TourDao tourDao,
      ClientTourDao clientTourDao,
      DiscountDao discountDao
  ) {
    if (instance == null) {
      instance = new TourRepository(clientDao, tourDao, clientTourDao, discountDao);
    }
    return instance;
  }

  public void saveClientTour(ClientTour clientTour) throws SQLException {
    Client client = clientDao.selectById(clientTour.getClientId());
    Tour tour = tourDao.selectById(clientTour.getTourId());
    if (client == null || tour == null)
      throw new SQLException("Client or tour was not found");
    clientTourDao.create(clientTour);
  }

  public Tour saveTour(Tour tour) throws SQLException {
    return tourDao.create(tour);
  }

  public Tour updateTour(int id, Tour tour) throws SQLException {
    int discountSum = discountDao
        .selectAllDiscountsByTourId(id)
        .stream()
        .map(Discount::getDiscountAmount)
        .mapToInt(Integer::intValue)
        .sum();
    if (discountSum >= tour.getPrice()) {
      throw new SQLException("Price is too low");
    }
    return tourDao.updateById(id, tour);
  }

  public Tour findTourById(int id) throws SQLException {
    return tourDao.selectById(id);
  }

  public List<Tour> findAllTours() throws SQLException {
    return tourDao.selectAll();
  }

  public List<Tour> findToursByClientId(int clientId) throws SQLException {
    return tourDao.selectByClientId(clientId);
  }

  public void deleteTour(int id) throws SQLException {
    tourDao.delete(id);
  }

  public void deleteClientFromTour(int clientId, int tourId) throws SQLException {
    ClientTour clientTour =
        clientTourDao.selectClientTourByClientIdAndTourId(clientId, tourId);
    if (clientTour == null)
      throw new SQLException("Client or tour with this client was not found");
    clientTourDao.delete(clientTour.getClientId(), clientTour.getTourId());
  }
}
