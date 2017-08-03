package com.company;
/**
 * Created by Joel.Bartlett18 on 6/29/2017.
 */

import javafx.application.Application;

import java.io.File;
import java.io.IOException;


public class Main {

    static Mediator med;
    static DriverServer driverServer;
    public static String loc = System.getProperty("user.dir") + "\\";

    public static void main(String[] args) throws IOException {

        med = new Mediator();

        AndroidServer androidServer = new AndroidServer();
        androidServer.start("Android Thread");

        driverServer = new DriverServer();
        driverServer.start("Driver Thread");

        Runtime.getRuntime().exec(loc + "Driver.exe", null, new File(loc));

        Application.launch(Display.class,args);

    }

    public static Mediator getMed() { return med; }

    public static DriverServer getDriverServer() { return driverServer; }
}