package data.utils.constants;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TableNames {
  public static final String ACCOUNTS = "Accounts";
  public static final String ADMINS = "Administrators";
  public static final String MANAGERS = "Managers";
  public static final String CLIENTS = "Clients";
  public static final String CLIENTS_TOUR_GROUPS = "ClientsTourGroups";
  public static final String CLIENTS_TOURS = "ClientsTours";
  public static final String DISCOUNTS = "Discounts";
  public static final String REQUESTS = "Requests";
  public static final String TOURS = "Tours";
  public static final String TOUR_GROUPS = "TourGroups";

  public static List<String> getTableNamesSet() {
    return Arrays.asList(
        CLIENTS,
        ACCOUNTS,
        MANAGERS,
        DISCOUNTS,
        REQUESTS,
        TOURS,
        TOUR_GROUPS,
        CLIENTS_TOURS,
        CLIENTS_TOUR_GROUPS
    );
  }
}
