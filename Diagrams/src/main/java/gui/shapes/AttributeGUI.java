package gui.shapes;

import java.util.EventObject;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import logic.types.Annotations;
import logic.types.AttTypes;

public class AttributeGUI extends HBox {

  private TextField attribute;

  private ChoiceBox<AttTypes> types;

  private ChoiceBox<Annotations> annotation;

  public AttributeGUI(double x, double y, double width, double height) {
    this.setLayoutX(x);
    this.setLayoutY(y);
    this.setWidth(width);
    this.setHeight(height);
    this.setPrefSize(width, height);
    this.setAlignment(Pos.CENTER);
    this.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY,
        Insets.EMPTY)));
    this.setPadding(new Insets(0, 10, 0, 10));
    this.setSpacing(15);

    this.annotation = new ChoiceBox<>();
    this.annotation.getItems().addAll(Annotations.values());
    this.annotation.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
    this.annotation.setMaxWidth(40);
    this.attribute = new TextField("attribute");
    this.attribute.setMaxWidth(70);
    this.attribute.setAlignment(Pos.CENTER_LEFT);
    this.types = new ChoiceBox<>();
    this.types.getItems().addAll(AttTypes.values());
    this.types.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
    this.getChildren().addAll(annotation, attribute, types);
  }

  public void setAttributeText(String text) {
    this.attribute.setText(text);
  }

  public String getAttributeText() {
    return this.attribute.getText();
  }

  public void setType(AttTypes type) {
    this.types.setValue(type);
  }

  public AttTypes getType() {
    return this.types.getValue();
  }

  public Annotations getAnnotation() {
    return annotation.getValue();
  }

  public void setAnnotation(Annotations annotation) {
    this.annotation.setValue(annotation);
  }

  public void setContextMenu(EventHandler<MouseEvent> event) {
    this.setOnContextMenuRequested(contextMenuEvent -> {
      ContextMenu menu = new ContextMenu();
      MenuItem delete = new MenuItem();
      delete.addEventHandler(MouseEvent.MOUSE_PRESSED, event);
      menu.getItems().add(delete);
    });
  }
}
