package service;

import at.favre.lib.crypto.bcrypt.BCrypt;
import data.model.*;
import data.repository.UserRepository;
import org.json.simple.JSONObject;

import java.security.InvalidParameterException;
import java.sql.SQLException;

import static service.helper.JwtService.createJWT;
import static service.helper.validation.UserDataValidationService.*;
import static service.helper.UserHelperService.withHashedPassword;
import static utils.Constants.*;

public class AuthService {
  private final UserRepository repository;
  private static AuthService instance;
  private static String SUPER_SECRET_KEY = "secret";

  private AuthService(UserRepository repository) {
    this.repository = repository;
  }

  public static synchronized AuthService getInstance(UserRepository repository) {
    if (instance == null) {
      instance = new AuthService(repository);
    }
    return instance;
  }

  public JSONObject registerUser(JSONObject jsonObject) {
    String login = (String) jsonObject.get("login");
    String password = (String) jsonObject.get("password");
    String role = (String) jsonObject.get("role");
    String secretKey = (String) jsonObject.get("secretKey");
    if (login == null || password == null || role == null)
      return getFailObject();
    if (role.equals(Role.MANAGER.toString())) {
      if (checkSecretKey(secretKey)) return getFailedKeyJsonObject();
      return registerManager(new Manager(new Credentials(login, password, Role.valueOf(role))));
    } else if (role.equals(Role.CLIENT.toString())) {
      return registerClient(new Client(new Credentials(login, password, Role.valueOf(role))));
    } else {
      return getFailObject();
    }
  }

  private boolean checkSecretKey(String secretKey) {
    return !secretKey.equals(SUPER_SECRET_KEY);
  }

  private JSONObject getFailedKeyJsonObject() {
    JSONObject jsonObject = new JSONObject();
    jsonObject.put("error", 1);
    jsonObject.put("description", "Secret key is invalid");
    return jsonObject;
  }

  private JSONObject getFailObject() {
    JSONObject object = new JSONObject();
    object.put("error", 1);
    object.put("description", "Invalid data provided");
    return object;
  }

  private JSONObject registerManager(Manager manager) {
    JSONObject jsonObject = new JSONObject();
    try {
      getValidatedManager(manager, false, false);
    } catch (IllegalArgumentException ex) {
      jsonObject.put("error", 1);
      jsonObject.put("description", ex.getMessage());
      return jsonObject;
    }
    manager.setCredentials(withHashedPassword(manager.getCredentials()));
    Manager savedManager;
    try {
      savedManager = repository.saveManager(manager);
    } catch (SQLException ex) {
      jsonObject.put("error", 1);
      jsonObject.put("description", ex.getMessage());
      return jsonObject;
    }
    jsonObject.put(ERROR, 0);
    jsonObject.put(USER, savedManager.toJSON());
    jsonObject.put(TOKEN, createJWT(savedManager.getCredentials().getLogin(),
        savedManager.getCredentials().getRole()));
    return jsonObject;
  }

  private JSONObject registerClient(Client client) {
    JSONObject jsonObject = new JSONObject();
    try {
      getValidatedClient(client, false, false);
    } catch (IllegalArgumentException ex) {
      jsonObject.put("error", 1);
      jsonObject.put("description", ex.getMessage());
      return jsonObject;
    }
    client.setCredentials(withHashedPassword(client.getCredentials()));
    Client savedClient;
    try {
      savedClient = repository.saveClient(client);
    } catch (SQLException ex) {
      jsonObject.put("error", 1);
      jsonObject.put("description", ex.getMessage());
      return jsonObject;
    }
    jsonObject.put(ERROR, 0);
    jsonObject.put(USER, savedClient.toJSON());
    jsonObject.put(TOKEN, createJWT(savedClient.getCredentials().getLogin(),
        savedClient.getCredentials().getRole()));
    return jsonObject;
  }

  public JSONObject loginUser(JSONObject jsonObject) {
    JSONObject result = new JSONObject();
    String username = (String) jsonObject.get("login");
    String password = (String) jsonObject.get("password");
    Credentials credentials;
    try {
      credentials = repository.findCredentialsByUsername(username);
    } catch (SQLException ex) {
      throw new InvalidParameterException("Credentials are invalid");
    }
    //validate password hash
    try {
      if (
          BCrypt
              .verifyer()
              .verify(password.toCharArray(), credentials.getPassword())
              .verified
      ) {
        result.put("error", 0);
        result.put(ROLE, credentials.getRole().toString());
        result.put(TOKEN, createJWT(credentials.getLogin(), credentials.getRole()));
      } else {
        result.put("error", 1);
        result.put("description", "Credentials are invalid");
      }
    } catch (NullPointerException ex) {
      result.put("error", 1);
      result.put("description", "Credentials are invalid");
    }
    return result;
  }
}
