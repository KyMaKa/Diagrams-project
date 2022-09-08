package gui.shapes;

import java.util.ArrayList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class CustomRectangle extends Rectangle {

  private ArrayList<Node> elements;

  private String placeholder = "Some text";

  public CustomRectangle(double x, double y, double width, double height) {
    this(width, height);
    setLayoutX(x);
    setLayoutY(y);
    TextField text = new TextField();
    this.elements = new ArrayList<>();
    text.setLayoutX(x);
    text.setLayoutY(y);
    text.setText(placeholder);
    text.setAlignment(Pos.CENTER);

    double lineXend = x + width;
    double lineY = 20.0;
    Line underName = new Line();
    underName.setStartX(x);
    underName.setStartY(lineY);
    underName.setEndX(lineXend);
    underName.setEndY(lineY);
    underName.setFill(Color.BLACK);

    text.layoutXProperty().bind(this.layoutXProperty().subtract(text.getLayoutBounds().getMinX()));
    text.layoutYProperty().bind(this.layoutYProperty().subtract(text.getLayoutBounds().getMinY()));
    underName.layoutXProperty().bind(this.layoutXProperty().subtract(underName.getLayoutBounds().getMinX()));
    underName.layoutYProperty().bind(this.layoutYProperty().subtract(underName.getLayoutBounds().getMinY()).add(lineY));

    elements.add(text);
    elements.add(underName);
  }

  public CustomRectangle(double width, double height) {
    setWidth(width);
    setHeight(height);
  }

  private void addText() {
    TextField text = new TextField();
    this.elements.add(text);
    text.setLayoutX(this.getX());
    text.setLayoutY(this.getY());
    text.setText("Some text");

  }

  public ArrayList<Node> getChildren() {
    return elements;
  }
}
