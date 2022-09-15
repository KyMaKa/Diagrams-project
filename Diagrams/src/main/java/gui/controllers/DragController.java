package gui.controllers;

import gui.shapes.CustomRectangle;
import java.util.ArrayList;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class DragController {
  private final Node target;

  private double dragX;

  private double dragY;

  private EventHandler<MouseEvent> setAnchor;
  private EventHandler<MouseEvent> updatePositionOnDrag;
  private EventHandler<MouseEvent> commitPositionOnRelease;
  private final int ACTIVE = 1;
  private final int INACTIVE = 0;
  private int cycleStatus = INACTIVE;
  private BooleanProperty isDraggable;

  public DragController(Node target) {
    this(target, false);
  }
  public DragController(Node target, boolean isDraggable) {
    this.target = target;
    createHandlers();
    createDraggableProperty();
    this.isDraggable.set(isDraggable);
  }
  private void createHandlers() {

    setAnchor = mouseEvent -> {
      dragX = target.getLayoutX() - mouseEvent.getSceneX();
      dragY = target.getLayoutY() - mouseEvent.getSceneY();
      target.setCursor(Cursor.MOVE);
    };

    commitPositionOnRelease = mouseEvent -> {
      target.setCursor(Cursor.HAND);
    };

    updatePositionOnDrag = mouseEvent -> {
      target.setLayoutX(mouseEvent.getSceneX() + dragX);
      target.setLayoutY(mouseEvent.getSceneY() + dragY);
      //moveChildren((CustomRectangle) target);
    };

    target.setOnMouseEntered(mouseEvent -> {
      target.setCursor(Cursor.HAND);
    });


  }

  public void createDraggableProperty() {
    isDraggable = new SimpleBooleanProperty();
    isDraggable.addListener((observable, oldValue, newValue) -> {
      if (newValue) {
        target.addEventFilter(MouseEvent.MOUSE_PRESSED, setAnchor);
        target.addEventFilter(MouseEvent.MOUSE_DRAGGED, updatePositionOnDrag);
        target.addEventFilter(MouseEvent.MOUSE_RELEASED, commitPositionOnRelease);
      } else {
        target.removeEventFilter(MouseEvent.MOUSE_PRESSED, setAnchor);
        target.removeEventFilter(MouseEvent.MOUSE_DRAGGED, updatePositionOnDrag);
        target.removeEventFilter(MouseEvent.MOUSE_RELEASED, commitPositionOnRelease);
      }
    });
  }
  public boolean isIsDraggable() {
    return isDraggable.get();
  }
  public BooleanProperty isDraggableProperty() {
    return isDraggable;
  }
}
