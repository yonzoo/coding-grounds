package app.controller;

import app.data.local.models.Product;
import app.data.repository.ProductRepository;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.util.List;

/**
 * Class controls Main.fxml view
 */
public class Controller {
  private ProductRepository repository;

  @FXML
  private Button fillBtn;
  @FXML
  private TextField fillText;

  @FXML
  private Button addBtn;
  @FXML
  private TextField addNameText;
  @FXML
  private TextField addPriceText;

  @FXML
  private Button deleteBtn;
  @FXML
  private TextField deleteNameText;

  @FXML
  private Button getPriceBtn;
  @FXML
  private TextField getPriceNameText;

  @FXML
  private Button changePriceBtn;
  @FXML
  private TextField changePriceNameText;
  @FXML
  private TextField changePricePriceText;

  @FXML
  private Button filterBtn;
  @FXML
  private TextField filterMinText;
  @FXML
  private TextField filterMaxText;

  @FXML
  private Button showAllBtn;

  @FXML
  private Text statusText;
  @FXML
  private ScrollPane errorPane;
  @FXML
  private ScrollPane scrollPane;

  /**
   * Method initialises controller with a repository and sets up button click listeners
   */
  public void initialize() {
    repository = ProductRepository.getInstance();
    fillBtn.setOnAction(this::handleFillBtnClick);
    addBtn.setOnAction(this::handleAddBtnClick);
    deleteBtn.setOnAction(this::handleDeleteBtnClick);
    getPriceBtn.setOnAction(this::handleGetPriceBtnClick);
    changePriceBtn.setOnAction(this::handleChangePriceBtnClick);
    filterBtn.setOnAction(this::handleFilterBtnClick);
    showAllBtn.setOnAction(this::handleShowAllBtnClick);
  }

  @FXML
  private void handleFillBtnClick(ActionEvent event) {
    try {
      repository.fill(fillText.getText());
      scrollPane.setContent(getEntries());
      setSuccessStatus();
    } catch (Exception e) {
      setFailStatus(e.getMessage());
    }
  }

  @FXML
  private void handleAddBtnClick(ActionEvent event) {
    try {
      String name = addNameText.getText();
      String price = addPriceText.getText();
      repository.add(name, price);
      scrollPane.setContent(getEntries());
      setSuccessStatus();
    } catch (Exception e) {
      setFailStatus(e.getMessage());
    }
  }

  @FXML
  private void handleDeleteBtnClick(ActionEvent event) {
    try {
      String name = deleteNameText.getText();
      repository.delete(name);
      scrollPane.setContent(getEntries());
      setSuccessStatus();
    } catch (Exception e) {
      setFailStatus(e.getMessage());
    }
  }

  @FXML
  private void handleGetPriceBtnClick(ActionEvent event) {
    try {
      String name = getPriceNameText.getText();
      scrollPane.setContent(getEntries(List.of(repository.getPrice(name))));
      setSuccessStatus();
    } catch (Exception e) {
      setFailStatus(e.getMessage());
    }
  }

  @FXML
  private void handleChangePriceBtnClick(ActionEvent event) {
    try {
      String name = changePriceNameText.getText();
      String price = changePricePriceText.getText();
      repository.changePrice(name, price);
      scrollPane.setContent(getEntries(List.of(repository.getPrice(name))));
      setSuccessStatus();
    } catch (Exception e) {
      setFailStatus(e.getMessage());
    }
  }

  @FXML
  private void handleFilterBtnClick(ActionEvent event) {
    try {
      String min = filterMinText.getText();
      String max = filterMaxText.getText();
      scrollPane.setContent(getEntries(repository.getProductsByPrice(min, max)));
      setSuccessStatus();
    } catch (Exception e) {
      setFailStatus(e.getMessage());
    }
  }

  @FXML
  private void handleShowAllBtnClick(ActionEvent event) {
    try {
      scrollPane.setContent(getEntries());
      setSuccessStatus();
    } catch (Exception e) {
      setFailStatus(e.getMessage());
    }
  }

  private void setFailStatus(String errorMessage) {
    statusText.setFill(Color.RED);
    statusText.setText("FAIL");
    HBox errorContainer = new HBox();
    errorContainer.setPadding(new Insets(0, 10, 0, 10));
    Text errorText = new Text(errorMessage);
    errorText.setFont(Font.font(20));
    errorText.setFill(Color.RED);
    errorContainer.getChildren().add(errorText);
    errorPane.setContent(errorContainer);
  }

  private void setSuccessStatus() {
    statusText.setFill(Color.GREEN);
    statusText.setText("SUCCESS");
    Text noErrorsText = new Text("NO ERRORS");
    noErrorsText.setFont(Font.font(20));
    noErrorsText.setFill(Color.valueOf("#5b5b5b"));
    noErrorsText.setWrappingWidth(365);
    noErrorsText.setTextAlignment(TextAlignment.CENTER);
    errorPane.setContent(noErrorsText);
  }

  private VBox getEntries() throws Exception {
    VBox entriesContainer = new VBox();
    entriesContainer.setPadding(new Insets(10, 10, 10, 10));
    List<Product> products = repository.getProducts();
    products.forEach(product -> {
      entriesContainer.getChildren().add(getProductWrapper(product));
      entriesContainer.getChildren().add(new Separator());
    });
    return entriesContainer;
  }

  private VBox getEntries(List<Product> products) {
    VBox entriesContainer = new VBox();
    entriesContainer.setPadding(new Insets(10, 10, 10, 10));
    products.forEach(product -> {
      entriesContainer.getChildren().add(getProductWrapper(product));
      entriesContainer.getChildren().add(new Separator());
    });
    return entriesContainer;
  }

  private Text getProductWrapper(Product product) {
    Text productWrapper = new Text();
    productWrapper.setText("Id: " + product.id + "\n" + "Prodid: "
        + product.prodid + "\n" + "Title: " + product.title
        + "\n" + "Cost: " + product.cost);
    return productWrapper;
  }
}
