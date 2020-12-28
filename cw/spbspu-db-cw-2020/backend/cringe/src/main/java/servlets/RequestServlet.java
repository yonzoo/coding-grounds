package servlets;

import data.dao.ClientTourDao;
import data.dao.RequestDao;
import data.repository.RequestRepository;
import helper.JsonConverter;
import org.json.simple.JSONObject;
import service.RequestService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static servlets.helpers.ServletHelper.sendResponse;
import static utils.Constants.*;

@WebServlet(
    name = "RequestServlet",
    urlPatterns = "/request")
public class RequestServlet extends HttpServlet {

  private final RequestService requestService = RequestService.getInstance(
      RequestRepository.getInstance(
          RequestDao.getInstance(),
          ClientTourDao.getInstance()
      )
  );

  @Override
  protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
    JSONObject reqJson = JsonConverter.parse(request, response);
    JSONObject jsonObject = requestService.updateRequest(reqJson);
    sendResponse(response, jsonObject, PUT_REQUEST_SUCCESS);
  }


  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    JSONObject reqJson = JsonConverter.parse(request, response);
    JSONObject jsonObject = requestService.createRequest(reqJson);
    sendResponse(response, jsonObject, CREATE_REQUEST_SUCCESS);
  }

  @Override
  protected void doDelete(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    JSONObject reqJson = JsonConverter.parse(request, response);
    JSONObject jsonObject = requestService.deleteRequest(reqJson);
    sendResponse(response, jsonObject, DELETE_REQUEST_SUCCESS);
  }

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    JSONObject reqJson = JsonConverter.parse(request, response);
    JSONObject jsonObject = requestService.getRequest(reqJson);
    sendResponse(response, jsonObject, GET_REQUEST_SUCCESS);
  }
}
