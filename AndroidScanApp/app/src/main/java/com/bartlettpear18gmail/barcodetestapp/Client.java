package com.bartlettpear18gmail.barcodetestapp;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import com.company.Barcode;

public class Client implements Runnable{

    Thread socketThread = null;

    //Server variables
    private static Socket socket;
    private static int port = 5000;
    private static String ip = "10.0.0.162";

    //Stream variables
    private ObjectInputStream inputStream = null;
    private ObjectOutputStream outputStream = null;

    @Override
    public void run() {
        try {

            socket = new Socket(ip, port);
            System.out.println("Socket connected");

            outputStream = new ObjectOutputStream(socket.getOutputStream());
            inputStream = new ObjectInputStream(socket.getInputStream());
            System.out.println("Streams created");


            String barcode  = new String("1234 symbology");
            outputStream.writeObject(barcode);
            System.out.println("Object sent");


            outputStream.flush();
            outputStream.close();
            inputStream.close();

        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("IO Exception");
        }


    }

    public void start() {
        if(socketThread == null) {
            System.out.println("Making new thread");
            socketThread = new Thread(this, "Server Thread");
            socketThread.start();
            System.out.println("Starting thread");
        }
    }
}
