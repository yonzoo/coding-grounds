package servlets.helpers;

import data.dao.ClientDao;
import data.dao.CredentialsDao;
import data.dao.ManagerDao;
import data.repository.UserRepository;
import org.json.simple.JSONObject;
import service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static utils.Constants.*;

public class ServletHelper {
  private static final UserService userService = UserService.getInstance(
      UserRepository.getInstance(
          CredentialsDao.getInstance(),
          ManagerDao.getInstance(),
          ClientDao.getInstance()
      )
  );

  public static void sendResponse(
      HttpServletResponse response,
      JSONObject jsonObject,
      String message
  ) throws IOException {
    JSONObject resJson = new JSONObject();
    resJson.put(DATA, jsonObject);
    resJson.put(MESSAGE, message);
    writeObjectToResponse(resJson, response);
  }

  public static void checkIfJsonParsed(HttpServletResponse response, JSONObject reqJsonObject) throws IOException {
    if (reqJsonObject == null) {
      JSONObject resJSON = new JSONObject();
      resJSON.put(ERROR, 1);
      resJSON.put(MESSAGE, JSON_ERROR);
      response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
      sendResponse(response, resJSON, FAIL);
    }
  }

  public static void blockIllegalAccess(HttpServletRequest request, HttpServletResponse response) throws IOException {
    boolean ifAdminOrManager = userService.checkIfAdminOrManager(request.getHeader(TOKEN));
    if (!ifAdminOrManager) {
      JSONObject jsonObject = new JSONObject();
      jsonObject.put(ERROR, 1);
      jsonObject.put(DESCRIPTION, "You do not have a permission to perform this operation");
      sendResponse(response, jsonObject, NO_RIGHTS);
    }
  }

  private static void writeObjectToResponse(
      JSONObject jsonObject,
      HttpServletResponse response
  ) throws IOException {
    try (PrintWriter out = response.getWriter()) {
      out.println(jsonObject.toString());
      out.flush();
    }
  }
}
