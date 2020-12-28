package data.model;

import org.json.simple.JSONObject;

public class TourGroup {
  private int id;
  private final int tourId;
  private String name;
  private String description;
  private int maxAmount;

  public TourGroup(int tourId, String name, String description, int maxAmount) {
    this.tourId = tourId;
    this.name = name;
    this.description = description;
    this.maxAmount = maxAmount;
  }

  public TourGroup(int id, int tourId, String name, String description, int maxAmount) {
    this.id = id;
    this.tourId = tourId;
    this.name = name;
    this.description = description;
    this.maxAmount = maxAmount;
  }

  public int getId() {
    return id;
  }

  public int getTourId() {
    return tourId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public int getMaxAmount() {
    return maxAmount;
  }

  public void setMaxAmount(int maxAmount) {
    this.maxAmount = maxAmount;
  }

  public JSONObject toJSON() {
    JSONObject jsonObject = new JSONObject();
    jsonObject.put("tourGroupId", id);
    if (name != null)
      jsonObject.put("name", name);
    if (description != null)
      jsonObject.put("description", description);
    if (tourId != 0)
      jsonObject.put("tourId", tourId);
    if (maxAmount != 0)
      jsonObject.put("maxAmount", maxAmount);
    return jsonObject;
  }
}
