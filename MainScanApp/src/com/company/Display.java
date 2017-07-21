package com.company;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Display extends Application {

    private Text text;
    private String ip_address;
    private String output = "";

    @Override
    public void start(Stage primaryStage) throws UnknownHostException {

        ip_address = String.valueOf(InetAddress.getLocalHost());
        for (int i= 0; i < ip_address.length(); i++) {
            if(Character.isDigit(ip_address.charAt(i)) || Character.toString(ip_address.charAt(i)).equals(".")) {
                output += ip_address.charAt(i);
            }
        }

        //Create Window
        primaryStage.setTitle("Scan Diagnostic");
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("icon.png")));

        text = new Text();
        text.setFont(Font.font("Monospace",30));
        text.setStyle("-fx-stroke: #EF4136");
        text.setTextAlignment(TextAlignment.CENTER);
        text.setText("Host IP address: " + output);

        StackPane layout = new StackPane();
        layout.setStyle("-fx-background-color: #1D1E18;");
        layout.getChildren().add(text);
        Scene scene = new Scene(layout, 600, 150);

        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
