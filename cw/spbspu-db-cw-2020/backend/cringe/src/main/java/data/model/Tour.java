package data.model;

import org.json.simple.JSONObject;

import java.sql.Date;

public class Tour {
  private int id;
  private String name;
  private String description;
  private Date startDate;
  private Date endDate;
  private int price;

  public Tour(
      String name,
      String description,
      Date startDate,
      Date endDate,
      int price
  ) {
    this.name = name;
    this.description = description;
    this.startDate = startDate;
    this.endDate = endDate;
    this.price = price;
  }

  public Tour(
      int id,
      String name,
      String description,
      Date startDate,
      Date endDate,
      int price
  ) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.startDate = startDate;
    this.endDate = endDate;
    this.price = price;
  }

  public int getId() {
    return id;
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

  public Date getStartDate() {
    return startDate;
  }

  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }

  public Date getEndDate() {
    return endDate;
  }

  public void setEndDate(Date endDate) {
    this.endDate = endDate;
  }

  public int getPrice() {
    return price;
  }

  public void setPrice(int price) {
    this.price = price;
  }

  public JSONObject toJSON() {
    JSONObject jsonObject = new JSONObject();
    jsonObject.put("tourId", id);
    if (name != null)
      jsonObject.put("name", name);
    if (description != null)
      jsonObject.put("description", description);
    if (startDate != null)
      jsonObject.put("startDate", startDate);
    if (endDate != null)
      jsonObject.put("endDate", endDate);
    jsonObject.put("price", price);
    return jsonObject;
  }
}
