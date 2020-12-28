package service;

import data.model.Discount;
import data.model.Tour;
import data.repository.DiscountRepository;
import org.json.JSONArray;
import org.json.simple.JSONObject;

import java.sql.SQLException;
import java.util.List;

import static utils.Constants.*;

public class DiscountService {
  private final DiscountRepository repository;
  private static DiscountService instance;

  private DiscountService(DiscountRepository repository) {
    this.repository = repository;
  }

  public static synchronized DiscountService getInstance(DiscountRepository repository) {
    if (instance == null) {
      instance = new DiscountService(repository);
    }
    return instance;
  }

  public JSONObject createDiscount(JSONObject reqJson) {
    JSONObject jsonObject = new JSONObject();
    try {
      Discount discount = repository.saveDiscount(parseDiscount(reqJson));
      jsonObject.put(ERROR, 0);
      jsonObject.put(DISCOUNT, discount.toJSON());
    } catch (SQLException ex) {
      jsonObject.put(ERROR, 1);
      jsonObject.put(DESCRIPTION, ex.getMessage());
    }
    return jsonObject;
  }

  public JSONObject getDiscount(JSONObject reqJson) {
    JSONObject jsonObject = new JSONObject();
    try {
      int discountId = Integer.parseInt((String) reqJson.get("discountId"));
      Discount discount = repository.findDiscountById(discountId);
      jsonObject.put(ERROR, 0);
      jsonObject.put(DISCOUNT, discount.toJSON());
    } catch (NullPointerException | NumberFormatException | SQLException ex) {
      jsonObject.put(ERROR, 1);
      jsonObject.put(DESCRIPTION, ex.getMessage());
    }
    return jsonObject;
  }

  public JSONObject getAllDiscountsByTourId(JSONObject reqJson) {
    JSONObject jsonObject = new JSONObject();
    try {
      int tourId = Integer.parseInt((String) reqJson.get("tourId"));
      List<Discount> discounts = repository.findAllDiscountsByTourId(tourId);
      JSONArray discountsJsonArray = new JSONArray();
      discounts.stream().map(Discount::toJSON).forEach(discountsJsonArray::put);
      jsonObject.put(ERROR, 0);
      jsonObject.put(DISCOUNTS_ARRAY, discountsJsonArray);
    } catch (NumberFormatException | SQLException ex) {
      jsonObject.put(ERROR, 1);
      jsonObject.put(DESCRIPTION, ex.getMessage());
    }
    return jsonObject;
  }

  public JSONObject updateDiscount(JSONObject reqJson) {
    JSONObject jsonObject = new JSONObject();
    try {
      int discountId = Integer.parseInt((String) reqJson.get("id"));
      Discount discount = repository.updateDiscount(discountId, parseDiscount(reqJson));
      jsonObject.put(ERROR, 0);
      jsonObject.put(DISCOUNT, discount.toJSON());
    } catch (NumberFormatException | SQLException ex) {
      jsonObject.put(ERROR, 1);
      jsonObject.put(DESCRIPTION, ex.getMessage());
    }
    return jsonObject;
  }

  public JSONObject deleteDiscount(JSONObject reqJson) {
    JSONObject jsonObject = new JSONObject();
    try {
      int discountId = Integer.parseInt((String) reqJson.get("id"));
      repository.deleteDiscount(discountId);
      jsonObject.put(ERROR, 0);
    } catch (NullPointerException | NumberFormatException | SQLException ex) {
      jsonObject.put(ERROR, 1);
      jsonObject.put(DESCRIPTION, ex.getMessage());
    }
    return jsonObject;
  }

  public JSONObject deleteDiscountsByTourId(JSONObject reqJson) {
    JSONObject jsonObject = new JSONObject();
    try {
      int tourId = Integer.parseInt((String) reqJson.get("tourId"));
      repository.deleteAllDiscountsByTourId(tourId);
      jsonObject.put(ERROR, 0);
    } catch (NumberFormatException | SQLException ex) {
      jsonObject.put(ERROR, 1);
      jsonObject.put(DESCRIPTION, ex.getMessage());
    }
    return jsonObject;
  }

  private Discount parseDiscount(JSONObject jsonObject) {
    int tourId = Integer.parseInt((String) jsonObject.get("tourId"));
    int discountAmount = Integer.parseInt((String) jsonObject.get("discountAmount"));
    return new Discount(
        tourId,
        discountAmount
    );
  }
}
