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

    private static Text avgRuntime;
    private static Text percentSucces;
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
        text.setTranslateY(-75);
        text.setText("Host IP address: " + output);

        text2 = new Text();
        text2.setFont(Font.font("Monospace",30));
        text2.setStyle("-fx-stroke: #EF4136");
        text2.setTextAlignment(TextAlignment.CENTER);
        text2.setTranslateY(-25);
        text2.setText("Waiting for Android App...");

        avgRuntime = new Text();
        avgRuntime.setFont(Font.font("Monospace",30));
        avgRuntime.setStyle("-fx-stroke: #EF4136");
        avgRuntime.setTextAlignment(TextAlignment.CENTER);
        avgRuntime.setTranslateY(25);
        avgRuntime.setText("Average Runtime: ");

        percentSucces = new Text();
        percentSucces.setFont(Font.font("Monospace",30));
        percentSucces.setStyle("-fx-stroke: #EF4136");
        percentSucces.setTextAlignment(TextAlignment.CENTER);
        percentSucces.setTranslateY(75);
        percentSucces.setText("Percent Success: ");


        StackPane layout = new StackPane();
        layout.setStyle("-fx-background-color: #1D1E18;");
        layout.getChildren().add(text);
        layout.getChildren().add(text2);
        layout.getChildren().add(percentSucces);
        layout.getChildren().add(avgRuntime);
        Scene scene = new Scene(layout, 1000, 300);

        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void setText2(String msg) {
        text2.setText(msg);
    }
    public static void setPercentSucces(String msg) { percentSucces.setText(msg);}
    public static void setAvgRuntime(String msg) { avgRuntime.setText(msg); }

}
