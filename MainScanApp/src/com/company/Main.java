package com.company;
/**
 * Created by Joel.Bartlett18 on 6/29/2017.
 */

import javafx.application.Application;
import java.io.IOException;


public class Main {

    static Mediator med;
    public static void main(String[] args) throws IOException {

        med = new Mediator();



        AndroidServer androidServer = new AndroidServer();
        androidServer.start("Android Thread");

        DriverServer driverServer = new DriverServer();
        driverServer.start("Driver Thread");

        Application.launch(Display.class,args);

    }

    public static Mediator getMed() { return med; }
}