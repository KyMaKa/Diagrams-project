package gui.shapes;

import javafx.beans.InvalidationListener;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import logic.Types.RelTypes;

public class CustomLine extends Line {

  private Line arrow1;

  private Line arrow2;

  private final ChoiceBox<RelTypes> relType = new ChoiceBox<>();

  private CustomRectangle child;

  private Connector childConnector;


  public void setChild(CustomRectangle child) {
    this.child = child;
  }

  public CustomRectangle getChild() {
    return this.child;
  }

  public void setChildConnector(Connector connector) {
    this.childConnector = connector;
  }

  public Connector getChildConnector() {
    return this.childConnector;
  }

  public CustomLine() {
    this.relType.getItems().addAll(RelTypes.values());
    this.relType.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
    this.relType.layoutXProperty().bind(this.startXProperty().add(10));
    this.relType.layoutYProperty().bind(this.startYProperty());
  }

  public RelTypes getRelType() {
    return this.relType.getValue();
  }

  public void setRelType(RelTypes type) {
    this.relType.setValue(type);
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

    drawArrowLine();
  }

  private void drawArrowLine() {
    arrow1 = new Line();
    arrow2 = new Line();
    InvalidationListener updater = o -> {
      double ex = this.getEndX();
      double ey = this.getEndY();
      double sx = this.getStartX();
      double sy = this.getStartY();

      arrow1.setEndX(ex);
      arrow1.setEndY(ey);
      arrow2.setEndX(ex);
      arrow2.setEndY(ey);

      if (ex == sx && ey == sy) {
        // arrow parts of length 0
        arrow1.setStartX(ex);
        arrow1.setStartY(ey);
        arrow2.setStartX(ex);
        arrow2.setStartY(ey);
      } else {
        double hypot = Math.hypot(sx - ex, sy - ey);
        double factor = 15 / hypot;
        double factorO = 7 / hypot;

        // part in direction of main line
        double dx = (sx - ex) * factor;
        double dy = (sy - ey) * factor;

        // part orthogonal to main line
        double ox = (sx - ex) * factorO;
        double oy = (sy - ey) * factorO;

        arrow1.setStartX(ex + dx - oy);
        arrow1.setStartY(ey + dy + ox);
        arrow2.setStartX(ex + dx + oy);
        arrow2.setStartY(ey + dy - ox);
      }
    };

    // add updater to properties
    startXProperty().addListener(updater);
    startYProperty().addListener(updater);
    endXProperty().addListener(updater);
    endYProperty().addListener(updater);
    updater.invalidated(null);
  }

  public ChoiceBox<RelTypes> getRels() {
    return this.relType;
  }

  public Line getArrow1() {
    return arrow1;
  }

  public Line getArrow2() {
    return arrow2;
  }
}
