package servlets;

import data.dao.ClientDao;
import data.dao.ClientTourDao;
import data.dao.DiscountDao;
import data.dao.TourDao;
import data.repository.TourRepository;
import helper.JsonConverter;
import org.json.simple.JSONObject;
import service.TourService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static servlets.helpers.ServletHelper.blockIllegalAccess;
import static servlets.helpers.ServletHelper.sendResponse;
import static utils.Constants.*;

@WebServlet(
    name = "ClientTourServlet",
    urlPatterns = "/client/tour")
public class ClientTourServlet extends HttpServlet {

  private final TourService tourService = TourService.getInstance(
      TourRepository.getInstance(
          ClientDao.getInstance(),
          TourDao.getInstance(),
          ClientTourDao.getInstance(),
          DiscountDao.getInstance()
      )
  );

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    blockIllegalAccess(request, response);
    JSONObject reqJson = JsonConverter.parse(request, response);
    JSONObject jsonObject = tourService.addClientTourEntry(reqJson);
    sendResponse(response, jsonObject, ADD_CLIENT_TO_TOUR_SUCCESS);
  }

  @Override
  protected void doDelete(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    blockIllegalAccess(request, response);
    JSONObject reqJson = JsonConverter.parse(request, response);
    JSONObject jsonObject = tourService.deleteClientFromTour(reqJson);
    sendResponse(response, jsonObject, DELETE_CLIENT_FROM_TOUR_SUCCESS);
  }
}
