package com.company;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;




public class Server implements Runnable{


    Thread serverThread = null;
    public String currentString;
    public int currentInt;
    public int decodeData;

    //Server variables
    private static ServerSocket server;
    private static Socket socket;
    private static int port = 5000;

    //Stream variables
    private ObjectInputStream inputStream = null;
    private ObjectOutputStream outputStream = null;

    public String getCurrentString() { return currentString; }
    public int getCurrentInt() { return currentInt; }

    private void streams() throws IOException {
        outputStream = new ObjectOutputStream(socket.getOutputStream());
        inputStream = new ObjectInputStream(socket.getInputStream());
        System.out.println("Streams made");
    }

    private void close() throws IOException {
        inputStream.close();
        outputStream.flush();
        outputStream.close();
        socket.close();
        System.out.println("Streams closed");
    }

    private void setup() throws IOException {
        server = new ServerSocket(port);
        socket = server.accept();
        System.out.println("Server connected to Socket");
    }

    @Override
    public void run() {
        try {
            setup();
            streams();

            while(true) {
                Object temp = inputStream.readObject();

                if(temp instanceof String) {
                    currentString = (String) temp;
                    System.out.println(currentString);
                } else if (temp instanceof Integer) {
                    currentInt = (int) temp;
                    System.out.println(currentInt);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }


    public void start() {
        if(serverThread == null) {
            serverThread = new Thread(this, "Server Thread");
            serverThread.start();
        }
    }
}
