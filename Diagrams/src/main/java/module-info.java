module gui {
  requires javafx.controls;
  requires javafx.fxml;
  requires javafx.web;

  requires org.controlsfx.controls;
  requires com.dlsc.formsfx;
  requires validatorfx;
  requires org.kordamp.ikonli.javafx;
  requires org.kordamp.bootstrapfx.core;
  requires eu.hansolo.tilesfx;
  requires com.almasb.fxgl.all;
  requires com.google.gson;

  opens gui to javafx.fxml;
  exports gui;
  exports gui.controllers;
  opens gui.controllers to javafx.fxml;
  opens logic.annotations to com.google.gson;
  opens logic.types to com.google.gson;
  opens logic.objects to com.google.gson;
  opens gui.shapes to com.google.gson;
}