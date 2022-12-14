package gui.controllers;

import gui.shapes.CustomRectangle;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import logic.validation.EntityNames;

public class EntityController {

  @FXML
  DrawController drawController;

  @FXML
  ToolbarController toolbarController;

  @FXML
  public Pane pane;
  @FXML
  public Button draw;
  @FXML
  public ToolBar toolbar;

  @FXML
  public void initialize(){
    drawController.setPane(this.pane);
    toolbarController.setPane(this.pane);
    toolbarController.setDrawController(this.drawController);
    new EntityNames();
  }

}
