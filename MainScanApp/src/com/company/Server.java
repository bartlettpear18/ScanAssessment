package com.company;

//import scan.Barcode;
//import scan_classes;

//import com.company.Barcode;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;




public class Server implements Runnable{


    Thread serverThread = null;

    //Server variables
    private static ServerSocket server;
    private static Socket socket;
    private static int port = 5000;

    //Stream variables
    private ObjectInputStream inputStream = null;
    private ObjectOutputStream outputStream = null;

    @Override
    public void run() {
        try {
            server = new ServerSocket(port);
            System.out.println("Server created");

            socket = server.accept();
            System.out.println("Socket connected");

            outputStream = new ObjectOutputStream(socket.getOutputStream());
            inputStream = new ObjectInputStream(socket.getInputStream());
            System.out.println("Streams created");

            do {
                System.out.println("Reading input data");
                String barcode = null;
                Object temp = inputStream.readObject();
                System.out.println("Object received");
                if (temp instanceof String) {
                    barcode = (String) temp;
                    System.out.println("temp is barcode");
                    System.out.println(temp);
                }
            } while (inputStream.available() != 0);

            socket.close();

        } catch (IOException e) {
            //e.printStackTrace();
                System.out.println("IO Exception");
        } catch (ClassNotFoundException e) {
            //e.printStackTrace();
            System.out.println("Class not found exception");
        }
    }

    public void start() {
        if(serverThread == null) {
            serverThread = new Thread(this, "Server Thread");
            serverThread.start();
        }
    }
}
