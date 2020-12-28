package helper;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;

import static utils.Constants.JSON_ERROR;
import static utils.Constants.MESSAGE;

public class JsonConverter {
  static JSONParser parser = new JSONParser();

  public static JSONObject parse(
      HttpServletRequest request,
      HttpServletResponse response
  ) throws IOException {
    JSONObject reqJson;
    try {
      Reader reqReader = request.getReader();
      reqJson = (JSONObject) parser.parse(reqReader);
    } catch (ParseException | ClassCastException ex) {
//      handleJSONError(response);
      return null;
    }
    return reqJson;
  }

  public static void handleJSONError(HttpServletResponse response) throws IOException {
    JSONObject resJSON = new JSONObject();
    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
    try (PrintWriter out = response.getWriter()) {
      resJSON.put(MESSAGE, JSON_ERROR);
      out.println(resJSON.toString());
      out.flush();
    }
  }
}
