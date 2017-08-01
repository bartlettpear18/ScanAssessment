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
    private static Text text2;
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
        text.setTranslateY(-25);
        text.setText("Host IP address: " + output);

        text2 = new Text();
        text2.setFont(Font.font("Monospace",30));
        text2.setStyle("-fx-stroke: #EF4136");
        text2.setTextAlignment(TextAlignment.CENTER);
        text2.setTranslateY(25);
        text2.setText("Waiting for Android App...");

        StackPane layout = new StackPane();
        layout.setStyle("-fx-background-color: #1D1E18;");
        layout.getChildren().add(text);
        layout.getChildren().add(text2);
        Scene scene = new Scene(layout, 800, 175);

        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void setText2(String msg) {
        text2.setText(msg);
    }

}
