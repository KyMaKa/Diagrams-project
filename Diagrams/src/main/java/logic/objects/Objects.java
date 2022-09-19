package logic.objects;

import java.util.LinkedList;
import java.util.List;

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
