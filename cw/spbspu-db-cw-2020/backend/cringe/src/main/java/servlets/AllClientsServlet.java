package servlets;

import data.dao.ClientDao;
import data.dao.CredentialsDao;
import data.dao.ManagerDao;
import data.repository.UserRepository;
import org.json.simple.JSONObject;
import service.UserService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static servlets.helpers.ServletHelper.sendResponse;
import static utils.Constants.GET_USER_SUCCESS;

@WebServlet(
    name = "AllClientsServlet",
    urlPatterns = "/all/clients")
public class AllClientsServlet extends HttpServlet {

  private final UserService userService = UserService.getInstance(
      UserRepository.getInstance(
          CredentialsDao.getInstance(),
          ManagerDao.getInstance(),
          ClientDao.getInstance()
      )
  );

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    JSONObject jsonObject = userService.getAllClients();
    sendResponse(response, jsonObject, GET_USER_SUCCESS);
  }
}
