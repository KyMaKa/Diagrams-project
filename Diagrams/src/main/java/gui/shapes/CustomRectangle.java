package gui.shapes;

import java.util.ArrayList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

//TODO: rework to pane(StackPane)
public class CustomRectangle extends Pane {

  private String placeholder = "Entity";

  private Button addNewAttribute;

  private int attributeY = 20;
  private Line secondLine;

  public CustomRectangle(double x, double y, double width, double height) {
    this(width, height);
    this.setPrefSize(width, height);
    this.setLayoutX(x);
    this.setLayoutY(y);
    this.setPadding(new Insets(10,0,10,0));
    TextField name = new TextField();
    name.setLayoutX(0);
    name.setLayoutY(0);
    name.setText(placeholder);
    name.setAlignment(Pos.CENTER);

    double lineXend = /*x + */width;
    double lineY = 20.0;
    Line underName = new Line();
    underName.setStartX(0);
    underName.setStartY(20);
    underName.setEndX(lineXend);
    underName.setEndY(20);
    underName.setFill(Color.BLACK);
    secondLine = new Line();
    secondLine.setStartX(0);
    secondLine.setStartY(lineY * 4);
    secondLine.setEndX(lineXend);
    secondLine.setEndY(lineY * 4);

    addNewAttribute = new Button();
    addNewAttribute.setLayoutX((width) / 2 - 7);
    addNewAttribute.setLayoutY(55);
    addNewAttribute.setOnMouseClicked(mouseEvent -> addText("attribute"));

    //name.layoutXProperty().bind(this.layoutXProperty().subtract(name.getLayoutBounds().getMinX()));
    //name.layoutYProperty().bind(this.layoutYProperty().subtract(name.getLayoutBounds().getMinY()));
    //underName.layoutXProperty().bind(this.layoutXProperty().subtract(underName.getLayoutBounds().getMinX()));
    //underName.layoutYProperty().bind(this.layoutYProperty().subtract(underName.getLayoutBounds().getMinY()).add(lineY));
    //secondLine.layoutXProperty().bind(this.layoutXProperty().subtract(secondLine.getLayoutBounds().getMinX()));
    //secondLine.layoutYProperty().bind(this.layoutYProperty().subtract(secondLine.getLayoutBounds().getMinY()).add(lineY * 4));

    this.getChildren().add(name);
    this.getChildren().add(underName);
    this.getChildren().add(secondLine);
    this.getChildren().add(addNewAttribute);
  }

  public CustomRectangle(double width, double height) {
    setWidth(width);
    setHeight(height);
  }

  private void addText(String input) {
    TextField text = new TextField(input);
    text.setLayoutX(this.getWidth() / 2 - 14);
    text.setLayoutY(attributeY);
    attributeY += 20;
    this.getChildren().add(text);
    if (this.addNewAttribute.getLayoutBounds().contains(text.getLayoutBounds())) {
      this.addNewAttribute.relocate(this.addNewAttribute.getLayoutX(), this.addNewAttribute.getLayoutY() + 20);
      this.setPrefSize(this.getWidth(), this.getHeight() + 20);
      this.secondLine.setStartY(this.secondLine.getStartY() + 20);
      this.secondLine.setEndY(this.secondLine.getEndY() + 20);

    }
  }



  public Button getAddNewAttribute() {
    return this.addNewAttribute;

  }
}
