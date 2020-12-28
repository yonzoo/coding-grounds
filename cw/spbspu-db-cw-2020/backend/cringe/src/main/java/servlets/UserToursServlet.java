package servlets;

import data.dao.*;
import data.model.Client;
import data.repository.RequestRepository;
import data.repository.TourRepository;
import data.repository.UserRepository;
import helper.JsonConverter;
import org.json.simple.JSONObject;
import service.RequestService;
import service.TourService;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import static servlets.helpers.ServletHelper.sendResponse;
import static utils.Constants.GET_REQUEST_SUCCESS;
import static utils.Constants.TOKEN;

@WebServlet(
    name = "UserToursServlet",
    urlPatterns = "/user/tours")
public class UserToursServlet extends HttpServlet {

  private final TourService tourService = TourService.getInstance(
      TourRepository.getInstance(
          ClientDao.getInstance(),
          TourDao.getInstance(),
          ClientTourDao.getInstance(),
          DiscountDao.getInstance()
      )
  );

  private final UserService userService = UserService.getInstance(
      UserRepository.getInstance(
          CredentialsDao.getInstance(),
          ManagerDao.getInstance(),
          ClientDao.getInstance()
      )
  );

  private final RequestService requestService = RequestService.getInstance(
      RequestRepository.getInstance(
          RequestDao.getInstance(),
          ClientTourDao.getInstance()
      )
  );

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    JSONObject reqJson = JsonConverter.parse(request, response);
    try {
      Client client = userService.getClientDataAsObject(request.getHeader(TOKEN));
      JSONObject clientRequestsJson = requestService.createRequest(
          Integer.parseInt((String) reqJson.get("tourId")),
          client.getId()
      );
      sendResponse(response, clientRequestsJson, GET_REQUEST_SUCCESS);
    } catch (SQLException ex) {
      ex.printStackTrace();
    }
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    try {
      Client client = userService.getClientDataAsObject(request.getHeader(TOKEN));
      JSONObject clientRequestsJson = tourService.getToursByClientId(client.getId());
      sendResponse(response, clientRequestsJson, GET_REQUEST_SUCCESS);
    } catch (SQLException ex) {
      ex.printStackTrace();
    }
  }
}
