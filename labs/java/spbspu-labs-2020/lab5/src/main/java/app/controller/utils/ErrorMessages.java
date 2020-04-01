package app.controller.utils;

/**
 * Class holds error messages which are used by Controller
 */
public class ErrorMessages {

  private ErrorMessages() {
    //restrict instantiation
  }

  public static final String FILL_FORMAT_ERROR =
      "FILL OPERATION FAILED BECAUSE INVALID FORMAT OF AMOUNT WAS PROVIDED";
  public static final String ADD_FORMAT_ERROR =
      "ADD OPERATION FAILED BECAUSE INVALID NAME OR PRICE WAS PROVIDED";
  public static final String DELETE_FORMAT_ERROR =
      "DELETE OPERATION FAILED BECAUSE INVALID NAME WAS PROVIDED";
  public static final String GET_PRICE_FORMAT_ERROR =
      "GET PRICE OPERATION FAILED BECAUSE INVALID NAME WAS PROVIDED";
  public static final String CHANGE_PRICE_FORMAT_ERROR =
      "CHANGE PRICE OPERATION FAILED BECAUSE INVALID NAME OR PRICE WAS PROVIDED";
  public static final String FILTER_FORMAT_ERROR =
      "FILTER OPERATION FAILED BECAUSE OF INVALID MIN OR MAX RANGE WAS PROVIDED";
  public static final String SHOW_ALL_ERROR =
      "SHOW ALL OPERATION FAILED BECAUSE OF SQL INTERNAL ERROR";

}
