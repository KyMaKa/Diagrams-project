package gui.shapes;

import java.util.ArrayList;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class CustomRectangle extends Rectangle {

  private ArrayList<Text> texts;

  public CustomRectangle(double x, double y, double width, double height) {
    this(width, height);
    setX(x);
    setY(y);
    Text text = new Text();
    this.texts = new ArrayList<>();
    text.setX(this.getX());
    text.setY(this.getY());
    text.setText("Some text");
    text.setFill(Color.WHITE);
    texts.add(text);
  }

  public CustomRectangle(double width, double height) {
    setWidth(width);
    setHeight(height);
  }

  private void addText() {
    Text text = new Text();
    this.texts.add(text);
    text.setX(this.getX());
    text.setY(this.getY());
    text.setText("Some text");
  }

  public ArrayList<Text> getText() {
    return texts;
  }
}
