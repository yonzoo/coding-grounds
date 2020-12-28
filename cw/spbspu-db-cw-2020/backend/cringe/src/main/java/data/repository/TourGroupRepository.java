package data.repository;

import data.dao.ClientTourGroupDao;
import data.dao.Dao;
import data.dao.TourGroupDao;
import data.model.*;

import java.sql.SQLException;
import java.util.List;

public class TourGroupRepository {
  private static TourGroupRepository instance;
  private final TourGroupDao tourGroupDao;
  private final Dao<Client> clientDao;
  private final Dao<Tour> tourDao;
  private final ClientTourGroupDao clientTourGroupDao;

  private TourGroupRepository(
      TourGroupDao tourGroupDao,
      ClientTourGroupDao clientTourGroupDao,
      Dao<Client> clientDao,
      Dao<Tour> tourDao
  ) {
    this.tourGroupDao = tourGroupDao;
    this.clientTourGroupDao = clientTourGroupDao;
    this.clientDao = clientDao;
    this.tourDao = tourDao;
  }

  public static synchronized TourGroupRepository getInstance(
      TourGroupDao tourGroupDao,
      ClientTourGroupDao clientTourGroupDao,
      Dao<Client> clientDao,
      Dao<Tour> tourDao
  ) {
    if (instance == null) {
      instance = new TourGroupRepository(
          tourGroupDao,
          clientTourGroupDao,
          clientDao,
          tourDao
      );
    }
    return instance;
  }

  public TourGroup saveTourGroup(TourGroup tourGroup) throws SQLException {
    throwAnExceptionIfTourNotExists(tourGroup.getTourId());
    return tourGroupDao.create(tourGroup);
  }

  public TourGroup updateTourGroup(int id, TourGroup tourGroup) throws SQLException {
    throwAnExceptionIfTourNotExists(tourGroup.getTourId());
    return tourGroupDao.updateById(id, tourGroup);
  }

  public TourGroup findTourGroupById(int id) throws SQLException {
    return tourGroupDao.selectById(id);
  }

  public List<TourGroup> findAllTourGroups() throws SQLException {
    return tourGroupDao.selectAll();
  }

  public List<TourGroup> findTourGroupsByClientId(int clientId) throws SQLException {
    return tourGroupDao.selectByClientId(clientId);
  }

  public void addClientToTourGroup(int clientId, int tourGroupId) throws SQLException {
    Client client = clientDao.selectById(clientId);
    TourGroup tourGroup = tourGroupDao.selectById(tourGroupId);
    if (client == null || tourGroup == null)
      throw new SQLException("Client or tour was not found");
    clientTourGroupDao.create(new ClientTourGroup(clientId, tourGroupId));
  }

  public void deleteTourGroup(int id) throws SQLException {
    tourGroupDao.delete(id);
  }

  public void deleteClientFromTourGroup(int clientId, int tourGroupId) throws SQLException {
    ClientTourGroup clientTourGroup =
        clientTourGroupDao.selectClientTourGroupByClientIdAndTourGroupId(clientId, tourGroupId);
    if (clientTourGroup == null)
      throw new SQLException("Client or tour group with this client was not found");
    clientTourGroupDao.delete(clientTourGroup.getClientId(), clientTourGroup.getTourGroupId());
  }

  private void throwAnExceptionIfTourNotExists(int tourId) throws SQLException {
    Tour tour = tourDao.selectById(tourId);
    if (tour == null) {
      throw new SQLException("Tour for this tour group was not found");
    }
  }
}
