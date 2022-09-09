package gui.controllers;

import gui.shapes.CustomRectangle;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class DrawController {

  @FXML
  public Button draw;

  public Pane pane;

  public DrawController() {
  }
  public void setPane(Pane pane) {
    this.pane = pane;

  }

  @FXML
  protected void onDrawButtonClick() {

    CustomRectangle rect = new CustomRectangle(100, 100, 150, 125);
    rect.setFill(Color.WHITE);
    new DragController(rect, true);

    pane.getChildren().add(rect);
    for (Node n : rect.getChildren())
      pane.getChildren().add(n);
  }
}
