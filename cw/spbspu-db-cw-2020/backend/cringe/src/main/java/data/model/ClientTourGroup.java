package data.model;

public class ClientTourGroup {
  private int clientId;
  private int tourId;

  public ClientTourGroup(int clientId, int tourId) {
    this.clientId = clientId;
    this.tourId = tourId;
  }

  public int getClientId() {
    return clientId;
  }

  public void setClientId(int clientId) {
    this.clientId = clientId;
  }

  public int getTourGroupId() {
    return tourId;
  }

  public void setTourGroupId(int tourId) {
    this.tourId = tourId;
  }
}
