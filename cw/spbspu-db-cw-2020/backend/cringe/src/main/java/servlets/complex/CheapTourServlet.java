package servlets.complex;

import data.dao.ClientDao;
import data.dao.ComplexDao;
import data.dao.CredentialsDao;
import data.dao.ManagerDao;
import data.repository.ComplexRepository;
import data.repository.UserRepository;
import org.json.simple.JSONObject;
import service.ComplexService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static servlets.helpers.ServletHelper.sendResponse;
import static utils.Constants.GET_DATA_SUCCESS;

@WebServlet(
    name = "CheapTourServlet",
    urlPatterns = "/cheap/tour")
public class CheapTourServlet extends HttpServlet {

  private final ComplexService complexService = ComplexService.getInstance(
      ComplexRepository.getInstance(
          ComplexDao.getInstance(),
          UserRepository.getInstance(
              CredentialsDao.getInstance(),
              ManagerDao.getInstance(),
              ClientDao.getInstance()
          )
      )
  );

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    JSONObject jsonObject = complexService.getCheapTour();
    sendResponse(response, jsonObject, GET_DATA_SUCCESS);
  }
}
