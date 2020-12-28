package service.helper.validation;

import data.model.Tour;

import java.time.LocalDate;

public class TourValidationService {

  public static void getValidatedTour(Tour tour) {
    if (tour.getName() == null) {
      throw new IllegalArgumentException("Tour name must be provided");
    }
    if (tour.getDescription() == null) {
      throw new IllegalArgumentException("Tour description must be provided");
    }
    if (tour.getStartDate() == null) {
      throw new IllegalArgumentException("Tour start date must be provided");
    }
    if (tour.getEndDate() == null) {
      throw new IllegalArgumentException("Tour end date must be provided");
    }
    if (tour.getPrice() == 0) {
      throw new IllegalArgumentException("Tour price must be more than 0");
    }
    if (!tour.getName().matches("[a-zA-Z0-9.\\-_ ]{3,50}")) {
      throw new IllegalArgumentException("Tour name is invalid");
    }
    if (!tour.getDescription().matches("[a-zA-Z0-9.\\-_ ]{1,1000}")) {
      throw new IllegalArgumentException("Description is invalid");
    }
    LocalDate startDate = tour.getStartDate().toLocalDate();
    LocalDate endDate = tour.getEndDate().toLocalDate();
    if (!startDate.isBefore(endDate)) {
      throw new IllegalArgumentException("Start date must be before the end date");
    }
    int price = tour.getPrice();
    if (price <= 0) {
      throw new IllegalArgumentException("Price must be more than 0");
    }
  }
}
