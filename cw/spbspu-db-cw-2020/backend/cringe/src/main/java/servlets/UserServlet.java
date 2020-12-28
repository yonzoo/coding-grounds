package servlets;

import data.dao.ClientDao;
import data.dao.CredentialsDao;
import data.dao.ManagerDao;
import data.repository.UserRepository;
import helper.JsonConverter;
import org.json.simple.JSONObject;
import service.UserService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static servlets.helpers.ServletHelper.sendResponse;
import static utils.Constants.*;

@WebServlet(
    name = "UserServlet",
    urlPatterns = "/user")
public class UserServlet extends HttpServlet {

  private final UserService userService = UserService.getInstance(
      UserRepository.getInstance(
          CredentialsDao.getInstance(),
          ManagerDao.getInstance(),
          ClientDao.getInstance()
      )
  );

  @Override
  protected void doDelete(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    JSONObject jsonObject = userService.deleteUserData(request.getHeader(ID));
    sendResponse(response, jsonObject, DELETE_USER_SUCCESS);
  }

  @Override
  protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
    JSONObject reqJson = JsonConverter.parse(request, response);
    JSONObject jsonObject = userService.updateUserData(request.getHeader(TOKEN), reqJson);
    sendResponse(response, jsonObject, PUT_USER_SUCCESS);
  }

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    JSONObject jsonObject = userService.getUserData(request.getHeader(TOKEN));
    sendResponse(response, jsonObject, GET_USER_SUCCESS);
  }
}
