package com.company;/**
 * Created by Joel.Bartlett18 on 6/29/2017.
 */

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Display extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Scan Engine Diagnostic");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        //Start Button
        Button btn = new Button();
        btn.setText("Run Test");
        btn.getStyleClass().add("button");

        btn.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                btn.setStyle("-fx-background-color: #EF4136; -fx-text-fill: #FFFFFF;");
            }
        });
        btn.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                btn.setStyle("-fx-background-color:transparent;");
            }
        });
        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("Running Test");
            }
        });

        //Model Name Input
        TextField textField = new TextField();
        grid.add(textField,1,1);

        grid.add(btn,1,2);
        StackPane root = new StackPane();
        //root.getChildren().add(btn);

        Scene scene = new Scene(grid, 300, 275);
        primaryStage.setScene(scene);

        scene.getStylesheets().clear();
        scene.getStylesheets().add(Main.class.getResource("Style.css").toExternalForm());
        primaryStage.show();
    }
}
