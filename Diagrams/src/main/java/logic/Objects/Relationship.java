package logic.Objects;

import logic.Types.RelTypes;

public class Relationship {
  private String name;

  private RelTypes type;

  private Entity parent;

  private Entity child;

  private double childX;

  private double childY;

  public double getChildX() {
    return childX;
  }

  public void setChildX(double childX) {
    this.childX = childX;
  }

  public double getChildY() {
    return childY;
  }

  public void setChildY(double childY) {
    this.childY = childY;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public RelTypes getType() {
    return type;
  }

  public void setType(RelTypes type) {
    this.type = type;
  }

  public Entity getParent() {
    return parent;
  }

  public void setParent(Entity parent) {
    this.parent = parent;
  }

  public Entity getChild() {
    return child;
  }

  public void setChild(Entity child) {
    this.child = child;
  }
}
