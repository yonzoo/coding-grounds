package servlets;

import data.dao.ClientDao;
import data.dao.CredentialsDao;
import data.dao.ManagerDao;
import data.repository.UserRepository;
import helper.JsonConverter;
import org.json.simple.JSONObject;
import service.AuthService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static servlets.helpers.ServletHelper.checkIfJsonParsed;
import static servlets.helpers.ServletHelper.sendResponse;
import static utils.Constants.REGISTER_SUCCESS;

@WebServlet(
    name = "RegisterServlet",
    urlPatterns = "/register")
public class RegisterServlet extends HttpServlet {

  private final AuthService authService = AuthService.getInstance(
      UserRepository.getInstance(
          CredentialsDao.getInstance(),
          ManagerDao.getInstance(),
          ClientDao.getInstance()
      )
  );

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    JSONObject reqJsonObject = JsonConverter.parse(request, response);
    checkIfJsonParsed(response, reqJsonObject);
    JSONObject jsonObject = authService.registerUser(reqJsonObject);
    sendResponse(response, jsonObject, REGISTER_SUCCESS);
  }
}
