package gui.shapes;

import java.util.ArrayList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.beans.binding.*;

public class CustomRectangle extends Rectangle {

  private ArrayList<Node> texts;

  private DoubleBinding centerXBinding = new DoubleBinding() {
    {

      bind(xProperty(), widthProperty());

    }
    @Override
    protected double computeValue() {
      return getLayoutX() + getWidth() / 2;
    }
  };

  public CustomRectangle(double x, double y, double width, double height) {
    this(width, height);
    setX(x);
    setY(y);
    TextField text = new TextField();
    this.texts = new ArrayList<>();
    text.setLayoutX(this.getX());
    text.setLayoutY(this.getY());
    text.setText("Some text");
    text.setAlignment(Pos.CENTER);

    text.layoutXProperty().bind(this.layoutXProperty().add(this.getWidth() / 2));
    text.layoutYProperty().bind(this.layoutYProperty().add(100));
    texts.add(text);
  }

  public CustomRectangle(double width, double height) {
    setWidth(width);
    setHeight(height);
  }

  private void addText() {
    TextField text = new TextField();
    this.texts.add(text);
    text.setLayoutX(this.getX());
    text.setLayoutY(this.getY());
    text.setText("Some text");

  }

  public ArrayList<Node> getChildren() {
    return texts;
  }
}
