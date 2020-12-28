package servlets;

import data.dao.ClientDao;
import data.dao.ClientTourGroupDao;
import data.dao.TourDao;
import data.dao.TourGroupDao;
import data.model.ClientTourGroup;
import data.repository.TourGroupRepository;
import helper.JsonConverter;
import org.json.simple.JSONObject;
import service.TourGroupService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static servlets.helpers.ServletHelper.blockIllegalAccess;
import static servlets.helpers.ServletHelper.sendResponse;
import static utils.Constants.*;

@WebServlet(
    name = "TourGroupServlet",
    urlPatterns = "/tour/group")
public class TourGroupServlet extends HttpServlet {

  private final TourGroupService tourGroupService = TourGroupService.getInstance(
      TourGroupRepository.getInstance(
          TourGroupDao.getInstance(),
          ClientTourGroupDao.getInstance(),
          ClientDao.getInstance(),
          TourDao.getInstance()
      )
  );

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    blockIllegalAccess(request, response);
    JSONObject reqJson = JsonConverter.parse(request, response);
    JSONObject jsonObject = tourGroupService.createTourGroup(reqJson);
    sendResponse(response, jsonObject, CREATE_TOUR_GROUP_SUCCESS);
  }

  @Override
  protected void doDelete(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    blockIllegalAccess(request, response);
    JSONObject reqJson = JsonConverter.parse(request, response);
    JSONObject jsonObject = tourGroupService.deleteTourGroup(reqJson);
    sendResponse(response, jsonObject, DELETE_TOUR_GROUP_SUCCESS);
  }

  @Override
  protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
    blockIllegalAccess(request, response);
    JSONObject reqJson = JsonConverter.parse(request, response);
    JSONObject jsonObject = tourGroupService.updateTourGroup(reqJson);
    sendResponse(response, jsonObject, PUT_TOUR_GROUP_SUCCESS);
  }

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    JSONObject reqJson = JsonConverter.parse(request, response);
    JSONObject jsonObject = tourGroupService.getTourGroup(reqJson);
    sendResponse(response, jsonObject, GET_TOUR_GROUP_SUCCESS);
  }
}
