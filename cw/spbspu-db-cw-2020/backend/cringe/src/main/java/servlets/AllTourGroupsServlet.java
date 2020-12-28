package servlets;

import data.dao.ClientDao;
import data.dao.ClientTourGroupDao;
import data.dao.TourDao;
import data.dao.TourGroupDao;
import data.repository.TourGroupRepository;
import org.json.simple.JSONObject;
import service.TourGroupService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static servlets.helpers.ServletHelper.sendResponse;
import static utils.Constants.GET_TOUR_GROUP_SUCCESS;

@WebServlet(
    name = "AllTourGroupsServlet",
    urlPatterns = "/all/tour/groups")
public class AllTourGroupsServlet extends HttpServlet {

  private final TourGroupService tourGroupService = TourGroupService.getInstance(
      TourGroupRepository.getInstance(
          TourGroupDao.getInstance(),
          ClientTourGroupDao.getInstance(),
          ClientDao.getInstance(),
          TourDao.getInstance()
      )
  );

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    JSONObject jsonObject = tourGroupService.getAllTourGroups();
    sendResponse(response, jsonObject, GET_TOUR_GROUP_SUCCESS);
  }
}
