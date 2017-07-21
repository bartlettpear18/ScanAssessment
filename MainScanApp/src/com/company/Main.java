package com.company;
/**
 * Created by Joel.Bartlett18 on 6/29/2017.
 */

import javafx.application.Application;

import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        //Application.launch(Display.class,args);

        Server server = new Server();
        server.start();



    }
}