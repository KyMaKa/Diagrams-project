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
import logic.Objects.CustomLine;

public class DrawController {

  @FXML
  public Button draw;

  public Pane pane;

  private Node source = null;
  private Node connector = null;

  private final EventHandler<MouseEvent> handler = mouseEvent -> {
    Node target = (Node) mouseEvent.getTarget();
    if (source == null) {
      connector = target;
      source = (Node) target.getParent();
      System.out.println("Clicked");
    } else {
      Node tSource = (Node) target.getParent();
      CustomLine customLine = new CustomLine();
      customLine.setLine(source, connector, tSource, target);
      this.pane.getChildren().addAll(customLine, customLine.getParentText(), customLine.getChildText());
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
