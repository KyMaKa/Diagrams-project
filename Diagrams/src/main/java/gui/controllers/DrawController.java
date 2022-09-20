package gui.controllers;

import gui.shapes.CustomRectangle;
import java.util.LinkedList;
import java.util.List;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import gui.shapes.Connector;
import gui.shapes.CustomLine;
import logic.types.RelTypes;
import logic.validation.EntityNames;
import logic.validation.AnnotationValidator;

public class DrawController {

  @FXML
  public Button draw;

  public Pane pane;

  private CustomRectangle source = null;
  private Node connector = null;

  private final List<CustomLine> lines = new LinkedList<>();

  private final AnnotationValidator validator = new AnnotationValidator();
  private final EventHandler<MouseEvent> relationHandler = mouseEvent -> {

    Node target = (Node) mouseEvent.getTarget();
    if (source == null) {
      connector = target;
      source = (CustomRectangle) target.getParent();
      System.out.println("Clicked");
    } else {
      Node tSource = target.getParent();
      if (validator.validatePK(source, (CustomRectangle) tSource)) {
        drawRelation(source, (Connector) connector, (CustomRectangle) tSource, (Connector) target,
            null);
      } else {
        mouseEvent.consume();
      }
      source = null;
      connector = null;
    }
  };

  private final EventHandler<KeyEvent> deleteHandler = event -> {
    if (event.getCode() == KeyCode.DELETE) {
      CustomRectangle target = (CustomRectangle) event.getSource();
      removeLine(target);
      for (CustomLine c : target.getParents()) {
        removeLine(c.getParentRect());
        this.pane.getChildren().remove(target);
      }
      this.pane.getChildren().remove(target);
    }
  };

  private void removeLine(CustomRectangle target) {
    target.getRelations().forEach((key, value) -> {
      for (CustomLine c : value) {
        this.pane.getChildren().remove(c.getArrow1());
        this.pane.getChildren().remove(c.getArrow2());
        this.pane.getChildren().remove(c.getRels());
        this.pane.getChildren().remove(c);
      }
    });
  }

  public DrawController() {
  }
  public void setPane(Pane pane) {
    this.pane = pane;

  }

  @FXML
  protected void onDrawButtonClick() {

    CustomRectangle rect = new CustomRectangle(100, 100, 220, 125);
    rect.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY,
        Insets.EMPTY)));
    drawEntity(rect);
  }

  public void drawEntity(CustomRectangle rect) {
    new DragController(rect, true);
    rect.setConnectorPressed(relationHandler);
    rect.setDeleteEvent(deleteHandler);
    if (EntityNames.entityNames.containsKey(rect.getName())) {
      String newName = rect.getName() + EntityNames.entityNames.get(rect.getName());
      EntityNames.entityNames.put(rect.getName(), EntityNames.entityNames.get(rect.getName()) + 1);
      rect.setName(newName);
      EntityNames.entityNames.put(newName, 1);
    }
    EntityNames.entityNames.put(rect.getName(), 1);
    pane.getChildren().add(rect);
  }

  public void drawRelation(CustomRectangle source, Connector connector, CustomRectangle tSource, Connector target, RelTypes type) {
    CustomLine customLine = new CustomLine();
    ChoiceBox<RelTypes> relType = customLine.getRels();
    relType.setOnAction(event -> {
      if (relType.getValue().equals(RelTypes.OneToMany) || relType.getValue().equals(RelTypes.ManyToMany)) {
        if (!validator.validateFK(source, tSource)) {
          relType.setValue(RelTypes.OneToOne);
        }
      }
    });

    customLine.setLine(source, connector, tSource, target);
    customLine.setChild(tSource);
    customLine.setChildConnector(target);
    customLine.setRelType(type);
    source.addConnector(connector, customLine);
    customLine.setParentRect(source);
    tSource.addParent(customLine);
    this.pane.getChildren().addAll(customLine, customLine.getRels(), customLine.getArrow2(), customLine.getArrow1());
  }
}
