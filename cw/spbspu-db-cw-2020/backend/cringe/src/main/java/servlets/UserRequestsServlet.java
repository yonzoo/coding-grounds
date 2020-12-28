package servlets;

import data.dao.*;
import data.model.Client;
import data.repository.RequestRepository;
import data.repository.UserRepository;
import org.json.simple.JSONObject;
import service.RequestService;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import static servlets.helpers.ServletHelper.sendResponse;
import static utils.Constants.*;

@WebServlet(
    name = "UserRequestsServlet",
    urlPatterns = "/user/requests")
public class UserRequestsServlet extends HttpServlet {

  private final RequestService requestService = RequestService.getInstance(
      RequestRepository.getInstance(
          RequestDao.getInstance(),
          ClientTourDao.getInstance()
      )
  );

  private final UserService userService = UserService.getInstance(
      UserRepository.getInstance(
          CredentialsDao.getInstance(),
          ManagerDao.getInstance(),
          ClientDao.getInstance()
      )
  );

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    try {
      Client client = userService.getClientDataAsObject(request.getHeader(TOKEN));
      JSONObject clientRequestsJson = requestService.getUserRequests(client.getId());
      sendResponse(response, clientRequestsJson, GET_REQUEST_SUCCESS);
    } catch (SQLException ex) {
      ex.printStackTrace();
    }
  }
}
