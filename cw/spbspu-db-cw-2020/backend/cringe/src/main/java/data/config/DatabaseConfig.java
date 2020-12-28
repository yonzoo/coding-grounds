package data.config;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import org.apache.ibatis.jdbc.ScriptRunner;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.sql.*;
import java.util.List;
import java.util.Properties;

import static data.utils.constants.Queries.*;
import static data.utils.constants.TableNames.*;

public class DatabaseConfig {
  private static DatabaseConfig instance;
  private Connection connection = null;

  private DatabaseConfig() {
    makeJDBCConnection();
  }

  public static synchronized DatabaseConfig getInstance() {
    if (instance == null) {
      instance = new DatabaseConfig();
    }
    return instance;
  }

  public Connection getConnection() {
    return connection;
  }

  private void makeJDBCConnection() {
    try {
      Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
    } catch (ClassNotFoundException e) {
      System.out.println("Sorry, couldn't find JDBC driver. Make sure you have added JDBC Maven Dependency Correctly");
      e.printStackTrace();
      return;
    }
    try {
      Properties p = new Properties();
      String USER = "SA";
      p.setProperty("user", USER);
      String PASSWORD = "21RoOm7_";
      p.setProperty("password", PASSWORD);
      p.setProperty("useUnicode", "true");
      p.setProperty("characterEncoding", "cp1251");
      String URL = "jdbc:sqlserver://localhost:1433;database=tour_db";
      connection = DriverManager.getConnection(URL, p);
      if (connection != null) {
        System.out.println("MySQL Connection Successful!");
        initTables();
        initViews();
      } else {
        System.out.println("Failed to make connection!");
      }
    } catch (SQLException | FileNotFoundException e) {
      System.out.println("MySQL Connection Failed!");
      e.printStackTrace();
    }
  }

  private void initTables() throws SQLException, FileNotFoundException {
    DatabaseMetaData dbm = connection.getMetaData();
    List<String> tableNames = getTableNamesSet();
    ScriptRunner scriptRunner = new ScriptRunner(connection);
    for (String tableName : tableNames) {
      if (!getTable(dbm, tableName).next()) {
        Reader reader = new BufferedReader(
            new FileReader(getCreateQueryPath(tableName))
        );
        scriptRunner.runScript(reader);
      }
    }
  }

  private void initViews() throws SQLException {
    for (String query : createViewsQueries) {
      PreparedStatement preparedStatement = connection.prepareStatement(query);
      try {
        preparedStatement.executeUpdate();
      } catch (SQLException ex) {
        //do nothing
      }
    }
  }

  private String getCreateQueryPath(String tableName) {
    String queryScriptPath;
    switch (tableName) {
      case ACCOUNTS: {
        queryScriptPath = CREATE_ACCOUNTS_TABLE_PATH;
        break;
      }
      case MANAGERS: {
        queryScriptPath = CREATE_MANAGERS_TABLE_PATH;
        break;
      }
      case CLIENTS: {
        queryScriptPath = CREATE_CLIENTS_TABLE_PATH;
        break;
      }
      case DISCOUNTS: {
        queryScriptPath = CREATE_DISCOUNTS_TABLE_PATH;
        break;
      }
      case REQUESTS: {
        queryScriptPath = CREATE_REQUESTS_TABLE_PATH;
        break;
      }
      case TOURS: {
        queryScriptPath = CREATE_TOURS_TABLE_PATH;
        break;
      }
      case TOUR_GROUPS: {
        queryScriptPath = CREATE_TOUR_GROUPS_TABLE_PATH;
        break;
      }
      case CLIENTS_TOUR_GROUPS: {
        queryScriptPath = CREATE_CLIENTS_TOUR_GROUPS_TABLE_PATH;
        break;
      }
      case CLIENTS_TOURS: {
        queryScriptPath = CREATE_CLIENTS_TOURS_TABLE_PATH;
        break;
      }
      default:
        throw new IllegalStateException("Unexpected value: " + tableName);
    }
    return queryScriptPath;
  }

  private ResultSet getTable(DatabaseMetaData dbm, String tableName)
      throws SQLException {
    return dbm.getTables(
        null,
        null,
        tableName,
        null
    );
  }
}
