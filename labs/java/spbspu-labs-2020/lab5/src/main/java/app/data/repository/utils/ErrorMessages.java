package app.data.repository.utils;

/**
 * Class holds error messages which are used by Controller
 */
public class ErrorMessages {

  private ErrorMessages() {
    //restrict instantiation
  }

  public static final String FILL_FORMAT_ERROR =
      "Amount has invalid format";
  public static final String FILL_EMPTY =
      "Amount must not be empty";
  public static final String FILL_NEGATIVE =
      "Amount must not be less than zero";

  public static final String PRICE_FORMAT_ERROR =
      "Price has invalid format";
  public static final String NAME_OR_PRICE_EMPTY =
      "Name and price must not be empty";

  public static final String PRICE_NEGATIVE =
      "Price must not be less than zero";
  public static final String NAME_EMPTY =
      "Name must not be empty";

  public static final String FILTER_EMPTY =
      "Minimum and maximum values must not be empty";
  public static final String FILTER_NEGATIVE =
      "Minimum and maximum values must not be less than zero";
  public static final String FILTER_LOGIC_ERROR =
      "Maximum value must not be less than minimum value";

  public static final String FILTER_FORMAT_ERROR =
      "Minimum or maximum value has invalid format";
}
