package servlets;

import data.dao.ClientTourDao;
import data.dao.RequestDao;
import data.repository.RequestRepository;
import org.json.simple.JSONObject;
import service.RequestService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static servlets.helpers.ServletHelper.sendResponse;
import static utils.Constants.GET_REQUEST_SUCCESS;

@WebServlet(
    name = "AllRequestsServlet",
    urlPatterns = "/all/requests")
public class AllRequestsServlet extends HttpServlet {

  private final RequestService requestService = RequestService.getInstance(
      RequestRepository.getInstance(
          RequestDao.getInstance(),
          ClientTourDao.getInstance()
      )
  );

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    JSONObject jsonObject = requestService.getAllRequests();
    sendResponse(response, jsonObject, GET_REQUEST_SUCCESS);
  }
}
