package logic.validation;

import gui.shapes.AttributeGUI;
import gui.shapes.CustomRectangle;
import java.util.List;
import logic.types.Annotations;

public class PKvalidator {

  public boolean validate(CustomRectangle source, CustomRectangle target) {
    List<AttributeGUI> pksSource = source.getAttributes()
        .stream()
        .filter(attributeGUI -> attributeGUI.getAnnotation() == Annotations.PK)
        .toList();

    List<AttributeGUI> pksTarget = target.getAttributes()
        .stream()
        .filter(attributeGUI -> attributeGUI.getAnnotation() == Annotations.PK)
        .toList();

    if (pksSource.size() == 0 || pksTarget.size() == 0) {
      return false;
    }

    return pksSource.get(0).getType() == pksTarget.get(0).getType();
  }

}
