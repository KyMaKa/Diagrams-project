package logic;

import gui.shapes.AttributeGUI;
import gui.shapes.CustomLine;
import gui.shapes.CustomRectangle;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import logic.objects.Attribute;
import logic.types.Annotations;
import logic.types.RelTypes;

public class DLLbuilder {

  private List<CustomRectangle> entities;

  private String script = "";

  private String DbName = "umls";

  public DLLbuilder(List<CustomRectangle> entities) {
    this.entities = entities;
  }

  public void build() throws IOException {
    String drop = "DROP DATABASE IF EXISTS " + DbName + "; \r\n";
    String createDB = "CREATE DATABASE " + DbName + ";";
    String connect = "\\c " + DbName;
    script += drop + createDB + "\r\n" + connect + "\r\n";
    for (CustomRectangle rect : entities) {
      String pkName = null;
      script += "CREATE TABLE " + rect.getName() + " (\r\n";
      int count = 0;
      for (AttributeGUI attr : rect.getAttributes()) {
        count++;
        if (attr.getAnnotation() != null && attr.getAnnotation().equals(Annotations.PK)) {
          pkName = attr.getAttributeText();
        }
        String annon = "";
        if (attr.getAnnotation() != null && attr.getAnnotation() != Annotations.FK) {
          annon = attr.getAnnotation().toString();
        }
        String type = "";
        if (attr.getType() != null) {
          type = attr.getType().name();
        }
        script += attr.getAttributeText() + " " + type + " " + annon;
        if (count != rect.getAttributes().size()) {
          script += ",\r\n ";
        }
      }
      String finalPkName = pkName;
      for (CustomLine c : rect.getParents()) {
        CustomRectangle parent = c.getParentRect();
        List<AttributeGUI> pks = parent.getAttributes().stream()
            .filter(a -> a.getAnnotation() != null && a.getAnnotation().equals(Annotations.PK))
            .toList();

        if (c.getRelType().equals(RelTypes.OneToOne)) {
          script +=
              ",\r\n CONSTRAINT " + "fk_parent " + "FOREIGN KEY " + "(" + finalPkName
                  + ") REFERENCES "
                  + parent.getName() + " (" + pks.get(0).getAttributeText() + ")";

        } else if (c.getRelType().equals(RelTypes.OneToMany)) {
          List<AttributeGUI> fks = rect.getAttributes().stream()
              .filter(a -> a.getAnnotation().equals(Annotations.FK))
              .toList();
          int i = 0;
          for (AttributeGUI fk : fks) {
            script +=
                ",\r\n " + "FOREIGN KEY " + "(" + fk.getAttributeText()
                    + ") REFERENCES "
                    + parent.getName() + " (" + pks.get(i).getAttributeText() + ") "
                    + "ON DELETE CASCADE";
            i++;
          }
        } else if (c.getRelType().equals(RelTypes.ManyToMany)) {
          List<AttributeGUI> pkk = rect.getAttributes().stream()
              .filter(a -> a.getAnnotation().equals(Annotations.PK))
              .toList();
          int i = 0;
          for (AttributeGUI pk : pkk) {
            script +=
                ",\r\n " + "FOREIGN KEY " + "(" + pk.getAttributeText()
                    + ") REFERENCES "
                    + parent.getName() + " (" + pks.get(i).getAttributeText() + ") "
                    + "ON DELETE CASCADE";
            i++;
          }
        }
      }
      script += "); \r\n";
    }
    FileWriter writer = new FileWriter("script.sql");
    writer.write(script);
    writer.close();
    System.out.println(script);
  }

}
