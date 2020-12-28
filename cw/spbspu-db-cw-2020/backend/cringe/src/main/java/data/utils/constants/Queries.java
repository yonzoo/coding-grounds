package data.utils.constants;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Queries {
  public static final String CREATE_ACCOUNTS_TABLE_PATH = resolvePath("/create/CreateAccountsTable.sql");
  public static final String CREATE_MANAGERS_TABLE_PATH = resolvePath("/create/CreateManagersTable.sql");
  public static final String CREATE_CLIENTS_TABLE_PATH = resolvePath("/create/CreateClientsTable.sql");
  public static final String CREATE_CLIENTS_TOUR_GROUPS_TABLE_PATH = resolvePath("/create/CreateClientsTourGroupsTable.sql");
  public static final String CREATE_CLIENTS_TOURS_TABLE_PATH = resolvePath("/create/CreateClientsToursTable.sql");
  public static final String CREATE_DISCOUNTS_TABLE_PATH = resolvePath("/create/CreateDiscountsTable.sql");
  public static final String CREATE_REQUESTS_TABLE_PATH = resolvePath("/create/CreateRequestsTable.sql");
  public static final String CREATE_TOURS_TABLE_PATH = resolvePath("/create/CreateToursTable.sql");
  public static final String CREATE_TOUR_GROUPS_TABLE_PATH = resolvePath("/create/CreateTourGroupsTable.sql");

  public static final String GET_ACCOUNT_BY_ID_QUERY = "SELECT * FROM [dbo].[Accounts] WHERE [AccountId] = (?);";
  public static final String GET_ACCOUNT_BY_LOGIN_QUERY = "SELECT * FROM [dbo].[Accounts] WHERE [Login] = (?);";
  public static final String GET_ADMINISTRATOR_BY_ID_QUERY = "SELECT * FROM [dbo].[Administrators] WHERE [AdministratorId] = (?);";
  public static final String GET_MANAGER_BY_ID_QUERY = "SELECT * FROM [dbo].[Managers] WHERE [ManagerId] = (?);";
  public static final String GET_CLIENT_BY_ID_QUERY = "SELECT * FROM [dbo].[Clients] WHERE [ClientId] = (?);";
  public static final String GET_REQUEST_BY_ID_QUERY = "SELECT * FROM [dbo].[Requests] WHERE [RequestId] = (?);";
  public static final String GET_TOUR_BY_ID_QUERY = "SELECT * FROM [dbo].[Tours] WHERE [TourId] = (?);";
  public static final String GET_TOUR_GROUP_BY_ID_QUERY = "SELECT * FROM [dbo].[TourGroups] WHERE [TourGroupId] = (?);";
  public static final String GET_ALL_ADMINS_QUERY = "SELECT * FROM [dbo].[Administrators];";
  public static final String GET_ALL_MANAGERS_QUERY = "SELECT * FROM [dbo].[Managers];";
  public static final String GET_ALL_CLIENTS_QUERY = "SELECT * FROM [dbo].[Clients];";
  public static final String GET_ALL_CREDENTIALS_QUERY = "SELECT * FROM [dbo].[Accounts];";
  public static final String GET_ALL_TOURS_QUERY = "SELECT * FROM [dbo].[Tours];";
  public static final String GET_ALL_TOUR_GROUPS_QUERY = "SELECT * FROM [dbo].[TourGroups];";
  public static final String GET_ALL_DISCOUNTS_QUERY = "SELECT * FROM [dbo].[Discounts];";
  public static final String GET_ALL_REQUESTS_QUERY = "SELECT * FROM [dbo].[Requests];";
  public static final String GET_ALL_CLIENTS_TOURS_QUERY = "SELECT * FROM [dbo].[ClientsTours];";
  public static final String GET_MANAGER_BY_ACCOUNT_ID_QUERY = "SELECT * FROM [dbo].[Managers] WHERE [AccountId] = (?);";
  public static final String GET_ADMINISTRATOR_BY_ACCOUNT_ID_QUERY = "SELECT * FROM [dbo].[Administrators] WHERE [AccountId] = (?);";
  public static final String GET_CLIENT_BY_ACCOUNT_ID_QUERY = "SELECT * FROM [dbo].[Clients] WHERE [AccountId] = (?);";
  public static final String GET_DISCOUNT_BY_ID_QUERY = "SELECT * FROM [dbo].[Discounts] WHERE [DiscountId] = (?);";
  public static final String GET_REQUESTS_BY_CLIENT_ID_AND_TOUR_ID_QUERY =
      "SELECT * FROM [dbo].[Requests] WHERE [ClientId] = (?) AND [TourId] = (?);";
  public static final String GET_REQUESTS_BY_CLIENT_ID_QUERY = "SELECT * FROM [dbo].[Requests] WHERE [ClientId] = (?)";
  public static final String GET_TOURS_BY_CLIENT_ID_QUERY = "SELECT Tours.TourId, Name, Description, StartDate, EndDate, Price FROM Tours JOIN ClientsTours CT on Tours.TourId = CT.TourId\n" +
      "WHERE ClientId = (?)";
  public static final String GET_CLIENT_TOUR_BY_CLIENT_ID_AND_TOUR_ID_QUERY =
      "SELECT * FROM [dbo].[ClientsTours] WHERE [ClientId] = (?) AND [TourId] = (?);";
  public static final String GET_CLIENTS_TOURS_BY_CLIENT_ID_QUERY = "SELECT * FROM [dbo].[ClientsTours] WHERE [ClientId] = (?);";
  public static final String GET_CLIENTS_TOURS_BY_TOUR_ID_QUERY = "SELECT * FROM [dbo].[ClientsTours] WHERE [TourId] = (?);";
  public static final String GET_TOUR_GROUPS_BY_CLIENT_ID_QUERY = "SELECT TourGroups.TourGroupId AS TourGroupId, TourGroups.Name, TourGroups.Description, TourGroups.TourId, MaxAmount FROM TourGroups JOIN ClientsTourGroups ON TourGroups.TourGroupId = ClientsTourGroups.TourGroupId\n" +
      "WHERE ClientId = (?)";
  public static final String GET_CLIENT_TOUR_GROUP_BY_ID_QUERY = "SELECT * FROM [dbo].[ClientsTourGroups] WHERE [TourGroupId] = (?);";
  public static final String GET_CLIENT_TOUR_GROUP_BY_CLIENT_ID_AND_TOUR_GROUP_ID_QUERY =
      "SELECT * FROM [dbo].[ClientsTourGroups] WHERE [ClientId] = (?) AND [TourGroupId] = (?);";
  public static final String GET_CLIENT_TOUR_GROUPS_BY_CLIENT_ID_QUERY = "SELECT * FROM [dbo].[ClientsTourGroups] WHERE [ClientId] = (?);";
  public static final String GET_CLIENTS_TOUR_GROUPS_BY_TOUR_GROUP_ID_QUERY = "SELECT * FROM [dbo].[ClientsTourGroups] WHERE [TourGroupId] = (?);";
  public static final String GET_DISCOUNTS_BY_TOUR_ID_QUERY = "SELECT * FROM [dbo].[Discounts] WHERE [TourId] = (?)";

  public static final String INSERT_ACCOUNT_QUERY = "INSERT INTO [dbo].[Accounts] VALUES (?,?,?);";
  public static final String INSERT_ADMINISTRATOR_QUERY = "INSERT INTO [dbo].[Administrators] VALUES (?,?,?,?);";
  public static final String INSERT_CLIENT_QUERY = "INSERT INTO [dbo].[Clients] VALUES (?,?,?,?,?,?);";
  public static final String INSERT_CLIENT_TOUR_QUERY = "INSERT INTO [dbo].[ClientsTours] VALUES (?,?);";
  public static final String INSERT_CLIENT_TOUR_GROUP_QUERY = "INSERT INTO [dbo].[ClientsTourGroups] VALUES (?,?);";
  public static final String INSERT_DISCOUNT_QUERY = "INSERT INTO [dbo].[Discounts] VALUES (?,?);";
  public static final String INSERT_MANAGER_QUERY = "INSERT INTO [dbo].[Managers] VALUES (?,?,?,?);";
  public static final String INSERT_REQUEST_QUERY = "INSERT INTO [dbo].[Requests] VALUES (?,?,?,?);";
  public static final String INSERT_TOUR_QUERY = "INSERT INTO [dbo].[Tours] VALUES (?,?,?,?,?);";
  public static final String INSERT_TOUR_GROUP_QUERY = "INSERT INTO [dbo].[TourGroups] VALUES (?,?,?,?);";

  public static final String UPDATE_ACCOUNT_QUERY =
      "UPDATE [dbo].[Accounts] SET [Login] = (?), [Password] = (?) WHERE [AccountId] = (?);";
  public static final String UPDATE_ADMINISTRATOR_QUERY =
      "UPDATE [dbo].[Administrators] SET [Name] = (?), [PhoneNumber] = (?), [Email] = (?) WHERE [AdministratorId] = (?);";
  public static final String UPDATE_CLIENT_QUERY =
      "UPDATE [dbo].[Clients] SET [Name] = (?), [BirthDate] = (?), [PhoneNumber] = (?), [Email] = (?), [DocumentNumber] = (?) WHERE [ClientId] = (?);";
  public static final String UPDATE_CLIENT_TOUR_GROUP_QUERY =
      "UPDATE [dbo].[ClientsTourGroups] SET [TourGroupId] = (?) WHERE [ClientId] = (?);";
  public static final String UPDATE_DISCOUNT_QUERY =
      "UPDATE [dbo].[Discounts] SET [DiscountAmount] = (?), [TourId] = (?) WHERE [DiscountId] = (?);";
  public static final String UPDATE_MANAGER_QUERY =
      "UPDATE [dbo].[Managers] SET [Name] = (?), [PhoneNumber] = (?), [Email] = (?) WHERE [ManagerId] = (?);";
  public static final String UPDATE_REQUEST_QUERY =
      "UPDATE [dbo].[Requests] SET [isApproved] = (?) WHERE [RequestId] = (?);";
  public static final String UPDATE_TOUR_QUERY =
      "UPDATE [dbo].[Tours] SET [Name] = (?), [Description] = (?), [StartDate] = (?), [EndDate] = (?), [Price] = (?) WHERE [TourId] = (?);";
  public static final String UPDATE_TOUR_GROUP_QUERY =
      "UPDATE [dbo].[TourGroups] SET [TourId] = (?), [Name] = (?), [Description] = (?), [MaxAmount] = (?) WHERE [TourGroupId] = (?);";

  public static final String DELETE_ACCOUNT_QUERY = "DELETE FROM [dbo].[Accounts] WHERE [AccountId] = (?);";
  public static final String DELETE_CLIENT_QUERY = "DELETE FROM [dbo].[Clients] WHERE [ClientId] = (?);";
  public static final String DELETE_CLIENT_TOUR_QUERY = "DELETE FROM [dbo].[ClientsTours] WHERE [ClientId] = (?) AND [TourId] = (?);";
  public static final String DELETE_CLIENT_TOUR_GROUP_QUERY = "DELETE FROM [dbo].[ClientsTourGroups] WHERE [ClientId] = (?) AND [TourGroupId] = (?);";
  public static final String DELETE_DISCOUNT_QUERY = "DELETE FROM [dbo].[Discounts] WHERE [DiscountId] = (?);";
  public static final String DELETE_MANAGER_QUERY = "DELETE FROM [dbo].[Managers] WHERE [ManagerId] = (?);";
  public static final String DELETE_REQUEST_QUERY = "DELETE FROM [dbo].[Requests] WHERE [RequestId] = (?);";
  public static final String DELETE_TOUR_QUERY = "DELETE FROM [dbo].[Tours] WHERE [TourId] = (?);";
  public static final String DELETE_TOUR_GROUP_QUERY = "DELETE FROM [dbo].[TourGroups] WHERE [TourGroupId] = (?);";
  public static final String DELETE_DISCOUNTS_BY_TOUR_ID_QUERY = "DELETE FROM [dbo].[Discounts] WHERE [TourId] = (?)";

  public static final String CREATE_NO_TOUR_VIEW_QUERY =
      "CREATE VIEW NoTour AS SELECT *\n" +
          "FROM Clients\n" +
          "WHERE ClientId NOT IN (\n" +
          "    SELECT DISTINCT Clients.ClientId\n" +
          "    FROM Clients\n" +
          "             JOIN ClientsTours CT on Clients.ClientId = CT.ClientId\n" +
          ")";

  public static final String GET_FROM_NO_TOUR_QUERY = "SELECT * FROM NoTour";

  public static final String CREATE_MANY_TOURS_QUERY = "CREATE VIEW ManyTours AS\n" +
      "SELECT DISTINCT Clients.ClientId, Name, BirthDate, DocumentNumber, AccountId, PhoneNumber, Email\n" +
      "FROM Clients\n" +
      "LEFT JOIN ClientsTours CT on Clients.ClientId = CT.ClientId\n" +
      "GROUP BY Clients.ClientId, Name, BirthDate, DocumentNumber, AccountId, PhoneNumber, Email\n" +
      "HAVING COUNT(Clients.ClientId) > 1";

  public static final String GET_FROM_MANY_TOURS_QUERY = "SELECT * FROM ManyTours";

  public static final String CREATE_NEXT_TOUR_QUERY = "CREATE VIEW NextTour AS SELECT * FROM Tours " +
      "WHERE StartDate <= DATEADD(MONTH, 1, GETDATE()) AND StartDate >= GETDATE()";

  public static final String GET_FROM_NEXT_TOUR_QUERY = "SELECT * FROM NextTour";

  public static final String CREATE_CHEAP_TOUR_QUERY = "CREATE VIEW CheapTour\n" +
      "AS SELECT T.TourId, T.Name, T.Description, T.StartDate, T.EndDate,  Price - DiscountSum AS Price FROM Tours AS T\n" +
      "    LEFT JOIN (\n" +
      "        SELECT TourId, SUM(DiscountAmount) AS DiscountSum FROM Discounts\n" +
      "        GROUP BY TourId\n" +
      "    ) as D ON T.TourId = D.TourId\n" +
      "GROUP BY T.Price, T.Name, T.Description, T.StartDate, T.EndDate, D.DiscountSum, T.TourId\n" +
      "HAVING T.Price - D.DiscountSum = (SELECT MIN(A.Price - B.DiscountSum) FROM Tours AS A\n" +
      "        LEFT JOIN (\n" +
      "        SELECT TourId, SUM(DiscountAmount) AS DiscountSum FROM Discounts\n" +
      "        GROUP BY TourId\n" +
      "    ) as B ON A.TourId = B.TourId\n" +
      ")";

  public static final String GET_FROM_CHEAP_TOUR_QUERY = "SELECT * from CheapTour";

  public static final String CREATE_SALES_SUM_QUERY = "CREATE VIEW SalesSum AS\n" +
      "SELECT SUM(TD.FinalPrice) AS SalesSum\n" +
      "FROM ClientsTours\n" +
      "         INNER JOIN (SELECT T.StartDate, T.TourId, Price - DiscountSum AS FinalPrice\n" +
      "                     FROM Tours AS T\n" +
      "                              LEFT JOIN (\n" +
      "                         SELECT TourId, SUM(DiscountAmount) AS DiscountSum\n" +
      "                         FROM Discounts\n" +
      "                         GROUP BY TourId\n" +
      "                     ) as D ON T.TourId = D.TourId\n" +
      "                     GROUP BY T.Price, D.DiscountSum, T.TourId, T.StartDate) AS TD ON TD.TourId = ClientsTours.TourId";

  public static final String GET_FROM_SALES_SUM_QUERY = "SELECT * FROM SalesSum";

  public static final String CREATE_SUMMER_TOURS_QUERY = "CREATE VIEW SummerTours AS\n" +
      "SELECT *\n" +
      "FROM Tours\n" +
      "WHERE (SELECT MONTH(StartDate)) >= 6\n" +
      "  AND (SELECT MONTH(EndDate)) <= 8\n" +
      "  AND (SELECT YEAR(StartDate)) = (SELECT YEAR(EndDate))\n" +
      "  AND (SELECT MONTH(StartDate)) <= (SELECT MONTH(EndDate))";

  public static final String GET_SUMMER_TOURS_QUERY = "SELECT * FROM SummerTours";

  public static final String GET_YEAR_MOST_POPULAR_TOUR_QUERY = "SELECT * FROM Tours\n" +
      "WHERE Tours.TourId = (\n" +
      "    SELECT TOP 1 Tours.TourId\n" +
      "    FROM  ClientsTours\n" +
      "              INNER JOIN Tours\n" +
      "                         ON ClientsTours.TourId = Tours.TourId\n" +
      "    WHERE StartDate >= DATEADD(MONTH, -6, GETDATE()) AND StartDate <= DATEADD(MONTH, 6, GETDATE())\n" +
      "    GROUP BY Tours.TourId\n" +
      "    ORDER BY COUNT(Tours.TourId) DESC)";

  public static final String GET_TOUR_PARTICIPANTS_BY_DATE_QUERY = "SELECT DISTINCT Clients.ClientId, Clients.Name, BirthDate, DocumentNumber, AccountId, PhoneNumber, Email FROM Clients\n" +
      "LEFT JOIN ClientsTours CT on Clients.ClientId = CT.ClientId\n" +
      "LEFT JOIN Tours T on CT.TourId = T.TourId\n" +
      "WHERE T.StartDate = (?)";

  public static final String GET_TOUR_TOUR_GROUPS_QUERY = "SELECT *\n" +
      "FROM TourGroups\n" +
      "WHERE TourGroups.TourId = (?)";

  public static final String GET_TOURS_WITH_REQUESTS_QUERY = "SELECT Tours.TourId, Name, Description, StartDate, EndDate, Price\n" +
      "FROM Tours\n" +
      "         INNER JOIN Requests\n" +
      "                    ON Tours.TourId = Requests.TourId\n" +
      "GROUP BY Tours.TourId, Name, Description, StartDate, EndDate, Price\n" +
      "HAVING COUNT(isApproved) >= 1";

  public static final String GET_TOURS_WITHOUT_REQUESTS_QUERY = "SELECT Tours.TourId, Name, Description, StartDate, EndDate, Price FROM Tours\n" +
      "LEFT JOIN Requests AS R2 ON Tours.TourId = R2.TourId\n" +
      "GROUP BY Tours.TourId, Name, Description, StartDate, EndDate, Price\n" +
      "HAVING COUNT(R2.TourId) = 0";

  public static final String GET_VACANT_TOUR_GROUPS_QUERY = "SELECT *\n" +
      "FROM TourGroups\n" +
      "WHERE (SELECT COUNT(*) FROM ClientsTourGroups INNER JOIN TourGroups ON ClientsTourGroups.TourGroupId = TourGroups.TourGroupId) < MaxAmount";

  public static final String GET_TOUR_GROUPS_BY_TOUR_DATE = "SELECT *\n" +
      "FROM TourGroups\n" +
      "INNER JOIN Tours\n" +
      "ON TourGroups.TourId = Tours.TourId\n" +
      "WHERE StartDate = (?)";

  public static final String GET_TOUR_DISCOUNTS = "SELECT *\n" +
      "FROM Discounts\n" +
      "WHERE Discounts.TourId = (?)";

  public static final String GET_CLIENT_TOURS_BY_DOCUMENT_NUMBER = "SELECT * from Tours\n" +
      "INNER JOIN Clients\n" +
      "ON Clients.DocumentNumber = (?)";

  public static final String GET_MONTH_SALES = "SELECT Tours.TourId AS TourId, Tours.Name, Price - (SELECT SUM(DiscountAmount) FROM Discounts WHERE Discounts.TourId = Tours.TourId) AS SalesSum\n" +
      "FROM ClientsTours\n" +
      "         INNER JOIN Tours\n" +
      "                    ON ClientsTours.TourId = Tours.TourId\n" +
      "WHERE StartDate >= DATEADD(MONTH, -1, GETDATE())\n" +
      "  AND StartDate <= GETDATE()\n";

  public static final String GET_YEAR_SALES = "SELECT Tours.TourId, Tours.Name AS TourName, Price - (SELECT SUM(DiscountAmount) FROM Discounts WHERE Discounts.TourId = Tours.TourId) AS SalesSum\n" +
      "FROM ClientsTours\n" +
      "         INNER JOIN Tours\n" +
      "                    ON ClientsTours.TourId = Tours.TourId\n" +
      "WHERE StartDate >= DATEADD(YEAR, -1, GETDATE())\n" +
      "  AND StartDate <= GETDATE()\n";

  public static final List<String> createViewsQueries =
      Arrays.asList(
          CREATE_NO_TOUR_VIEW_QUERY,
          CREATE_MANY_TOURS_QUERY,
          CREATE_NEXT_TOUR_QUERY,
          CREATE_SUMMER_TOURS_QUERY,
          CREATE_CHEAP_TOUR_QUERY,
          CREATE_SALES_SUM_QUERY
      );

  private static String resolvePath(String filename) {
    File file = new File("src/main/resources/queries/tables" + filename);
    return file.getAbsolutePath();
  }
}
