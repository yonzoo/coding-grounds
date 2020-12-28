package service;

import data.model.*;
import data.repository.UserRepository;
import org.json.JSONArray;
import org.json.simple.JSONObject;
import service.helper.UserHelperService;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import static data.model.Role.*;
import static service.helper.JwtService.decodeJWT;
import static service.helper.validation.UserDataValidationService.getValidatedClient;
import static service.helper.validation.UserDataValidationService.getValidatedManager;
import static utils.Constants.*;

public class UserService {
  private final UserRepository repository;
  private static UserService instance;

  private UserService(UserRepository repository) {
    this.repository = repository;
  }

  public static synchronized UserService getInstance(UserRepository repository) {
    if (instance == null) {
      instance = new UserService(repository);
    }
    return instance;
  }

  public JSONObject getUserData(String token) {
    JSONObject jsonObject = new JSONObject();
    String username = decodeJWT(token).getId();
    Role role = Role.valueOf(decodeJWT(token).getSubject());
    try {
      if (role.equals(MANAGER)) {
        Manager manager = repository.findManagerByUsername(username);
        jsonObject.put(ERROR, 0);
        jsonObject.put(USER, manager.toJSON());
      } else if (role.equals(CLIENT)) {
        Client client = repository.findClientByUsername(username);
        jsonObject.put(ERROR, 0);
        jsonObject.put(USER, client.toJSON());
      }
    } catch (SQLException ex) {
      jsonObject.put(ERROR, 1);
      jsonObject.put(DESCRIPTION, ex.getMessage());
    }
    return jsonObject;
  }

  public Client getClientDataAsObject(String token) throws SQLException {
    String username = decodeJWT(token).getId();
    return repository.findClientByUsername(username);
  }

  private Manager parseManager(JSONObject jsonObject, int accountId) {
    String login = (String) jsonObject.get("login");
    String password = (String) jsonObject.get("password");
    String name = (String) jsonObject.get("name");
    String phoneNumber = (String) jsonObject.get("phoneNumber");
    String email = (String) jsonObject.get("email");
    return new Manager(
        new Credentials(accountId, login, password, MANAGER),
        name,
        phoneNumber,
        email
    );
  }
  
  public JSONObject getAllClients() {
    JSONObject jsonObject = new JSONObject();
    try {
      JSONArray clientsArray = new JSONArray();
      repository.findAllClients().stream().map(Client::toJSON).forEach(clientsArray::put);
      jsonObject.put(ERROR, 0);
      jsonObject.put(CLIENTS_ARRAY, clientsArray);
    } catch (SQLException ex) {
      jsonObject.put(ERROR, 1);
      jsonObject.put(DESCRIPTION, ex.getMessage());
    }
    return jsonObject;
  }

  private Client parseClient(JSONObject jsonObject, int accountId) {
    String login = (String) jsonObject.get("login");
    String password = (String) jsonObject.get("password");
    String name = (String) jsonObject.get("name");
    Date birthDate;
    try {
      birthDate = jsonObject.get("birthDate") != "" ?
          Date.valueOf((String) jsonObject.get("birthDate"))
          : null;
    } catch (IllegalArgumentException ex) {
      birthDate = null;
    }
    String phoneNumber = (String) jsonObject.get("phoneNumber");
    String email = (String) jsonObject.get("email");
    String documentNumber = (String) jsonObject.get("documentNumber");
    return new Client(
        new Credentials(accountId, login, password, CLIENT),
        name,
        birthDate,
        phoneNumber,
        email,
        documentNumber
    );
  }

  public JSONObject updateUserData(String token, JSONObject newUserObject) {
    JSONObject jsonObject = new JSONObject();
    String username = decodeJWT(token).getId();
    Role role = Role.valueOf(decodeJWT(token).getSubject());
    try {
      Credentials credentials = repository.findCredentialsByUsername(username);
      if (role.equals(MANAGER)) {
        updateManager(newUserObject, jsonObject, username, credentials);
      } else if (role.equals(CLIENT)) {
        updateClient(newUserObject, jsonObject, username, credentials);
      }
    } catch (NullPointerException | IllegalArgumentException | SQLException ex) {
      ex.printStackTrace();
      jsonObject.put(ERROR, 1);
      jsonObject.put(DESCRIPTION, ex.getMessage() == null ?
          "You are not authorized" : ex.getMessage()
      );
    }
    return jsonObject;
  }

  private void updateManager(JSONObject newUserObject, JSONObject jsonObject, String username, Credentials credentials) throws SQLException {
    Manager parsedManager = parseManager(newUserObject, credentials.getId());
    Manager oldManager = repository.findManagerByUsername(username);
    if (parsedManager.getCredentials().getPassword().equals("*******")) {
      parsedManager.getCredentials().setPassword(oldManager.getCredentials().getPassword());
    }
    boolean isDefaultLoginProvided = isDefaultLoginProvided(
        oldManager.getCredentials(), parsedManager.getCredentials()
    );
    boolean isDefaultPasswordProvided = isDefaultPasswordProvided(
        oldManager.getCredentials(), parsedManager.getCredentials()
    );
    getValidatedManager(parsedManager, isDefaultLoginProvided, isDefaultPasswordProvided);
    if (!isDefaultPasswordProvided) {
      parsedManager.setCredentials(
          UserHelperService.withHashedPassword(parsedManager.getCredentials())
      );
      jsonObject.put(REDIRECT, 1);
    }
    if (!isDefaultLoginProvided) {
      jsonObject.put(REDIRECT, 1);
    }
    Manager updatedManager = repository.updateManager(
        oldManager.getId(), parsedManager
    );
    jsonObject.put(ERROR, 0);
    jsonObject.put(USER, updatedManager.toJSON());
  }

  private void updateClient(JSONObject newUserObject, JSONObject jsonObject, String username, Credentials credentials) throws SQLException {
    Client parsedClient = parseClient(newUserObject, credentials.getId());
    Client oldClient = repository.findClientByUsername(username);
    if (parsedClient.getCredentials().getPassword().equals("*******")) {
      parsedClient.getCredentials().setPassword(oldClient.getCredentials().getPassword());
    }
    boolean isDefaultLoginProvided = isDefaultLoginProvided(
        oldClient.getCredentials(), parsedClient.getCredentials()
    );
    boolean isDefaultPasswordProvided = isDefaultPasswordProvided(
        oldClient.getCredentials(), parsedClient.getCredentials()
    );
    getValidatedClient(parsedClient, isDefaultLoginProvided, isDefaultPasswordProvided);
    if (!isDefaultPasswordProvided) {
      parsedClient.setCredentials(
          UserHelperService.withHashedPassword(parsedClient.getCredentials())
      );
      jsonObject.put(REDIRECT, 1);
    }
    if (!isDefaultLoginProvided) {
      jsonObject.put(REDIRECT, 1);
    }
    Client updatedClient = repository.updateClient(
        oldClient.getId(), parsedClient
    );
    jsonObject.put(ERROR, 0);
    jsonObject.put(USER, updatedClient.toJSON());
  }

  private boolean isDefaultLoginProvided(Credentials oldCredentials, Credentials parsedCredentials) {
    return oldCredentials.getLogin().equals(parsedCredentials.getLogin());
  }

  private boolean isDefaultPasswordProvided(Credentials oldCredentials, Credentials parsedCredentials) {
    return oldCredentials.getPassword().equals(parsedCredentials.getPassword());
  }

  public boolean checkIfAdminOrManager(String token) {
    Role role = Role.valueOf(decodeJWT(token).getSubject());
    return role.equals(ADMIN) || role.equals(MANAGER);
  }

  public JSONObject deleteUserData(String token) {
    JSONObject jsonObject = new JSONObject();
    String username = decodeJWT(token).getId();
    Role role = Role.valueOf(decodeJWT(token).getSubject());
    try {
      if (role.equals(MANAGER)) {
        Manager manager = repository.findManagerByUsername(username);
        repository.deleteManager(manager.getAccountId());
        jsonObject.put(ERROR, 0);
      } else if (role.equals(CLIENT)) {
        Client client = repository.findClientByUsername(username);
        repository.deleteClient(client.getAccountId());
        jsonObject.put(ERROR, 0);
      }
    } catch (SQLException ex) {
      jsonObject.put(ERROR, 1);
      jsonObject.put(DESCRIPTION, ex.getMessage());
    }
    return jsonObject;
  }
}
