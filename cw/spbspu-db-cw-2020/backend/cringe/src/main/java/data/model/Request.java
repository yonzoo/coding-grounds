package data.model;

import org.json.simple.JSONObject;

import java.sql.Date;

public class Request {
  private int id;
  private final int tourId;
  private final int clientId;
  private boolean isApproved;
  private Date date;

  public Request(int tourId, int clientId, boolean isApproved) {
    this.clientId = clientId;
    this.tourId = tourId;
    this.isApproved = isApproved;
  }

  public Request(int id, int clientId, int tourId, boolean isApproved, Date date) {
    this.id = id;
    this.tourId = tourId;
    this.clientId = clientId;
    this.isApproved = isApproved;
    this.date = date;
  }

  public int getId() {
    return id;
  }

  public int getTourId() {
    return tourId;
  }

  public int getClientId() {
    return clientId;
  }

  public boolean isApproved() {
    return isApproved;
  }

  public void setApproved(boolean approved) {
    isApproved = approved;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public JSONObject toJSON() {
    JSONObject jsonObject = new JSONObject();
    jsonObject.put("requestId", id);
    jsonObject.put("tourId", tourId);
    jsonObject.put("clientId", clientId);
    jsonObject.put("isApproved", isApproved);
    if (date != null)
      jsonObject.put("createdDate", date);
    return jsonObject;
  }
}
