package service;

import data.model.ClientTour;
import data.model.Tour;
import data.repository.TourRepository;
import org.json.JSONArray;
import org.json.simple.JSONObject;
import service.helper.validation.TourValidationService;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Arrays;

import static utils.Constants.*;

public class TourService {
  private final TourRepository repository;
  private static TourService instance;

  private TourService(TourRepository repository) {
    this.repository = repository;
  }

  public static synchronized TourService getInstance(TourRepository repository) {
    if (instance == null) {
      instance = new TourService(repository);
    }
    return instance;
  }

  public JSONObject createTour(JSONObject reqJson) {
    JSONObject jsonObject = new JSONObject();
    try {
      TourValidationService.getValidatedTour(parseTour(reqJson));
      Tour tour = repository.saveTour(parseTour(reqJson));
      JSONArray toursJsonArray = new JSONArray();
      toursJsonArray.put(tour.toJSON());
      jsonObject.put(ERROR, 0);
      jsonObject.put(TOURS_ARRAY, toursJsonArray);
    } catch (SQLException | IllegalArgumentException ex) {
      jsonObject.put(ERROR, 1);
      jsonObject.put(DESCRIPTION, ex.getMessage());
    }
    return jsonObject;
  }

  public JSONObject getTour(JSONObject reqJson) {
    JSONObject jsonObject = new JSONObject();
    try {
      int tourId = Integer.parseInt((String) reqJson.get("tourId"));
      Tour tour = repository.findTourById(tourId);
      jsonObject.put(ERROR, 0);
      jsonObject.put(TOUR, tour.toJSON());
    } catch (SQLException ex) {
      jsonObject.put(ERROR, 1);
      jsonObject.put(DESCRIPTION, ex.getMessage());
    }
    return jsonObject;
  }

  public JSONObject addClientTourEntry(JSONObject reqJson) {
    JSONObject jsonObject = new JSONObject();
    try {
      int clientId = Integer.parseInt((String) reqJson.get("clientId"));
      int tourId = Integer.parseInt((String) reqJson.get("tourId"));
      repository.saveClientTour(new ClientTour(clientId, tourId));
      jsonObject.put(ERROR, 0);
    } catch (SQLException ex) {
      jsonObject.put(ERROR, 1);
      jsonObject.put(DESCRIPTION, ex.getMessage());
    }
    return jsonObject;
  }

  public JSONObject getAllTours() {
    JSONObject jsonObject = new JSONObject();
    try {
      JSONArray toursArray = new JSONArray();
      repository.findAllTours().stream().map(Tour::toJSON).forEach(toursArray::put);
      jsonObject.put(ERROR, 0);
      jsonObject.put(TOURS_ARRAY, toursArray);
    } catch (SQLException ex) {
      jsonObject.put(ERROR, 1);
      jsonObject.put(DESCRIPTION, ex.getMessage());
    }
    return jsonObject;
  }

  public JSONObject getToursByClientId(int clientId) {
    JSONObject jsonObject = new JSONObject();
    try {
      JSONArray toursArray = new JSONArray();
      repository.findToursByClientId(clientId).stream().map(Tour::toJSON).forEach(toursArray::put);
      jsonObject.put(ERROR, 0);
      jsonObject.put(TOURS_ARRAY, toursArray);
    } catch (SQLException ex) {
      jsonObject.put(ERROR, 1);
      jsonObject.put(DESCRIPTION, ex.getMessage());
    }
    return jsonObject;
  }

  public JSONObject updateTour(JSONObject reqJson) {
    JSONObject jsonObject = new JSONObject();
    try {
      int tourId = Integer.parseInt((String) reqJson.get("id"));
      TourValidationService.getValidatedTour(parseTour(reqJson));
      Tour tour = repository.updateTour(tourId, parseTour(reqJson));
      JSONArray toursJsonArray = new JSONArray();
      toursJsonArray.put(tour.toJSON());
      jsonObject.put(ERROR, 0);
      jsonObject.put(TOURS_ARRAY, toursJsonArray);
    } catch (IllegalArgumentException | SQLException ex) {
      jsonObject.put(ERROR, 1);
      jsonObject.put(DESCRIPTION, ex.getMessage());
    }
    return jsonObject;
  }

  public JSONObject deleteTour(JSONObject reqJson) {
    JSONObject jsonObject = new JSONObject();
    try {
      int tourId = Integer.parseInt((String) reqJson.get("id"));
      repository.deleteTour(tourId);
      jsonObject.put(ERROR, 0);
    } catch (SQLException ex) {
      jsonObject.put(ERROR, 1);
      jsonObject.put(DESCRIPTION, ex.getMessage());
    }
    return jsonObject;
  }

  public JSONObject deleteClientFromTour(JSONObject reqJson) {
    JSONObject jsonObject = new JSONObject();
    try {
      int clientId = Integer.parseInt((String) reqJson.get("clientId"));
      int tourId = Integer.parseInt((String) reqJson.get("tourId"));
      repository.deleteClientFromTour(clientId, tourId);
      jsonObject.put(ERROR, 0);
    } catch (SQLException ex) {
      jsonObject.put(ERROR, 1);
      jsonObject.put(DESCRIPTION, ex.getMessage());
    }
    return jsonObject;
  }

  private Tour parseTour(JSONObject jsonObject) {
    String name = (String) jsonObject.get("name");
    String description = (String) jsonObject.get("description");
    Date startDate = Date.valueOf((String) jsonObject.get("startDate"));
    Date endDate = Date.valueOf((String) jsonObject.get("endDate"));
    int price = Integer.parseInt((String) jsonObject.get("price"));
    return new Tour(
        name,
        description,
        startDate,
        endDate,
        price
    );
  }
}
