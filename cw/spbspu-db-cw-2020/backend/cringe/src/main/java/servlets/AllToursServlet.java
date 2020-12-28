package servlets;

import data.dao.ClientDao;
import data.dao.ClientTourDao;
import data.dao.DiscountDao;
import data.dao.TourDao;
import data.repository.TourRepository;
import org.json.simple.JSONObject;
import service.TourService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static servlets.helpers.ServletHelper.sendResponse;
import static utils.Constants.GET_TOUR_SUCCESS;

@WebServlet(
    name = "AllToursServlet",
    urlPatterns = "/all/tours")
public class AllToursServlet extends HttpServlet {

  private final TourService tourService = TourService.getInstance(
      TourRepository.getInstance(
          ClientDao.getInstance(),
          TourDao.getInstance(),
          ClientTourDao.getInstance(),
          DiscountDao.getInstance()
      )
  );

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    JSONObject jsonObject = tourService.getAllTours();
    sendResponse(response, jsonObject, GET_TOUR_SUCCESS);
  }
}
