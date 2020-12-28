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
import static utils.Constants.GET_CLIENT_TOUR_GROUPS_SUCCESS;

@WebServlet(
    name = "ClientTourGroupsServlet",
    urlPatterns = "/client/tour/groups")
public class ClientTourGroupsServlet extends HttpServlet {

  private final TourGroupService tourGroupService = TourGroupService.getInstance(
      TourGroupRepository.getInstance(
          TourGroupDao.getInstance(),
          ClientTourGroupDao.getInstance(),
          ClientDao.getInstance(),
          TourDao.getInstance()
      )
  );

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    blockIllegalAccess(request, response);
    JSONObject reqJsonObject = JsonConverter.parse(request, response);
    if (reqJsonObject == null)
      return;
    JSONObject jsonObject = tourGroupService.getClientTourGroups(reqJsonObject);
    sendResponse(response, jsonObject, GET_CLIENT_TOUR_GROUPS_SUCCESS);
  }
}
