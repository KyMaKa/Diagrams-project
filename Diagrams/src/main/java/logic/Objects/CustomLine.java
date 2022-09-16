package logic.Objects;

import javafx.scene.Node;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

public class CustomLine extends Line {

  private final Text parentText = new Text("0..1");

  private final Text childText = new Text("0..1");

  public CustomLine() {
    this.parentText.layoutXProperty().bind(this.startXProperty().add(10));
    this.parentText.layoutYProperty().bind(this.startYProperty());
    this.childText.layoutXProperty().bind(this.endXProperty().add(10));
    this.childText.layoutYProperty().bind(this.endYProperty());
  }

  public Text getParentText() {
    return this.parentText;
  }

  public Text getChildText() {
    return this.childText;
  }

  public void setLine(Node source, Node connector, Node tSource, Node target) {
    this.setStartX(source.getLayoutX() + connector.getLayoutX());
    this.setEndX(tSource.getLayoutX() + target.getLayoutX());
    this.setStartY(source.getLayoutY() + connector.getLayoutY());
    this.setEndY(tSource.getLayoutY() + target.getLayoutY());
    this.startXProperty().bind(source.layoutXProperty().add(connector.getLayoutX()));
    this.startYProperty().bind(source.layoutYProperty().add(connector.getLayoutY()));
    this.endXProperty().bind(tSource.layoutXProperty().add(target.getLayoutX()));
    this.endYProperty().bind(tSource.layoutYProperty().add(target.getLayoutY()));
  }

}
