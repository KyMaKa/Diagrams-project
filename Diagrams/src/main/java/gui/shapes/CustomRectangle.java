package gui.shapes;

import java.util.ArrayList;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

//TODO: rework to pane(StackPane)
public class CustomRectangle extends Rectangle {

  private ArrayList<Node> elements;

  private String placeholder = "Entity";

  private Button addNewAttribute;

  public CustomRectangle(double x, double y, double width, double height) {
    this(width, height);
    setLayoutX(x);
    setLayoutY(y);
    TextField name = new TextField();
    this.elements = new ArrayList<>();
    name.setLayoutX(x);
    name.setLayoutY(y);
    name.setText(placeholder);
    name.setAlignment(Pos.CENTER);

    double lineXend = x + width;
    double lineY = 20.0;
    Line underName = new Line();
    underName.setStartX(x);
    underName.setStartY(lineY);
    underName.setEndX(lineXend);
    underName.setEndY(lineY);
    underName.setFill(Color.BLACK);
    Line secondLine = new Line();
    secondLine.setStartX(x);
    secondLine.setStartY(lineY * 4);
    secondLine.setEndX(lineXend);
    secondLine.setEndY(lineY * 4);

    addNewAttribute = new Button();
    addNewAttribute.setLayoutX((x * 2 + width) / 2 - 7);
    addNewAttribute.setLayoutY(y + 55);
    addNewAttribute.setOnMouseClicked(mouseEvent -> {
      addText("attribute");
    });

    name.layoutXProperty().bind(this.layoutXProperty().subtract(name.getLayoutBounds().getMinX()));
    name.layoutYProperty().bind(this.layoutYProperty().subtract(name.getLayoutBounds().getMinY()));
    underName.layoutXProperty().bind(this.layoutXProperty().subtract(underName.getLayoutBounds().getMinX()));
    underName.layoutYProperty().bind(this.layoutYProperty().subtract(underName.getLayoutBounds().getMinY()).add(lineY));
    secondLine.layoutXProperty().bind(this.layoutXProperty().subtract(secondLine.getLayoutBounds().getMinX()));
    secondLine.layoutYProperty().bind(this.layoutYProperty().subtract(secondLine.getLayoutBounds().getMinY()).add(lineY * 4));

    //elements.add(name);
    elements.add(underName);
    elements.add(secondLine);
    elements.add(addNewAttribute);
  }

  public CustomRectangle(double width, double height) {
    setWidth(width);
    setHeight(height);
  }

  private void addText(String input) {
    TextField text = new TextField(input);
    text.setLayoutX(this.getX());
    text.setLayoutY(this.getY());
    this.elements.add(text);
  }

  public Button getAddNewAttribute() {
    return this.addNewAttribute;

  }

  public ArrayList<Node> getChildren() {
    return elements;
  }
}
