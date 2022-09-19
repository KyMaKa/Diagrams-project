package gui.shapes;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import java.util.Map;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import logic.Types.AttTypes;

public class CustomRectangle extends Pane {

  private Button addAttrButton;

  private final TextField name = new TextField("Entity");

  private int attributeY = 25;
  private Line secondLine;

  private Connector connector;


  private final List<AttributeGUI> attributes = new LinkedList<>();

  private final Map<Connector, List<CustomLine>> relations = new HashMap<>();

  public CustomRectangle(double x, double y, double width, double height) {
    this(width, height);
    this.setPrefSize(width, height);
    this.setLayoutX(x);
    this.setLayoutY(y);
    this.setPadding(new Insets(10, 0, 10, 0));
    this.setOnContextMenuRequested(contextMenuEvent -> {
      MenuItem deleteButton = new MenuItem("Delete");
      deleteButton.setOnAction(actionEvent -> {

      });
      ContextMenu menu = new ContextMenu();
      menu.getItems().add(deleteButton);
      menu.show(this, contextMenuEvent.getScreenX(), contextMenuEvent.getScreenY());
    });

    name.setLayoutX(0);
    name.setLayoutY(0);
    name.setAlignment(Pos.CENTER);

    double tst = 6.25;
    double lineY = 20;
    Line underName = new Line();
    underName.setStartX(0);
    underName.setStartY(20);
    underName.setEndX(width);
    underName.setEndY(20);
    underName.setFill(Color.BLACK);

    secondLine = new Line();
    secondLine.setStartX(0);
    double sLineY = lineY * 4 + (20 * (height / lineY - tst));
    secondLine.setStartY(sLineY);
    secondLine.setEndX(width);
    secondLine.setEndY(sLineY);

    addAttrButton = new Button();
    addAttrButton.setLayoutX((width) / 2 - 7);
    addAttrButton.setLayoutY(lineY * 2.75+ (20 * (height / lineY - tst)));
    addAttrButton.setOnMouseClicked(mouseEvent -> addAttribute("attribute", null));

    connector = new Connector();
    connector.setLayoutX(width);
    connector.setLayoutY(height / 2);
    connector.setRadius(5);
    connector.setParentName(this.name.getText());
    connector.setxInParent(connector.getLayoutX());
    connector.setyInParent(connector.getLayoutY());

    this.relations.put(connector, new LinkedList<>());


    this.getChildren().add(connector);
    this.getChildren().add(name);
    this.getChildren().add(underName);
    this.getChildren().add(secondLine);
    this.getChildren().add(addAttrButton);
  }

  public CustomRectangle(double width, double height) {
    setWidth(width);
    setHeight(height);
  }

  public Map<Connector, List<CustomLine>> getRelations() {
    return this.relations;
  }

  public void addConnector(Connector source, CustomLine target) {
    this.relations.get(source).add(target);
  }

  public void setConnectorPressed(EventHandler<MouseEvent> handler) {
    connector.addEventFilter(MouseEvent.MOUSE_PRESSED, handler);
  }

  public void addAttribute(String input, AttTypes type) {
    /*TextField text = new TextField(input);
    //text.setAlignment(Pos.CENTER);
    text.setPrefWidth(text.getLength() * 7);
    text.setLayoutX((this.getWidth() / 2) - (text.getLength() * 3));
    text.setLayoutY(attributeY);
    attributeY += 20;
    this.getChildren().add(text);
    this.attributes.add(text);*/
    AttributeGUI attribute = new AttributeGUI(0, attributeY, this.getWidth(), 15);
    attribute.setAttributeText(input);
    attribute.setType(type);
    attributeY += 25;
    this.getChildren().add(attribute);
    this.attributes.add(attribute);
    if (isOverlapping(attribute)) {
      this.addAttrButton.relocate(this.addAttrButton.getLayoutX(),
          this.addAttrButton.getLayoutY() + 25);
      this.setPrefSize(this.getWidth(), this.getHeight() + 25);
      this.secondLine.setStartY(this.secondLine.getStartY() + 25);
      this.secondLine.setEndY(this.secondLine.getEndY() + 25);
      this.connector.setLayoutY(this.getPrefHeight() / 2);
    }
  }

  private boolean isOverlapping(Node other) {
    return (this.addAttrButton.getLayoutY() - other.getLayoutY() < 35);
  }

  public void setName(String name) {
    this.name.setText(name);
  }

  public String getName() {
    return this.name.getText();
  }

  public Button getAddAttrButton() {
    return this.addAttrButton;
  }

  public List<AttributeGUI> getAttributes() {
    return this.attributes;
  }
}
