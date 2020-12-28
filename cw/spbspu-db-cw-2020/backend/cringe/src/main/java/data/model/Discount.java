package data.model;

import org.json.simple.JSONObject;

public class Discount {
  private int id;
  private final int tourId;
  private int discountAmount;

  public Discount(int tourId, int discountAmount) {
    this.tourId = tourId;
    this.discountAmount = discountAmount;
  }

  public Discount(int id, int tourId, int discountAmount) {
    this.id = id;
    this.tourId = tourId;
    this.discountAmount = discountAmount;
  }

  public int getId() {
    return id;
  }

  public int getTourId() {
    return tourId;
  }

  public int getDiscountAmount() {
    return discountAmount;
  }

  public void setDiscountAmount(int discountAmount) {
    this.discountAmount = discountAmount;
  }

  public JSONObject toJSON() {
    JSONObject jsonObject = new JSONObject();
    jsonObject.put("discountId", id);
    jsonObject.put("tourId", tourId);
    jsonObject.put("discountAmount", discountAmount);
    return jsonObject;
  }
}
