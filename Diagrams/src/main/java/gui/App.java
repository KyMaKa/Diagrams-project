package gui;

import gui.controllers.DragController;
import gui.controllers.EntityController;
import java.io.FileWriter;
import java.io.Writer;
import java.util.Objects;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import com.google.gson.Gson;

import java.io.IOException;
import logic.Entity.Entity;

public class App extends Application {

  public static Group group = new Group();

  @Override
  public void start(Stage stage) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/gui/hello-view.fxml"));
    //fxmlLoader.load();
    Scene scene = new Scene(fxmlLoader.load());
    scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("style.css")).toString());
    stage.setTitle("UML diagrams");
    stage.setScene(scene);
    stage.show();
  }

  public static void main(String[] args) throws IOException {
    launch();
  }
}