package data.repository;

import data.dao.ClientTourDao;
import data.dao.Dao;
import data.dao.RequestDao;
import data.model.ClientTour;
import data.model.Request;

import java.sql.SQLException;
import java.util.List;

public class RequestRepository {
  private static RequestRepository instance;
  private final RequestDao requestDao;
  private final ClientTourDao clientTourDao;

  private RequestRepository(
      RequestDao requestDao,
      ClientTourDao clientTourDao
  ) {
    this.requestDao = requestDao;
    this.clientTourDao = clientTourDao;
  }

  public static synchronized RequestRepository getInstance(
      RequestDao requestDao,
      ClientTourDao clientTourDao
  ) {
    if (instance == null) {
      instance = new RequestRepository(requestDao, clientTourDao);
    }
    return instance;
  }

  public Request saveRequest(Request request) throws SQLException {
    List<Request> existingRequests = requestDao.selectByClientIdAndTourId(
        request.getClientId(), request.getTourId()
    );
    if (!existingRequests.isEmpty())
      throw new SQLException("This user already has request for this tour");
    Request savedRequest = requestDao.create(request);
    saveClientTourIfRequestIsApproved(savedRequest);
    return savedRequest;
  }

  private void saveClientTourIfRequestIsApproved(Request request) throws SQLException {
    ClientTour existedClientTour = clientTourDao.selectClientTourByClientIdAndTourId(
        request.getClientId(),
        request.getTourId()
    );
    if (request.isApproved()) {
      if (existedClientTour == null) {
        clientTourDao.create(
            new ClientTour(request.getClientId(), request.getTourId())
        );
      }
    } else {
      if (existedClientTour != null) {
        clientTourDao.delete(request.getClientId(), request.getTourId());
      }
    }
  }

  public Request updateRequest(int id, Request request) throws SQLException {
    Request updatedRequest = requestDao.updateById(id, request);
    saveClientTourIfRequestIsApproved(updatedRequest);
    return updatedRequest;
  }

  public Request findRequestById(int id) throws SQLException {
    return requestDao.selectById(id);
  }

  public List<Request> findRequestsByClientId(int clientId) throws SQLException {
    return requestDao.selectByClientId(clientId);
  }

  public List<Request> findAllRequests() throws SQLException {
    return requestDao.selectAll();
  }

  public void deleteRequest(int id) throws SQLException {
    requestDao.delete(id);
  }
}
