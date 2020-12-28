package data.repository;

import data.dao.CredentialsDao;
import data.dao.UserDao;
import data.model.Client;
import data.model.Credentials;
import data.model.Manager;

import java.sql.SQLException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class UserRepository {
  private final CredentialsDao credentialsDao;
  private static UserRepository instance;
  private final UserDao<Manager> managerDao;
  private final UserDao<Client> clientDao;

  private UserRepository(
      CredentialsDao credentialsDao,
      UserDao<Manager> managerDao,
      UserDao<Client> clientDao
  ) {
    this.credentialsDao = credentialsDao;
    this.managerDao = managerDao;
    this.clientDao = clientDao;
  }

  public static synchronized UserRepository getInstance(
      CredentialsDao credentialsDao,
      UserDao<Manager> managerDao,
      UserDao<Client> clientDao
  ) {
    if (instance == null) {
      instance = new UserRepository(credentialsDao, managerDao, clientDao);
    }
    return instance;
  }

  public Manager saveManager(Manager user) throws SQLException {
    Credentials savedCredentials = credentialsDao.create(user.getCredentials());
    user.setCredentials(savedCredentials);
    Manager savedManager = managerDao.create(user);
    savedManager.setCredentials(credentialsDao.selectById(savedManager.getAccountId()));
    return savedManager;
  }

  public Client saveClient(Client user) throws SQLException {
    Credentials savedCredentials = credentialsDao.create(user.getCredentials());
    user.setCredentials(savedCredentials);
    Client savedClient = clientDao.create(user);
    savedClient.setCredentials(credentialsDao.selectById(savedClient.getAccountId()));
    return savedClient;
  }

  public Manager updateManager(int id, Manager user) throws SQLException {
    Credentials updatedCredentials = credentialsDao.updateById(
        user.getCredentials().getId(), user.getCredentials()
    );
    Manager updatedManager = managerDao.updateById(id, user);
    updatedManager.setCredentials(updatedCredentials);
    return updatedManager;
  }

  public Client updateClient(int id, Client user) throws SQLException {
    Credentials updatedCredentials = credentialsDao.updateById(
        user.getCredentials().getId(), user.getCredentials()
    );
    Client updatedClient = clientDao.updateById(id, user);
    updatedClient.setCredentials(updatedCredentials);
    return updatedClient;
  }

  public Credentials findCredentialsByUsername(String username) throws SQLException {
    return credentialsDao.selectByLogin(username);
  }

  public List<Client> findAllClients() throws SQLException {
    List<Client> clientsWithoutCredentials = clientDao.selectAll();
    return clientsWithoutCredentials.stream().map(client -> {
      try {
        return findClientByAccountId(client.getAccountId());
      } catch (SQLException ex) {
        ex.printStackTrace();
      }
      return null;
    }).collect(Collectors.toList());
  }

  public Manager findManagerByUsername(String username) throws SQLException {
    Credentials credentials = credentialsDao.selectByLogin(username);
    if (credentials != null) {
      Manager user = managerDao.selectByAccountId(credentials.getId());
      if (user != null) {
        user.setCredentials(credentials);
        return user;
      }
    }
    throw new NoSuchElementException("Manager with such username was not found");
  }

  public Client findClientByUsername(String username) throws SQLException {
    Credentials credentials = credentialsDao.selectByLogin(username);
    if (credentials != null) {
      Client user = clientDao.selectByAccountId(credentials.getId());
      if (user != null) {
        user.setCredentials(credentials);
        return user;
      }
    }
    throw new NoSuchElementException("Client with such username was not found");
  }

  public Client findClientByAccountId(int id) throws SQLException {
    Credentials credentials = credentialsDao.selectById(id);
    if (credentials != null) {
      Client user = clientDao.selectByAccountId(credentials.getId());
      if (user != null) {
        user.setCredentials(credentials);
        return user;
      }
    }
    throw new NoSuchElementException("Client with such id was not found");
  }

  public void deleteManager(int accountId) throws SQLException {
    Credentials credentials = credentialsDao.selectById(accountId);
    if (credentials != null) {
      Manager manager = managerDao.selectByAccountId(credentials.getId());
      if (manager != null) {
        managerDao.delete(manager.getId());
      }
      credentialsDao.delete(credentials.getId());
    }
  }

  public void deleteClient(int accountId) throws SQLException {
    Credentials credentials = credentialsDao.selectById(accountId);
    if (credentials != null) {
      Client client = clientDao.selectByAccountId(credentials.getId());
      if (client != null) {
        clientDao.delete(client.getId());
      }
      credentialsDao.delete(credentials.getId());
    }
  }
}
