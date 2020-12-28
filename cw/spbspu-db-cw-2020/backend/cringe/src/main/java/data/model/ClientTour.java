package data.model;

public class ClientTour {
  private int clientId;
  private int tourId;

  public ClientTour(int clientId, int tourId) {
    this.clientId = clientId;
    this.tourId = tourId;
  }

  public int getClientId() {
    return clientId;
  }

  public void setClientId(int clientId) {
    this.clientId = clientId;
  }

  public int getTourId() {
    return tourId;
  }

  public void setTourId(int tourId) {
    this.tourId = tourId;
  }
}
