package service;

import data.model.TourGroup;
import data.repository.TourGroupRepository;
import org.json.JSONArray;
import org.json.simple.JSONObject;

import java.sql.SQLException;

import static service.helper.validation.TourGroupValidationService.getValidatedTourGroup;
import static utils.Constants.*;

public class TourGroupService {
  private final TourGroupRepository repository;
  private static TourGroupService instance;

  private TourGroupService(TourGroupRepository repository) {
    this.repository = repository;
  }

  public static synchronized TourGroupService getInstance(TourGroupRepository repository) {
    if (instance == null) {
      instance = new TourGroupService(repository);
    }
    return instance;
  }

  public JSONObject createTourGroup(JSONObject reqJson) {
    JSONObject jsonObject = new JSONObject();
    try {
      TourGroup parsedTourGroup = parseTourGroup(reqJson);
      getValidatedTourGroup(parsedTourGroup);
      TourGroup tourGroup = repository.saveTourGroup(parsedTourGroup);
      JSONArray tourGroupsJsonArray = new JSONArray();
      tourGroupsJsonArray.put(tourGroup.toJSON());
      jsonObject.put(ERROR, 0);
      jsonObject.put(TOUR_GROUPS_ARRAY, tourGroupsJsonArray);
    } catch (NullPointerException | IllegalArgumentException | SQLException ex) {
      jsonObject.put(ERROR, 1);
      jsonObject.put(DESCRIPTION, ex.getMessage());
    }
    return jsonObject;
  }

  public JSONObject addClientToTourGroup(JSONObject reqJson) {
    JSONObject jsonObject = new JSONObject();
    try {
      int clientId = Integer.parseInt((String) reqJson.get("clientId"));
      int tourGroupId = Integer.parseInt((String) reqJson.get("tourGroupId"));
      repository.addClientToTourGroup(clientId, tourGroupId);
      jsonObject.put(ERROR, 0);
    } catch (NullPointerException | IllegalArgumentException | SQLException ex) {
      jsonObject.put(ERROR, 1);
      jsonObject.put(DESCRIPTION, ex.getMessage());
    }
    return jsonObject;
  }

  public JSONObject deleteClientFromTourGroup(JSONObject reqJson) {
    JSONObject jsonObject = new JSONObject();
    try {
      int clientId = Integer.parseInt((String) reqJson.get("clientId"));
      int tourGroupId = Integer.parseInt((String) reqJson.get("tourGroupId"));
      repository.deleteClientFromTourGroup(clientId, tourGroupId);
      jsonObject.put(ERROR, 0);
    } catch (SQLException ex) {
      jsonObject.put(ERROR, 1);
      jsonObject.put(DESCRIPTION, ex.getMessage());
    }
    return jsonObject;
  }

  public JSONObject getTourGroup(JSONObject reqJson) {
    JSONObject jsonObject = new JSONObject();
    try {
      int tourGroupId = Integer.parseInt((String) reqJson.get("tourGroupId"));
      TourGroup tourGroup = repository.findTourGroupById(tourGroupId);
      jsonObject.put(ERROR, 0);
      jsonObject.put(TOUR_GROUP, tourGroup.toJSON());
    } catch (NullPointerException | IllegalArgumentException | SQLException ex) {
      jsonObject.put(ERROR, 1);
      jsonObject.put(DESCRIPTION, ex.getMessage());
    }
    return jsonObject;
  }

  public JSONObject getClientTourGroups(JSONObject reqJson) {
    JSONObject jsonObject = new JSONObject();
    try {
      int clientId = Integer.parseInt((String) reqJson.get("clientId"));
      JSONArray tourGroupsArray = new JSONArray();
      repository.findTourGroupsByClientId(clientId).stream().map(TourGroup::toJSON).forEach(tourGroupsArray::put);
      jsonObject.put(ERROR, 0);
      jsonObject.put(TOUR_GROUPS_ARRAY, tourGroupsArray);
    } catch (NullPointerException | IllegalArgumentException | SQLException ex) {
      jsonObject.put(ERROR, 1);
      jsonObject.put(DESCRIPTION, ex.getMessage());
    }
    return jsonObject;
  }

  public JSONObject getAllTourGroups() {
    JSONObject jsonObject = new JSONObject();
    try {
      JSONArray tourGroupsArray = new JSONArray();
      repository.findAllTourGroups().stream().map(TourGroup::toJSON).forEach(tourGroupsArray::put);
      jsonObject.put(ERROR, 0);
      jsonObject.put(TOUR_GROUPS_ARRAY, tourGroupsArray);
    } catch (SQLException ex) {
      jsonObject.put(ERROR, 1);
      jsonObject.put(DESCRIPTION, ex.getMessage());
    }
    return jsonObject;
  }

  public JSONObject updateTourGroup(JSONObject reqJson) {
    JSONObject jsonObject = new JSONObject();
    try {
      int tourGroupId = Integer.parseInt((String) reqJson.get("id"));
      TourGroup parsedTourGroup = parseTourGroup(reqJson);
      getValidatedTourGroup(parsedTourGroup);
      TourGroup tourGroup = repository.updateTourGroup(tourGroupId, parsedTourGroup);
      jsonObject.put(ERROR, 0);
      jsonObject.put(TOUR_GROUP, tourGroup.toJSON());
    } catch (NullPointerException | IllegalArgumentException | SQLException ex) {
      jsonObject.put(ERROR, 1);
      jsonObject.put(DESCRIPTION, ex.getMessage());
    }
    return jsonObject;
  }

  public JSONObject deleteTourGroup(JSONObject reqJson) {
    JSONObject jsonObject = new JSONObject();
    try {
      int tourGroupId = Integer.parseInt((String) reqJson.get("id"));
      repository.deleteTourGroup(tourGroupId);
      jsonObject.put(ERROR, 0);
    } catch (SQLException ex) {
      jsonObject.put(ERROR, 1);
      jsonObject.put(DESCRIPTION, ex.getMessage());
    }
    return jsonObject;
  }

  private TourGroup parseTourGroup(JSONObject jsonObject) {
    String name = (String) jsonObject.get("name");
    String description = (String) jsonObject.get("description");
    int tourId = Integer.parseInt((String) jsonObject.get("tourId"));
    int maxAmount = Integer.parseInt((String) jsonObject.get("maxAmount"));
    return new TourGroup(
        tourId,
        name,
        description,
        maxAmount
    );
  }
}
