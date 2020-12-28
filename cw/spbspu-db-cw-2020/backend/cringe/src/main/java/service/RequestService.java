package service;

import data.model.Request;
import data.model.TourGroup;
import data.repository.RequestRepository;
import data.repository.UserRepository;
import org.json.JSONArray;
import org.json.simple.JSONObject;

import java.sql.SQLException;
import java.util.List;

import static service.helper.JwtService.decodeJWT;
import static utils.Constants.*;

public class RequestService {
  private final RequestRepository repository;
  private static RequestService instance;

  private RequestService(RequestRepository repository) {
    this.repository = repository;
  }

  public static synchronized RequestService getInstance(RequestRepository repository) {
    if (instance == null) {
      instance = new RequestService(repository);
    }
    return instance;
  }

  public JSONObject createRequest(JSONObject reqJson) {
    JSONObject jsonObject = new JSONObject();
    try {
      Request request = repository.saveRequest(parseRequest(reqJson));
      jsonObject.put(ERROR, 0);
      jsonObject.put(REQUEST, request.toJSON());
    } catch (NullPointerException | IllegalArgumentException | SQLException ex) {
      jsonObject.put(ERROR, 1);
      jsonObject.put(DESCRIPTION, ex.getMessage());
    }
    return jsonObject;
  }

  public JSONObject createRequest(int tourId, int clientId) {
    JSONObject jsonObject = new JSONObject();
    try {
      repository.saveRequest(new Request(tourId, clientId, false));
      jsonObject.put(ERROR, 0);
    } catch (NullPointerException | IllegalArgumentException | SQLException ex) {
      jsonObject.put(ERROR, 1);
      jsonObject.put(DESCRIPTION, ex.getMessage());
    }
    return jsonObject;
  }

  public JSONObject getRequest(JSONObject reqJson) {
    JSONObject jsonObject = new JSONObject();
    try {
      int requestId = Integer.parseInt((String) reqJson.get("requestId"));
      Request request = repository.findRequestById(requestId);
      jsonObject.put(ERROR, 0);
      jsonObject.put(REQUEST, request.toJSON());
    } catch (NullPointerException | IllegalArgumentException | SQLException ex) {
      jsonObject.put(ERROR, 1);
      jsonObject.put(DESCRIPTION, ex.getMessage());
    }
    return jsonObject;
  }

  public JSONObject getAllRequests() {
    JSONObject jsonObject = new JSONObject();
    try {
      JSONArray requestsArray = new JSONArray();
      repository.findAllRequests().stream().map(Request::toJSON).forEach(requestsArray::put);
      jsonObject.put(ERROR, 0);
      jsonObject.put(REQUEST_ARRAY, requestsArray);
    } catch (NullPointerException | IllegalArgumentException | SQLException ex) {
      jsonObject.put(ERROR, 1);
      jsonObject.put(DESCRIPTION, ex.getMessage());
    }
    return jsonObject;
  }

  public JSONObject getUserRequests(int clientId) {
    JSONObject jsonObject = new JSONObject();
    try {
      JSONArray requestsArray = new JSONArray();
      repository.findRequestsByClientId(clientId).stream().map(Request::toJSON).forEach(requestsArray::put);
      jsonObject.put(ERROR, 0);
      jsonObject.put(REQUEST_ARRAY, requestsArray);
    } catch (NullPointerException | IllegalArgumentException | SQLException ex) {
      jsonObject.put(ERROR, 1);
      jsonObject.put(DESCRIPTION, ex.getMessage());
    }
    return jsonObject;
  }

  public JSONObject updateRequest(JSONObject reqJson) {
    JSONObject jsonObject = new JSONObject();
    try {
      int requestId = Integer.parseInt((String) reqJson.get("id"));
      Request request = repository.updateRequest(requestId, parseRequest(reqJson));
      JSONArray requestsJsonArray = new JSONArray();
      requestsJsonArray.put(request.toJSON());
      jsonObject.put(ERROR, 0);
      jsonObject.put(REQUEST_ARRAY, requestsJsonArray);
    } catch (NullPointerException | IllegalArgumentException | SQLException ex) {
      jsonObject.put(ERROR, 1);
      jsonObject.put(DESCRIPTION, ex.getMessage());
    }
    return jsonObject;
  }

  public JSONObject deleteRequest(JSONObject reqJson) {
    JSONObject jsonObject = new JSONObject();
    try {
      int requestId = Integer.parseInt((String) reqJson.get("id"));
      repository.deleteRequest(requestId);
      jsonObject.put(ERROR, 0);
    } catch (SQLException ex) {
      jsonObject.put(ERROR, 1);
      jsonObject.put(DESCRIPTION, ex.getMessage());
    }
    return jsonObject;
  }

  private Request parseRequest(JSONObject jsonObject) {
    int tourId = jsonObject.get("tourId") == null ?
        0 : Integer.parseInt((String) jsonObject.get("tourId"));
    int clientId = jsonObject.get("clientId") == null ?
        0 : Integer.parseInt((String) jsonObject.get("clientId"));
    boolean isApproved = jsonObject.get("isApproved") != null && (boolean) jsonObject.get("isApproved");
    return new Request(tourId, clientId, isApproved);
  }
}
