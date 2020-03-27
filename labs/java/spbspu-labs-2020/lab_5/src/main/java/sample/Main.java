package sample;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import sample.data.ProductDao;
import sample.data.ProductRepository;
import sample.models.Product;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main extends Application {
  private Stage stage;
  private static final String SUCCESS_STATUS = "SUCCESS";
  private static final String DEFAULT_STATUS = "NO STATUS";
  private static final String DEFAULT_ENTRIES = "NO ENTRIES";
  private static final String FAIL_STATUS = "FAIL";

  public static void main(String[] args) {
    launch(args);
  }

  public VBox getFillContainer() {
    VBox fillContainer = new VBox();

    Button fillBtn = new Button();
    fillBtn.setMinWidth(100);
    fillBtn.setText("Fill");

    TextField fillText = new TextField();
    fillText.setPrefColumnCount(11);
    fillText.setPromptText("How many...");
    fillText.setMaxWidth(100);
    Text fillDesc = new Text();
    fillDesc.setText("Fill the database with n random products");

    fillBtn.setOnAction(event -> {
      try {
        ProductRepository.executeFill(Integer.parseInt(fillText.getText()));
        updateUI(SUCCESS_STATUS, ProductDao.getProductsFromDB());
      } catch (Exception e) {
        e.printStackTrace();
        updateUI(FAIL_STATUS, null);
      }
    });

    fillContainer.setSpacing(10);
    fillContainer.getChildren().add(fillDesc);
    fillContainer.getChildren().add(fillText);
    fillContainer.getChildren().add(fillBtn);

    return fillContainer;
  }

  public VBox getAddContainer() {
    VBox addContainer = new VBox();

    TextField addText1 = new TextField();
    addText1.setPrefColumnCount(11);
    addText1.setMinWidth(300);
    addText1.setPromptText("Please write the name of the product...");

    TextField addText2 = new TextField();
    addText2.setPrefColumnCount(11);
    addText2.setMaxWidth(100);
    addText2.setPromptText("Price...");

    Button addBtn = new Button();
    addBtn.setMinWidth(100);
    addBtn.setText("Add");

    addBtn.setOnAction(event -> {
      try {
        ProductRepository.executeAdd(addText1.getText(), Double.parseDouble(addText2.getText()));
        updateUI(SUCCESS_STATUS, ProductDao.getProductsFromDB());
      } catch (Exception e) {
        e.printStackTrace();
        updateUI(FAIL_STATUS, null);
      }
    });

    Text addDesc = new Text();
    addDesc.setText("Add a product");

    addContainer.setSpacing(10);
    addContainer.getChildren().add(addDesc);
    addContainer.getChildren().add(addText1);
    addContainer.getChildren().add(addText2);
    addContainer.getChildren().add(addBtn);

    return addContainer;
  }

  public VBox getDeleteContainer() {
    VBox deleteContainer = new VBox();

    Button deleteBtn = new Button();
    deleteBtn.setMinWidth(100);
    deleteBtn.setText("Delete");

    TextField deleteText = new TextField();
    deleteText.setPrefColumnCount(11);
    deleteText.setMinWidth(300);
    deleteText.setPromptText("Please write the name of the product...");

    Text deleteDesc = new Text();
    deleteDesc.setText("Delete a product");

    deleteBtn.setOnAction(event -> {
      try {
        ProductRepository.executeDelete(deleteText.getText());
        updateUI(SUCCESS_STATUS, ProductDao.getProductsFromDB());
      } catch (SQLException e) {
        e.printStackTrace();
        updateUI(FAIL_STATUS, null);
      }
    });

    deleteContainer.setSpacing(10);
    deleteContainer.getChildren().add(deleteDesc);
    deleteContainer.getChildren().add(deleteText);
    deleteContainer.getChildren().add(deleteBtn);

    return deleteContainer;
  }

  public VBox getShowAllContainer() {
    VBox showAllContainer = new VBox();

    Button showAllBtn = new Button();
    showAllBtn.setMinWidth(100);
    showAllBtn.setText("Show");
    showAllBtn.setOnAction(event -> {
      try {
        updateUI(SUCCESS_STATUS, ProductDao.getProductsFromDB());
      } catch (Exception e) {
        e.printStackTrace();
        updateUI(FAIL_STATUS, null);
      }
    });

    Text showAllDesc = new Text();
    showAllDesc.setText("Show all products");

    showAllContainer.setSpacing(10);
    showAllContainer.getChildren().add(showAllDesc);
    showAllContainer.getChildren().add(showAllBtn);

    return showAllContainer;
  }

  public VBox getGetPriceContainer() {
    VBox getPriceContainer = new VBox();

    Button getPriceBtn = new Button();
    getPriceBtn.setMinWidth(100);
    getPriceBtn.setText("Get price");

    TextField getPriceText = new TextField();
    getPriceText.setPrefColumnCount(11);
    getPriceText.setMinWidth(300);
    getPriceText.setPromptText("Please write the name of the product...");

    Text getPriceDesc = new Text();
    getPriceDesc.setText("Learn the price of the product");

    getPriceBtn.setOnAction(event -> {
      try {
        updateUI(SUCCESS_STATUS, ProductDao.getPrice(getPriceText.getText()));
      } catch (Exception e) {
        e.printStackTrace();
        updateUI(FAIL_STATUS, null);
      }
    });

    getPriceContainer.setSpacing(10);
    getPriceContainer.getChildren().add(getPriceDesc);
    getPriceContainer.getChildren().add(getPriceText);
    getPriceContainer.getChildren().add(getPriceBtn);

    return getPriceContainer;
  }

  public VBox getChangePriceContainer() {
    VBox getChangePriceContainer = new VBox();

    Button getChangePriceBtn = new Button();
    getChangePriceBtn.setMinWidth(100);
    getChangePriceBtn.setText("Change price");

    TextField getChangePriceText1 = new TextField();
    getChangePriceText1.setPrefColumnCount(11);
    getChangePriceText1.setMinWidth(300);
    getChangePriceText1.setPromptText("Please write the name of the product...");

    TextField getChangePriceText2 = new TextField();
    getChangePriceText2.setPrefColumnCount(11);
    getChangePriceText2.setMaxWidth(100);
    getChangePriceText2.setPromptText("New price...");

    Text getChangePriceDesc = new Text();
    getChangePriceDesc.setText("Change the price of the product");

    getChangePriceBtn.setOnAction(event -> {
      try {
        ProductRepository.executeChangePrice(getChangePriceText1.getText(), Double.parseDouble(getChangePriceText2.getText()));
        updateUI(SUCCESS_STATUS, ProductDao.getPrice(getChangePriceText1.getText()));
      } catch (Exception e) {
        e.printStackTrace();
        updateUI(FAIL_STATUS, null);
      }
    });

    getChangePriceContainer.setSpacing(10);
    getChangePriceContainer.getChildren().add(getChangePriceDesc);
    getChangePriceContainer.getChildren().add(getChangePriceText1);
    getChangePriceContainer.getChildren().add(getChangePriceText2);
    getChangePriceContainer.getChildren().add(getChangePriceBtn);

    return getChangePriceContainer;
  }

  public VBox getFilterByPriceContainer() {
    VBox getFilterByPriceContainer = new VBox();

    Button getFilterByPriceBtn = new Button();
    getFilterByPriceBtn.setMinWidth(100);
    getFilterByPriceBtn.setText("Filter by price");

    TextField getFilterByPriceText1 = new TextField();
    getFilterByPriceText1.setPrefColumnCount(1);
    getFilterByPriceText1.setMaxWidth(60);
    getFilterByPriceText1.setPromptText("Min");

    TextField getFilterByPriceText2 = new TextField();
    getFilterByPriceText2.setPrefColumnCount(1);
    getFilterByPriceText2.setMaxWidth(60);
    getFilterByPriceText2.setPromptText("Max");

    Text getFilterByPriceDesc = new Text();
    getFilterByPriceDesc.setText("Filter products by price");

    getFilterByPriceBtn.setOnAction(event -> {
      try {
        updateUI(SUCCESS_STATUS, ProductDao.getProductsByPrice(
            Double.parseDouble(getFilterByPriceText1.getText()),
            Double.parseDouble(getFilterByPriceText2.getText())
        ));
      } catch (Exception e) {
        e.printStackTrace();
        updateUI(FAIL_STATUS, null);
      }
    });

    getFilterByPriceContainer.setSpacing(10);
    getFilterByPriceContainer.getChildren().add(getFilterByPriceDesc);
    getFilterByPriceContainer.getChildren().add(getFilterByPriceText1);
    getFilterByPriceContainer.getChildren().add(getFilterByPriceText2);
    getFilterByPriceContainer.getChildren().add(getFilterByPriceBtn);

    return getFilterByPriceContainer;
  }

  public VBox getStatusContainer(String statusText) {
    VBox statusContainer = new VBox();
    Text statusDesc = new Text();
    statusDesc.setText("Status of the operation");
    statusDesc.setStyle("-fx-font: 18 arial;");
    statusContainer.getChildren().add(statusDesc);
    statusContainer.setPadding(new Insets(30, 0, 0, 70));
    statusContainer.setAlignment(Pos.TOP_CENTER);

    Text statusValue = new Text();
    statusValue.setText(statusText);
    statusValue.setStyle("-fx-font: 24 arial;");

    if (statusText.equals(SUCCESS_STATUS))
      statusValue.setFill(Color.GREEN);
    else if (statusText.equals(FAIL_STATUS))
      statusValue.setFill(Color.RED);
    else statusValue.setFill(Color.GRAY);

    Separator separator1 = new Separator();
    separator1.setOrientation(Orientation.HORIZONTAL);

    Separator separator2 = new Separator();
    separator2.setOrientation(Orientation.HORIZONTAL);

    statusContainer.getChildren().add(separator1);
    statusContainer.getChildren().add(statusValue);
    statusContainer.getChildren().add(separator2);

    statusContainer.setSpacing(10);
    return statusContainer;
  }

  public VBox getStatusDescContainer() {
    VBox statusDescContainer = new VBox();
    Text statusDesc = new Text();
    statusDesc.setStyle("-fx-font: 18 arial;");
    statusDesc.setText("Database entries");
    statusDescContainer.setPadding(new Insets(30, 0, 0, 100));
    statusDescContainer.getChildren().add(statusDesc);

    return statusDescContainer;
  }

  private List<Text> getProductWrappers(List<Product> products) {
    List<Text> wrappers = new ArrayList<>();
    products.forEach(product -> {
      Text productDescription = new Text();
      productDescription.setText("Id: " + product.id + "\n" + "Prodid: "
          + product.prodid + "\n" + "Title: " + product.title
          + "\n" + "Cost: " + product.cost);
      wrappers.add(productDescription);
    });
    return wrappers;
  }

  public ScrollPane getScrollPane(List<Product> products) {
    ScrollPane scrollPane = new ScrollPane();
    VBox entriesContainer = new VBox();

    Text scrollPaneDefaultValue = new Text();
    scrollPaneDefaultValue.setText(DEFAULT_ENTRIES);
    scrollPaneDefaultValue.setStyle("-fx-font: 24 arial;");
    scrollPaneDefaultValue.setFill(Color.DARKGRAY);

    if (products == null) {
      entriesContainer.getChildren().add(scrollPaneDefaultValue);
      entriesContainer.setAlignment(Pos.TOP_CENTER);
    } else {
      if (products.size() == 0) {
        entriesContainer.getChildren().add(scrollPaneDefaultValue);
        entriesContainer.setAlignment(Pos.TOP_CENTER);
        scrollPaneDefaultValue.setText(DEFAULT_ENTRIES);
      } else {
        entriesContainer.getChildren().addAll(getProductWrappers(products));
        entriesContainer.setAlignment(Pos.TOP_LEFT);
      }
    }
    entriesContainer.setPadding(new Insets(50, 50, 50, 50));

    String style = "-fx-background-color: rgba(209, 209, 209, 0.5); -fx-outer-border: rgba(209, 209, 209, 0.5); -fx-inner-border: rgba(209, 209, 209, 0.5);";
    scrollPane.setStyle(style);
    entriesContainer.setStyle(style);
    scrollPane.setContent(entriesContainer);
    entriesContainer.setSpacing(10);

    scrollPane.setFitToHeight(true);
    scrollPane.setFitToWidth(true);

    return scrollPane;
  }

  private VBox getLeftPanel() {
    VBox fillContainer = getFillContainer();
    VBox addContainer = getAddContainer();
    VBox deleteContainer = getDeleteContainer();
    VBox showAllContainer = getShowAllContainer();
    VBox getPriceContainer = getGetPriceContainer();
    VBox changePriceContainer = getChangePriceContainer();
    VBox filterByPriceContainer = getFilterByPriceContainer();

    VBox leftPanel = new VBox();
    leftPanel.getChildren().add(new Group(fillContainer));
    leftPanel.getChildren().add(new Group(addContainer));
    leftPanel.getChildren().add(new Group(deleteContainer));
    leftPanel.getChildren().add(new Group(showAllContainer));
    leftPanel.getChildren().add(new Group(getPriceContainer));
    leftPanel.getChildren().add(new Group(changePriceContainer));
    leftPanel.getChildren().add(new Group(filterByPriceContainer));
    leftPanel.setSpacing(20);

    return leftPanel;
  }

  private VBox getRightPanel(String status, List<Product> list) {
    VBox statusContainer = getStatusContainer(status);
    VBox statusDescContainer = getStatusDescContainer();
    ScrollPane scrollPane = getScrollPane(list);

    VBox rightPanel = new VBox();
    rightPanel.setPrefSize(800, 300);
    String style = "-fx-background-color: rgba(209, 209, 209, 0.5);";
    rightPanel.setStyle(style);
    rightPanel.getChildren().add(new Group(statusContainer));
    rightPanel.getChildren().add(new Group(statusDescContainer));
    rightPanel.getChildren().add(scrollPane);
    rightPanel.setSpacing(10);

    return rightPanel;
  }

  private void updateUI(String status, List<Product> products) {
    VBox leftPanel = getLeftPanel();
    VBox rightPanel = getRightPanel(status, products);

    HBox root = new HBox();

    HBox.setMargin(leftPanel, new Insets(30, 0, 0, 30));
    HBox.setMargin(rightPanel, new Insets(0, 0, 0, 80));
    root.getChildren().add(leftPanel);
    root.getChildren().add(rightPanel);
    root.setAlignment(Pos.CENTER_LEFT);

    Scene scene = new Scene(root, 800, 900);
    stage.setScene(scene);
    stage.show();
  }

  @Override
  public void start(Stage primaryStage) {
    stage = primaryStage;
    stage.setTitle("Lab 5");
    updateUI(DEFAULT_STATUS, null);
  }
}
