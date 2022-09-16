package gui.controllers;

import gui.shapes.CustomRectangle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public class DrawController {

  @FXML
  public Button draw;

  public Pane pane;

  private CustomRectangle source = null;
  private Node connector = null;

  private final EventHandler<MouseEvent> handler = mouseEvent -> {
    Node target = (Node) mouseEvent.getTarget();
    if (source == null) {
      connector = target;
      source = (CustomRectangle) target.getParent();
      System.out.println(connector.getLayoutX());
    } else {
      CustomRectangle tSource = (CustomRectangle) target.getParent();
      Line line = new Line();
      line.setStartX(source.getLayoutX() + connector.getLayoutX());
      line.setEndX(target.getLayoutX() + target.getLayoutX());
      line.setStartY(connector.getLayoutY() + connector.getLayoutY());
      line.setEndY(target.getLayoutY());
      this.pane.getChildren().add(line);
      System.out.println("After line addition.");
      source = null;
      connector = null;
    }
  };

  public DrawController() {
  }
  public void setPane(Pane pane) {
    this.pane = pane;

  }

  @FXML
  protected void onDrawButtonClick() {

    CustomRectangle rect = new CustomRectangle(100, 100, 150, 125);
    rect.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY,
        Insets.EMPTY)));
    new DragController(rect, true);
    rect.setLineEvent(handler);
    pane.getChildren().add(rect);
  }
}
