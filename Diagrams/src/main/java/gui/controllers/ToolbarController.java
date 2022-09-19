package gui.controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import gui.shapes.AttributeGUI;
import gui.shapes.Connector;
import gui.shapes.CustomLine;
import gui.shapes.CustomRectangle;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import logic.Objects.Objects;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import logic.Objects.Attribute;
import logic.Objects.Entity;
import logic.Objects.Relationship;

public class ToolbarController {

  @FXML
  private ToolBar toolBar;

  @FXML
  private Button load;

  @FXML
  private Button save;

  private Pane pane;

  public void setPane(Pane pane) {
    this.pane = pane;
  }

  private DrawController drawController;

  public void setDrawController(DrawController drawController) {
    this.drawController = drawController;
  }

  @FXML
  public void onSaveButtonPressed() throws IOException {

    ObservableList<Node> children = pane.getChildren();
    Objects objects = new Objects();
    for (Node n : children) {
      if (n.getClass() == CustomRectangle.class) {
        CustomRectangle node = (CustomRectangle) n;
        String name = node.getName();
        Entity entity = new Entity(name);

        entity.setPosX(node.getLayoutX());
        entity.setPosY(node.getLayoutY());
        entity.setHeight(node.getHeight());
        entity.setWidth(node.getWidth());
        List<Attribute> attributes = new LinkedList<>();
        for (AttributeGUI attr : node.getAttributes()) {
          Attribute attribute = new Attribute();
          attribute.setName(attr.getAttributeText());
          attribute.setType(attr.getType());
          attributes.add(attribute);
        }

        entity.setAttributes(attributes);

        List<Relationship> relationships = new LinkedList<>();
        Map<Connector, List<CustomLine>> relations = node.getRelations();
        relations.forEach((Connector key, List<CustomLine> line) -> {
          for (CustomLine rel : line) {
            Relationship relationship = new Relationship();
            //relationship.setParent(entity);
            relationship.setType(rel.getRelType());
            relationship.setParentX(key.getLayoutX());
            relationship.setParentY(key.getLayoutY());
            relationship.setChildX(rel.getChildConnector().getLayoutX());
            relationship.setChildY(rel.getChildConnector().getLayoutY());
            relationship.setChild(new Entity(rel.getChild().getName()));
            relationships.add(relationship);
          }
        });
        entity.setRelations(relationships);

        objects.addObject(entity);
      }
    }
    Gson g = new GsonBuilder().setPrettyPrinting().create();

    try (Writer writer = new FileWriter("project1.json")) {
      g.toJson(objects, writer);
    }

  }

  @FXML
  public void onLoadButtonPressed() throws IOException {

    Gson g = new Gson();
    Objects objects;
    this.pane.getChildren().clear();
    try (Reader reader = new FileReader("project1.json")) {
      objects = g.fromJson(reader, Objects.class);
    }
    List<Entity> entities = objects.getObjects();
    for (Entity entity : entities) {
      CustomRectangle rect = new CustomRectangle(entity.getPosX(), entity.getPosY(),
          entity.getWidth(), entity.getHeight());
      rect.setName(entity.getName());
      rect.setBackground(
          new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
      //new DragController(rect, true);

      for (Attribute attr : entity.getAttributes()) {
        rect.addAttribute(attr.getName(), attr.getType());
      }

      for (Relationship rel : entity.getRelations()) {
        rect.getRelations().forEach((key, value) -> {
          if (key.getLayoutX() == rel.getParentX() && key.getLayoutY() == rel.getParentY()) {
            Entity e = rel.getChild();
            CustomRectangle child = new CustomRectangle(e.getPosX(), e.getPosY(), e.getWidth(), e.getHeight());
            child.setName(e.getName());
            Connector connector = new Connector();
            connector.setParentName(e.getName());
            connector.setLayoutX(rel.getChildX());
            connector.setLayoutY(rel.getChildY());
            CustomLine line = new CustomLine();
            line.setChildConnector(connector);
            line.setChild(child);
            line.setRelType(rel.getType());

            rect.addConnector(key, line);
          }
        });
      }
      drawController.drawEntity(rect);
      //pane.getChildren().add(rect);
    }

    List<CustomRectangle> children = new LinkedList<>();
    for (Node n : pane.getChildren().stream().toList()) {
      children.add((CustomRectangle) n);
    }

    for(CustomRectangle source : children) {
      Map<Connector, List<CustomLine>> rels = source.getRelations()
          .entrySet()
          .stream()
          .collect(Collectors.toMap(Entry::getKey, e -> List.copyOf(e.getValue())));

      source.getRelations().forEach((key, value) -> {
        value.clear();
      });

      rels.forEach((key, value) -> {
        for (CustomLine c : value) {
          CustomRectangle tSource = children.stream().filter( node -> node.getName().equals(c.getChild().getName())).toList().get(0);
          tSource.getRelations().forEach((conn, list) -> {
            if (conn.getLayoutY() == c.getChildConnector().getLayoutY() && conn.getLayoutX() == c.getChildConnector().getLayoutX()) {
              drawController.drawRelation(source, key, tSource, conn, c.getRelType());
            }
          });
        }
      });
    }

  }

}
