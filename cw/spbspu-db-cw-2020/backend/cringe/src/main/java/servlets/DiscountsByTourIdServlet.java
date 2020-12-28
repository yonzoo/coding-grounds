package servlets;

import data.dao.DiscountDao;
import data.dao.TourDao;
import data.repository.DiscountRepository;
import helper.JsonConverter;
import org.json.simple.JSONObject;
import service.DiscountService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static servlets.helpers.ServletHelper.blockIllegalAccess;
import static servlets.helpers.ServletHelper.sendResponse;
import static utils.Constants.*;

@WebServlet(
    name = "DiscountsByTourIdServlet",
    urlPatterns = "/discounts/by/tour/id")
public class DiscountsByTourIdServlet extends HttpServlet {
  private final DiscountService discountService = DiscountService.getInstance(
      DiscountRepository.getInstance(
          DiscountDao.getInstance(),
          TourDao.getInstance()
      )
  );

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    JSONObject reqJson = JsonConverter.parse(request, response);
    JSONObject jsonObject = discountService.getAllDiscountsByTourId(reqJson);
    sendResponse(response, jsonObject, GET_DISCOUNTS_BY_TOUR_ID_SUCCESS);
  }

  @Override
  protected void doDelete(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    blockIllegalAccess(request, response);
    JSONObject reqJson = JsonConverter.parse(request, response);
    JSONObject jsonObject = discountService.deleteDiscountsByTourId(reqJson);
    sendResponse(response, jsonObject, DELETE_DISCOUNTS_BY_TOUR_ID_SUCCESS);
  }
}
