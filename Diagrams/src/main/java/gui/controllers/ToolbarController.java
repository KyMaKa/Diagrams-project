package gui.controllers;

import com.google.gson.Gson;
import gui.shapes.CustomRectangle;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.LinkedList;
import java.util.List;
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

  @FXML
  public void onSaveButtonPressed() throws IOException {

    ObservableList<Node> children = pane.getChildren();
    Objects objects = new Objects();
    for (Node n : children) {
      CustomRectangle node = (CustomRectangle) n;
      String name = node.getName();
      Entity entity = new Entity(name);
      entity.setPosX(node.getLayoutX());
      entity.setPosY(node.getLayoutY());
      entity.setHeight(node.getHeight());
      entity.setWidth(node.getWidth());
      List<Attribute> attributes = new LinkedList<>();
      for (TextField attr : node.getAttributes()) {
        Attribute attribute = new Attribute();
        attribute.setName(attr.getText());
        attributes.add(attribute);
      }

      entity.setAttributes(attributes);
      objects.addObject(entity);
    }
    Gson g = new Gson();

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
      new DragController(rect, true);

      for (Attribute attr : entity.getAttributes()) {
        rect.addText(attr.getName());
      }

      pane.getChildren().add(rect);
    }

  }

}
