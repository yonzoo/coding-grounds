package service;

import data.model.*;
import data.repository.ComplexRepository;
import org.json.JSONArray;
import org.json.simple.JSONObject;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import static utils.Constants.*;

public class ComplexService {
  private final ComplexRepository repository;
  private static ComplexService instance;

  private ComplexService(ComplexRepository repository) {
    this.repository = repository;
  }

  public static synchronized ComplexService getInstance(ComplexRepository repository) {
    if (instance == null) {
      instance = new ComplexService(repository);
    }
    return instance;
  }

  public JSONObject getFromNoTour() {
    JSONObject jsonObject = new JSONObject();
    try {
      List<Client> clients = repository.getFromNoTour();
      JSONArray clientsJsonArray = new JSONArray();
      clients.stream().map(Client::toJSON).forEach(clientsJsonArray::put);
      jsonObject.put(ERROR, 0);
      jsonObject.put(CLIENTS_ARRAY, clientsJsonArray);
    } catch (SQLException ex) {
      jsonObject.put(ERROR, 1);
      jsonObject.put(DESCRIPTION, ex.getMessage());
    }
    return jsonObject;
  }

  public JSONObject getFromManyTours() {
    JSONObject jsonObject = new JSONObject();
    try {
      List<Client> clients = repository.getFromManyTours();
      JSONArray clientsJsonArray = new JSONArray();
      clients.stream().map(Client::toJSON).forEach(clientsJsonArray::put);
      jsonObject.put(ERROR, 0);
      jsonObject.put(CLIENTS_ARRAY, clientsJsonArray);
    } catch (SQLException ex) {
      jsonObject.put(ERROR, 1);
      jsonObject.put(DESCRIPTION, ex.getMessage());
    }
    return jsonObject;
  }

  public JSONObject getFromNextTour() {
    JSONObject jsonObject = new JSONObject();
    try {
      List<Tour> tours = repository.getFromNextTour();
      JSONArray toursJsonArray = new JSONArray();
      tours.stream().map(Tour::toJSON).forEach(toursJsonArray::put);
      jsonObject.put(ERROR, 0);
      jsonObject.put(TOURS_ARRAY, toursJsonArray);
    } catch (SQLException ex) {
      jsonObject.put(ERROR, 1);
      jsonObject.put(DESCRIPTION, ex.getMessage());
    }
    return jsonObject;
  }

  public JSONObject getCheapTour() {
    JSONObject jsonObject = new JSONObject();
    try {
      List<Tour> tours = repository.getCheapTour();
      JSONArray toursJsonArray = new JSONArray();
      tours.stream().map(Tour::toJSON).forEach(toursJsonArray::put);
      jsonObject.put(ERROR, 0);
      jsonObject.put(TOURS_ARRAY, toursJsonArray);
    } catch (SQLException ex) {
      ex.printStackTrace();
      jsonObject.put(ERROR, 1);
      jsonObject.put(DESCRIPTION, ex.getMessage());
    }
    return jsonObject;
  }

  public JSONObject getSalesSum() {
    JSONObject jsonObject = new JSONObject();
    try {
      int salesSum = repository.getSalesSum();
      jsonObject.put(ERROR, 0);
      jsonObject.put(SALES_SUM, salesSum);
    } catch (SQLException ex) {
      jsonObject.put(ERROR, 1);
      jsonObject.put(DESCRIPTION, ex.getMessage());
    }
    return jsonObject;
  }

  public JSONObject getSummerTours() {
    JSONObject jsonObject = new JSONObject();
    try {
      List<Tour> tours = repository.getSummerTours();
      JSONArray toursJsonArray = new JSONArray();
      tours.stream().map(Tour::toJSON).forEach(toursJsonArray::put);
      jsonObject.put(ERROR, 0);
      jsonObject.put(TOURS_ARRAY, toursJsonArray);
    } catch (SQLException ex) {
      jsonObject.put(ERROR, 1);
      jsonObject.put(DESCRIPTION, ex.getMessage());
    }
    return jsonObject;
  }

  public JSONObject getYearMostPopularTour() {
    JSONObject jsonObject = new JSONObject();
    try {
      List<Tour> tours = repository.getYearMostPopularTour();
      JSONArray toursJsonArray = new JSONArray();
      tours.stream().map(Tour::toJSON).forEach(toursJsonArray::put);
      jsonObject.put(ERROR, 0);
      jsonObject.put(TOURS_ARRAY, toursJsonArray);
    } catch (SQLException ex) {
      jsonObject.put(ERROR, 1);
      jsonObject.put(DESCRIPTION, ex.getMessage());
    }
    return jsonObject;
  }

  public JSONObject getTourParticipantsByTourDate(JSONObject reqJson) {
    JSONObject jsonObject = new JSONObject();
    try {
      Date tourDate = new Date(Date.parse((String) jsonObject.get("tourDate")));
      List<Client> clients = repository.getTourParticipantsByTourDate(tourDate);
      JSONArray clientsJsonArray = new JSONArray();
      clients.stream().map(Client::toJSON).forEach(clientsJsonArray::put);
      jsonObject.put(ERROR, 0);
      jsonObject.put(CLIENTS_ARRAY, clientsJsonArray);
    } catch (SQLException ex) {
      jsonObject.put(ERROR, 1);
      jsonObject.put(DESCRIPTION, ex.getMessage());
    }
    return jsonObject;
  }

  public JSONObject getTourTourGroups(JSONObject reqJson) {
    JSONObject jsonObject = new JSONObject();
    try {
      int tourId = Integer.parseInt((String) reqJson.get("tourId"));
      List<TourGroup> tourGroups = repository.getTourTourGroups(tourId);
      JSONArray tourGroupsJsonArray = new JSONArray();
      tourGroups.stream().map(TourGroup::toJSON).forEach(tourGroupsJsonArray::put);
      jsonObject.put(ERROR, 0);
      jsonObject.put(TOUR_GROUPS_ARRAY, tourGroupsJsonArray);
    } catch (NullPointerException | IllegalArgumentException | SQLException ex) {
      jsonObject.put(ERROR, 1);
      jsonObject.put(DESCRIPTION, ex.getMessage());
    }
    return jsonObject;
  }

  public JSONObject getToursWithApprovedRequests() {
    JSONObject jsonObject = new JSONObject();
    try {
      List<Tour> tours = repository.getToursWithApprovedRequests();
      JSONArray toursJsonArray = new JSONArray();
      tours.stream().map(Tour::toJSON).forEach(toursJsonArray::put);
      jsonObject.put(ERROR, 0);
      jsonObject.put(TOURS_ARRAY, toursJsonArray);
    } catch (SQLException ex) {
      jsonObject.put(ERROR, 1);
      jsonObject.put(DESCRIPTION, ex.getMessage());
    }
    return jsonObject;
  }

  public JSONObject getToursWithoutAnyRequests() {
    JSONObject jsonObject = new JSONObject();
    try {
      List<Tour> tours = repository.getToursWithoutAnyRequests();
      JSONArray toursJsonArray = new JSONArray();
      tours.stream().map(Tour::toJSON).forEach(toursJsonArray::put);
      jsonObject.put(ERROR, 0);
      jsonObject.put(TOURS_ARRAY, toursJsonArray);
    } catch (SQLException ex) {
      ex.printStackTrace();
      jsonObject.put(ERROR, 1);
      jsonObject.put(DESCRIPTION, ex.getMessage());
    }
    return jsonObject;
  }

  public JSONObject getVacantTourGroupsQuery() {
    JSONObject jsonObject = new JSONObject();
    try {
      List<TourGroup> tourGroups = repository.getVacantTourGroupsQuery();
      JSONArray tourGroupsJsonArray = new JSONArray();
      tourGroups.stream().map(TourGroup::toJSON).forEach(tourGroupsJsonArray::put);
      jsonObject.put(ERROR, 0);
      jsonObject.put(TOUR_GROUPS_ARRAY, tourGroupsJsonArray);
    } catch (SQLException ex) {
      jsonObject.put(ERROR, 1);
      jsonObject.put(DESCRIPTION, ex.getMessage());
    }
    return jsonObject;
  }

  public JSONObject getTourGroupsByTourDate(JSONObject reqJson) {
    JSONObject jsonObject = new JSONObject();
    try {
      Date tourDate = Date.valueOf((String) reqJson.get("startDate"));
      List<TourGroup> tourGroups = repository.getTourGroupsByTourDate(tourDate);
      JSONArray tourGroupsJsonArray = new JSONArray();
      tourGroups.stream().map(TourGroup::toJSON).forEach(tourGroupsJsonArray::put);
      jsonObject.put(ERROR, 0);
      jsonObject.put(TOUR_GROUPS_ARRAY, tourGroupsJsonArray);
    } catch (SQLException ex) {
      jsonObject.put(ERROR, 1);
      jsonObject.put(DESCRIPTION, ex.getMessage());
    }
    return jsonObject;
  }

  public JSONObject getTourDiscounts(JSONObject reqJson) {
    JSONObject jsonObject = new JSONObject();
    try {
      int tourId = Integer.parseInt((String) reqJson.get("tourId"));
      List<Discount> discounts = repository.getTourDiscounts(tourId);
      JSONArray discountsJsonArray = new JSONArray();
      discounts.stream().map(Discount::toJSON).forEach(discountsJsonArray::put);
      jsonObject.put(ERROR, 0);
      jsonObject.put(DISCOUNTS_ARRAY, discountsJsonArray);
    } catch (NullPointerException | IllegalArgumentException | SQLException ex) {
      jsonObject.put(ERROR, 1);
      jsonObject.put(DESCRIPTION, ex.getMessage());
    }
    return jsonObject;
  }

  public JSONObject getClientToursByDocumentNumber(JSONObject reqJson) {
    JSONObject jsonObject = new JSONObject();
    try {
      int documentNumber = Integer.parseInt((String) reqJson.get("documentNumber"));
      List<Tour> tours = repository.getClientToursByDocumentNumber(documentNumber);
      JSONArray toursJsonArray = new JSONArray();
      tours.stream().map(Tour::toJSON).forEach(toursJsonArray::put);
      jsonObject.put(ERROR, 0);
      jsonObject.put(TOURS_ARRAY, toursJsonArray);
    } catch (NullPointerException | IllegalArgumentException | SQLException ex) {
      jsonObject.put(ERROR, 1);
      jsonObject.put(DESCRIPTION, ex.getMessage());
    }
    return jsonObject;
  }

  public JSONObject getMonthSales() {
    JSONObject jsonObject = new JSONObject();
    try {
      List<Sale> sales = repository.getMonthSales();
      JSONArray salesJsonArray = new JSONArray();
      sales.stream().map(Sale::toJSON).forEach(salesJsonArray::put);
      jsonObject.put(ERROR, 0);
      jsonObject.put(TOURS_ARRAY, salesJsonArray);
    } catch (SQLException ex) {
      jsonObject.put(ERROR, 1);
      jsonObject.put(DESCRIPTION, ex.getMessage());
    }
    return jsonObject;
  }

  public JSONObject getYearSales() {
    JSONObject jsonObject = new JSONObject();
    try {
      List<Sale> sales = repository.getYearSales();
      JSONArray salesJsonArray = new JSONArray();
      sales.stream().map(Sale::toJSON).forEach(salesJsonArray::put);
      jsonObject.put(ERROR, 0);
      jsonObject.put(SALES_ARRAY, salesJsonArray);
    } catch (SQLException ex) {
      jsonObject.put(ERROR, 1);
      jsonObject.put(DESCRIPTION, ex.getMessage());
    }
    return jsonObject;
  }

}
