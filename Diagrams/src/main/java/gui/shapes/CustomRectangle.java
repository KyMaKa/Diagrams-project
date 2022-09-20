package gui.shapes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import java.util.Map;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import logic.types.Annotations;
import logic.types.AttTypes;

public class CustomRectangle extends Pane {

  private Button addAttrButton;

  private final TextField name = new TextField("Entity");

  private int attributeY = 25;
  private Line secondLine;

  private Connector connectorL;

  private Connector connectorR;

  private Connector connectorT;

  private Connector connectorB;

  private final List<Connector> connectors = new LinkedList<>();

  private final List<AttributeGUI> attributes = new LinkedList<>();

  private final Map<Connector, List<CustomLine>> relations = new HashMap<>();

  private final List<CustomLine> parents = new LinkedList<>();

  public CustomRectangle(double x, double y, double width, double height) {
    this(width, height);
    this.setPrefSize(width, height);
    this.setLayoutX(x);
    this.setLayoutY(y);
    this.setPadding(new Insets(10, 0, 10, 0));
    this.setOnMouseClicked(mouseEvent -> {
      this.requestFocus();
    });

    name.setLayoutX(0);
    name.setLayoutY(0);
    name.setPrefWidth(width);
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
    addAttrButton.setOnMouseClicked(mouseEvent -> addAttribute("attribute", null, null));

    connectorL = new Connector();
    connectorL.setLayoutX(width);
    connectorL.setLayoutY(height / 2);
    connectorL.setRadius(5);
    connectorL.setParentName(this.name.getText());
    connectorL.setxInParent(connectorL.getLayoutX());
    connectorL.setyInParent(connectorL.getLayoutY());

    connectorR = new Connector();
    connectorR.setLayoutX(0);
    connectorR.setLayoutY(height / 2);
    connectorR.setRadius(5);
    connectorR.setParentName(this.name.getText());
    connectorR.setxInParent(connectorR.getLayoutX());
    connectorR.setyInParent(connectorR.getLayoutY());

    connectorB = new Connector();
    connectorB.setLayoutX(width / 2);
    connectorB.setLayoutY(height);
    connectorB.setRadius(5);
    connectorB.setParentName(this.name.getText());
    connectorB.setxInParent(connectorB.getLayoutX());
    connectorB.setyInParent(connectorB.getLayoutY());

    connectorT = new Connector();
    connectorT.setLayoutX(width / 2);
    connectorT.setLayoutY(0);
    connectorT.setRadius(5);
    connectorT.setParentName(this.name.getText());
    connectorT.setxInParent(connectorT.getLayoutX());
    connectorT.setyInParent(connectorT.getLayoutY());

    connectors.add(connectorB);
    connectors.add(connectorR);
    connectors.add(connectorT);
    connectors.add(connectorL);

    this.relations.put(connectorL, new LinkedList<>());
    this.relations.put(connectorB, new LinkedList<>());
    this.relations.put(connectorR, new LinkedList<>());
    this.relations.put(connectorT, new LinkedList<>());


    this.getChildren().addAll(connectorL, connectorB, connectorR, connectorT);
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

  public List<CustomLine> getParents() {
    return this.parents;
  }

  public void addParent(CustomLine target) {
    this.parents.add(target);
  }

  public void addConnector(Connector source, CustomLine target) {
    this.relations.get(source).add(target);
  }

  public void setDeleteEvent(EventHandler<KeyEvent> handler) {
    this.setOnKeyPressed(handler);
  }

  public void setConnectorPressed(EventHandler<MouseEvent> handler) {
    this.connectors.forEach(connector -> connector.addEventFilter(MouseEvent.MOUSE_PRESSED, handler));
  }

  public void addAttribute(String input, AttTypes type, Annotations annotation) {
    AttributeGUI attribute = new AttributeGUI(0, attributeY, this.getWidth(), 15);
    attribute.setAttributeText(input);
    attribute.setType(type);
    attribute.setAnnotation(annotation);
    attributeY += 25;
    this.getChildren().add(attribute);
    this.attributes.add(attribute);
    if (isOverlapping(attribute)) {
      this.addAttrButton.relocate(this.addAttrButton.getLayoutX(),
          this.addAttrButton.getLayoutY() + 25);
      this.setPrefSize(this.getWidth(), this.getHeight() + 25);
      this.secondLine.setStartY(this.secondLine.getStartY() + 25);
      this.secondLine.setEndY(this.secondLine.getEndY() + 25);
      this.connectorL.setLayoutY(this.getPrefHeight() / 2);
      this.connectorR.setLayoutY(this.getPrefHeight() / 2);
      this.connectorB.setLayoutY(this.getPrefHeight());
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
