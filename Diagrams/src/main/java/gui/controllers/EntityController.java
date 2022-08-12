package gui.controllers;

import gui.App;
import gui.shapes.CustomRectangle;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

public class EntityController {

  @FXML
  private Pane pane;

  @FXML
  protected void onDrawButtonClick() {
    CustomRectangle rect = new CustomRectangle(100, 100, 200, 100);
    DragController dragController = new DragController(rect, true);
    pane.getChildren().addAll(rect, rect.getText().get(0));
  }

  public Node createShape() {
    CustomRectangle rect = new CustomRectangle(100, 100, 200, 100);
    DragController dragController = new DragController(rect, true);
    return rect;
  }

}
