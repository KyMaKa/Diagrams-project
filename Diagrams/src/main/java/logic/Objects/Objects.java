package logic.Objects;

import java.util.LinkedList;
import java.util.List;
import logic.Entity.Entity;

public class Objects {
  private final List<Entity> objects;

  public Objects() {
    this.objects = new LinkedList<>();

  }

  public void addObject(Entity node) {
    this.objects.add(node);

  }

  public List<Entity> getObjects() {
    return this.objects;

  }

}
