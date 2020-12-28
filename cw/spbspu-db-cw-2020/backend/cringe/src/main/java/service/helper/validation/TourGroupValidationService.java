package service.helper.validation;

import data.model.TourGroup;

public class TourGroupValidationService {

  public static void getValidatedTourGroup(TourGroup tourGroup) {
    if (tourGroup.getName() == null || tourGroup.getName().equals("")) {
      throw new IllegalArgumentException("Tour group name must be provided");
    }
    if (tourGroup.getDescription() == null || tourGroup.getDescription().equals("")) {
      throw new IllegalArgumentException("Tour group description must be provided");
    }
    if (tourGroup.getMaxAmount() <= 0 || tourGroup.getMaxAmount() > 100) {
      throw new IllegalArgumentException("Max amount of people in tour group must be more than 0 and less or equal to 100");
    }
  }
}
