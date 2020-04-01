package app;

import app.controller.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

/**
 * Class creates JavaFx application
 */
public class Main extends Application {

  /**
   * Method starts JavaFx application
   * @param stage is the main view of the app
   * @throws Exception if FXMLLoader fails to load resources
   */
  @Override
  public void start(Stage stage) throws Exception {
    URL resource = getClass().getResource("/fxml/Main.fxml");
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(resource);
    loader.setController(new Controller());
    Scene rootScene = new Scene(loader.load());
    stage.setScene(rootScene);
    stage.show();
  }

  /**
   * Method launches JavaFx application
   */
  public static void main(String[] args) {
    launch();
  }

}