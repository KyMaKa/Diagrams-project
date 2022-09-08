package gui.controllers;

import com.google.gson.Gson;
import gui.shapes.CustomRectangle;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import logic.Entity.Entity;

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

    CustomRectangle node = (CustomRectangle) children.get(0);
    TextField textField = (TextField) node.getChildren().get(0);
    Entity entity = new Entity(textField.getText());
    entity.setPosX(node.getLayoutX());
    entity.setPosY(node.getLayoutY());

    System.out.println(node.getLayoutX());

    Gson g = new Gson();

    try (Writer writer = new FileWriter("project1.json")) {
      g.toJson(entity, writer);
    }

  }

  @FXML
  public void onLoadButtonPressed() throws IOException {

    Gson g = new Gson();
    Entity entity;

    try (Reader reader = new FileReader("project1.json")) {
      entity = g.fromJson(reader, Entity.class);
    }

    CustomRectangle rect = new CustomRectangle(entity.getPosX(), entity.getPosY(), 100, 200);
    rect.setFill(Color.WHITE);
    DragController dragController = new DragController(rect, true);

    pane.getChildren().add(rect);
    for (Node n : rect.getChildren())
      pane.getChildren().add(n);

  }

}
