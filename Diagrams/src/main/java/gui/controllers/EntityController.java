package gui.controllers;

import gui.shapes.CustomRectangle;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class EntityController {

  @FXML
  private Pane pane;

  @FXML
  protected void onDrawButtonClick() {
    CustomRectangle rect = new CustomRectangle(100, 100, 100, 200);
    rect.setFill(Color.WHITE);
    DragController dragController = new DragController(rect, true);
    TextField textField = new TextField();
    textField.setText("Some text");
    pane.getChildren().addAll(rect, rect.getChildren().get(0), textField);
  }

  public Node createShape() {
    CustomRectangle rect = new CustomRectangle(100, 100, 50, 200);
    DragController dragController = new DragController(rect, true);
    return rect;
  }

}
