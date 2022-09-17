package gui.shapes;

import javafx.scene.shape.Circle;

public class Connector extends Circle {

  private double xInParent;
  private double yInParent;
  private String parentName;

  public double getxInParent() {
    return xInParent;
  }

  public void setxInParent(double xInParent) {
    this.xInParent = xInParent;
  }

  public double getyInParent() {
    return yInParent;
  }

  public void setyInParent(double yInParent) {
    this.yInParent = yInParent;
  }

  public String getParentName() {
    return parentName;
  }

  public void setParentName(String parentName) {
    this.parentName = parentName;
  }
}
