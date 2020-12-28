package data.model;

import org.json.simple.JSONObject;

public class Sale {

  private final int tourId;
  private final String tourName;
  private final int saleSum;

  public Sale(int tourId, String tourName, int saleSum) {
    this.tourId = tourId;
    this.tourName = tourName;
    this.saleSum = saleSum;
  }

  public int getTourId() {
    return tourId;
  }

  public String getTourName() {
    return tourName;
  }

  public int getSaleSum() {
    return saleSum;
  }

  public JSONObject toJSON() {
    JSONObject jsonObject = new JSONObject();
    jsonObject.put("tourId", tourId);
    if (tourName != null)
      jsonObject.put("tourName", tourName);
    jsonObject.put("saleSum", saleSum);
    return jsonObject;
  }
}
