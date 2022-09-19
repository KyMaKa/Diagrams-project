package logic.objects;

import java.util.List;

public class Entity {

  public Entity(String name) {
    this.name = name;
  }

  private String name;

  private List<Attribute> attributes;

  private List<Relationship> relations;

  private double posX;

  private double posY;

  private double width;

  private double height;

  public double getWidth() {
    return width;
  }

  public void setWidth(double width) {
    this.width = width;
  }

  public double getHeight() {
    return height;
  }

  public void setHeight(double height) {
    this.height = height;
  }

  public double getPosX() {
    return posX;
  }

  public void setPosX(double posX) {
    this.posX = posX;
  }

  public double getPosY() {
    return posY;
  }

  public void setPosY(double posY) {
    this.posY = posY;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<Attribute> getAttributes() {
    return attributes;
  }

  public void setAttributes(List<Attribute> attributes) {
    this.attributes = attributes;
  }

  public List<Relationship> getRelations() {
    return relations;
  }

  public void setRelations(List<Relationship> relations) {
    this.relations = relations;
  }

}
