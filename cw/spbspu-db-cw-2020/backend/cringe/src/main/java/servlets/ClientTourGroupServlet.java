package servlets;

import data.dao.ClientDao;
import data.dao.ClientTourGroupDao;
import data.dao.TourDao;
import data.dao.TourGroupDao;
import data.repository.TourGroupRepository;
import helper.JsonConverter;
import org.json.simple.JSONObject;
import service.TourGroupService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static servlets.helpers.ServletHelper.blockIllegalAccess;
import static servlets.helpers.ServletHelper.sendResponse;
import static utils.Constants.*;

@WebServlet(
    name = "ClientTourGroupServlet",
    urlPatterns = "/client/tour/group")
public class ClientTourGroupServlet extends HttpServlet {

  private final TourGroupService tourGroupService = TourGroupService.getInstance(
      TourGroupRepository.getInstance(
          TourGroupDao.getInstance(),
          ClientTourGroupDao.getInstance(),
          ClientDao.getInstance(),
          TourDao.getInstance()
      )
  );

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    blockIllegalAccess(request, response);
    JSONObject reqJsonObject = JsonConverter.parse(request, response);
    if (reqJsonObject == null)
      return;
    JSONObject jsonObject = tourGroupService.addClientToTourGroup(reqJsonObject);
    sendResponse(response, jsonObject, ADD_CLIENT_TO_TOUR_GROUP_SUCCESS);
  }

  @Override
  protected void doDelete(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    blockIllegalAccess(request, response);
    JSONObject reqJson = JsonConverter.parse(request, response);
    JSONObject jsonObject = tourGroupService.deleteClientFromTourGroup(reqJson);
    sendResponse(response, jsonObject, DELETE_CLIENT_FROM_TOUR_GROUP_SUCCESS);
  }
}
