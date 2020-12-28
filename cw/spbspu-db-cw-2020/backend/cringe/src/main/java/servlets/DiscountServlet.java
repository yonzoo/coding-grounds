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
    name = "DiscountServlet",
    urlPatterns = "/discount")
public class DiscountServlet extends HttpServlet {

  private final DiscountService discountService = DiscountService.getInstance(
      DiscountRepository.getInstance(
          DiscountDao.getInstance(),
          TourDao.getInstance()
      )
  );

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    blockIllegalAccess(request, response);
    JSONObject reqJson = JsonConverter.parse(request, response);
    JSONObject jsonObject = discountService.createDiscount(reqJson);
    sendResponse(response, jsonObject, CREATE_DISCOUNT_SUCCESS);
  }

  @Override
  protected void doDelete(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    blockIllegalAccess(request, response);
    JSONObject reqJson = JsonConverter.parse(request, response);
    JSONObject jsonObject = discountService.deleteDiscount(reqJson);
    sendResponse(response, jsonObject, DELETE_DISCOUNT_SUCCESS);
  }

  @Override
  protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
    blockIllegalAccess(request, response);
    JSONObject reqJson = JsonConverter.parse(request, response);
    JSONObject jsonObject = discountService.updateDiscount(reqJson);
    sendResponse(response, jsonObject, PUT_DISCOUNT_SUCCESS);
  }

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    JSONObject reqJson = JsonConverter.parse(request, response);
    JSONObject jsonObject = discountService.getDiscount(reqJson);
    sendResponse(response, jsonObject, GET_DISCOUNT_SUCCESS);
  }
}
