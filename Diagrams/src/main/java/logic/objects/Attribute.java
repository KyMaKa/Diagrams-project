package logic.objects;

import logic.types.Annotations;
import logic.types.AttTypes;

public class Attribute {

  private String name;

  private AttTypes type;

  private Annotations annotations;

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public AttTypes getType() {
    return this.type;
  }

  public void setType(AttTypes type) {
    this.type = type;
  }

  public Annotations getAnnotations() {
    return this.annotations;
  }

  public void setAnnotations(Annotations annotations) {
    this.annotations = annotations;
  }

}
