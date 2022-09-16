package gui;

import java.util.Objects;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

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

  public static void main(String[] args) {
    launch();
  }
}