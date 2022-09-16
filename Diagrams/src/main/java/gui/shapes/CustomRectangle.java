package gui.shapes;

import java.util.LinkedList;
import java.util.List;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public class CustomRectangle extends Pane {

  private Button addAttrButton;

  private final TextField name = new TextField("Entity");

  private int attributeY = 20;
  private Line secondLine;

  private Circle circle;

  private final List<TextField> attributes = new LinkedList<>();

  public CustomRectangle(double x, double y, double width, double height) {
    this(width, height);
    this.setPrefSize(width, height);
    this.setLayoutX(x);
    this.setLayoutY(y);
    this.setPadding(new Insets(10, 0, 10, 0));
    name.setLayoutX(0);
    name.setLayoutY(0);
    name.setAlignment(Pos.CENTER);

    double tst = 6.25;
    double lineY = 20;
    Line underName = new Line();
    underName.setStartX(0);
    underName.setStartY(20);
    underName.setEndX(width);
    underName.setEndY(20);
    underName.setFill(Color.BLACK);

    secondLine = new Line();
    secondLine.setStartX(0);
    double sLineY = lineY * 4 + (20 * (height / lineY - tst));
    secondLine.setStartY(sLineY);
    secondLine.setEndX(width);
    secondLine.setEndY(sLineY);

    addAttrButton = new Button();
    addAttrButton.setLayoutX((width) / 2 - 7);
    addAttrButton.setLayoutY(lineY * 2.75+ (20 * (height / lineY - tst)));
    addAttrButton.setOnMouseClicked(mouseEvent -> addText("attribute"));

    circle = new Circle();
    circle.setLayoutX(width);
    circle.setLayoutY(height / 2);
    circle.setRadius(5);

    //circle.addEventHandler(MouseEvent.MOUSE_PRESSED, lineEvent);

    //name.layoutXProperty().bind(this.layoutXProperty().subtract(name.getLayoutBounds().getMinX()));
    //name.layoutYProperty().bind(this.layoutYProperty().subtract(name.getLayoutBounds().getMinY()));
    //underName.layoutXProperty().bind(this.layoutXProperty().subtract(underName.getLayoutBounds().getMinX()));
    //underName.layoutYProperty().bind(this.layoutYProperty().subtract(underName.getLayoutBounds().getMinY()).add(lineY));
    //secondLine.layoutXProperty().bind(this.layoutXProperty().subtract(secondLine.getLayoutBounds().getMinX()));
    //secondLine.layoutYProperty().bind(this.layoutYProperty().subtract(secondLine.getLayoutBounds().getMinY()).add(lineY * 4));

    this.getChildren().add(circle);
    this.getChildren().add(name);
    this.getChildren().add(underName);
    this.getChildren().add(secondLine);
    this.getChildren().add(addAttrButton);
  }

  public CustomRectangle(double width, double height) {
    setWidth(width);
    setHeight(height);
  }

  public void setLineEvent(EventHandler<MouseEvent> handler) {
    circle.addEventFilter(MouseEvent.MOUSE_PRESSED, handler);
  }

  public void drawLine(MouseEvent mouseEvent) {
    Node n = (Node) mouseEvent.getSource();
    System.out.println(n.getParent());

  }

  public void addText(String input) {
    TextField text = new TextField(input);
    text.setAlignment(Pos.CENTER);
    text.setPrefWidth(150);
    //text.setLayoutX(this.getWidth() / 2 - 15);
    text.setLayoutY(attributeY);
    attributeY += 20;
    this.getChildren().add(text);
    this.attributes.add(text);
    if (isOverlapping(text)) {
      this.addAttrButton.relocate(this.addAttrButton.getLayoutX(),
          this.addAttrButton.getLayoutY() + 20);
      this.setPrefSize(this.getWidth(), this.getHeight() + 20);
      this.secondLine.setStartY(this.secondLine.getStartY() + 20);
      this.secondLine.setEndY(this.secondLine.getEndY() + 20);

    }
  }

  private boolean isOverlapping(Node other) {
    return (this.addAttrButton.getLayoutY() - other.getLayoutY() < 35);
  }

  public void setName(String name) {
    this.name.setText(name);
  }

  public String getName() {
    return this.name.getText();
  }

  public Button getAddAttrButton() {
    return this.addAttrButton;
  }

  public List<TextField> getAttributes() {
    return this.attributes;
  }
}
