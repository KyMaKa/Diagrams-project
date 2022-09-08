package logic.Entity;

import logic.Attribute.Attribute;
import logic.Relationship.Relationship;

public class Entity {

  public Entity(String name) {
    this.name = name;
  }

  private String name;

  private Attribute[] attributes;

  private Relationship[] relations;

  private double posX;

  private double posY;

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

  public Attribute[] getAttributes() {
    return attributes;
  }

  public void setAttributes(Attribute[] attributes) {
    this.attributes = attributes;
  }

  public Relationship[] getRelations() {
    return relations;
  }

  public void setRelations(Relationship[] relations) {
    this.relations = relations;
  }

}
