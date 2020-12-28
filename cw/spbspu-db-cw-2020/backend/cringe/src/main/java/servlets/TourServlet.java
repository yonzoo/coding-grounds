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
    name = "TourServlet",
    urlPatterns = "/tour")
public class TourServlet extends HttpServlet {

  private final TourService tourService = TourService.getInstance(
      TourRepository.getInstance(
          ClientDao.getInstance(),
          TourDao.getInstance(),
          ClientTourDao.getInstance(),
          DiscountDao.getInstance()
      )
  );

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    blockIllegalAccess(request, response);
    JSONObject reqJson = JsonConverter.parse(request, response);
    JSONObject jsonObject = tourService.createTour(reqJson);
    sendResponse(response, jsonObject, CREATE_TOUR_SUCCESS);
  }

  @Override
  protected void doDelete(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    blockIllegalAccess(request, response);
    JSONObject reqJson = JsonConverter.parse(request, response);
    JSONObject jsonObject = tourService.deleteTour(reqJson);
    sendResponse(response, jsonObject, DELETE_TOUR_SUCCESS);
  }

  @Override
  protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
    blockIllegalAccess(request, response);
    JSONObject reqJson = JsonConverter.parse(request, response);
    JSONObject jsonObject = tourService.updateTour(reqJson);
    sendResponse(response, jsonObject, PUT_TOUR_SUCCESS);
  }

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    JSONObject reqJson = JsonConverter.parse(request, response);
    JSONObject jsonObject = tourService.getTour(reqJson);
    sendResponse(response, jsonObject, GET_TOUR_SUCCESS);
  }
}
