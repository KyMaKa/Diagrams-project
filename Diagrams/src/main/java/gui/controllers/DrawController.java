package gui.controllers;

import gui.shapes.CustomRectangle;
import java.util.LinkedList;
import java.util.List;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import gui.shapes.Connector;
import gui.shapes.CustomLine;

public class DrawController {

  @FXML
  public Button draw;

  public Pane pane;

  private CustomRectangle source = null;
  private Node connector = null;

  private final List<CustomLine> lines = new LinkedList<>();

  private final EventHandler<MouseEvent> handler = mouseEvent -> {
    Node target = (Node) mouseEvent.getTarget();
    if (source == null) {
      connector = target;
      source = (CustomRectangle) target.getParent();
      System.out.println("Clicked");
    } else {
      Node tSource = target.getParent();
      CustomLine customLine = new CustomLine();
      customLine.setLine(source, connector, tSource, target);
      customLine.setChild((CustomRectangle) tSource);
      customLine.setChildConnector((Connector) target);
      source.addConnector((Connector) connector, customLine);
      this.pane.getChildren().addAll(customLine, customLine.getParentText(), customLine.getChildText());
      this.lines.add(customLine);
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
