package com.company;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import static com.company.Display.setText2;
import static com.company.Main.driverServer;
import static com.company.Main.getDriverServer;
import static com.company.Main.getMed;

//        Application.launch(Display.class,args);

public class AndroidServer implements Runnable{


    public Thread thread = null;

    //AndroidServer variables
    private static ServerSocket server;
    private static Socket socket;
    private static int port = 5000;

    //Stream variables
    private ObjectInputStream inputStream = null;
    private ObjectOutputStream outputStream = null;

    private void streams() throws IOException {
        outputStream = new ObjectOutputStream(socket.getOutputStream());
        inputStream = new ObjectInputStream(socket.getInputStream());
        System.out.println("Streams made");
    }
    private void setup() throws IOException {
        server = new ServerSocket(port);
        socket = server.accept();
        System.out.println("AndroidServer connected to Socket");
    }

    private String checkStream() throws IOException, ClassNotFoundException {
        String tmp = (String) inputStream.readObject();
        return tmp;
    }

    private void sendToAndroid(String msg) throws IOException {
        String temp = new String(msg);
        outputStream.writeObject(temp);
        System.out.println("Sending: " + msg);
    }
    private void close() throws IOException {
        inputStream.close();
        outputStream.flush();
        outputStream.close();
        socket.close();
        System.out.println("Android streams closed");
    }

    @Override
    public void run() {
        try {
            setup();
            streams();
            setText2("Android connected");
            int i = 0;
            while(i <= 9) {
                String tmp = checkStream();
                if(tmp != null) {
                    setText2("Scanning...");
                    getMed().changeIsReady();
                    if(!tmp.equals("0")) {
                        getMed().addCodes(tmp);
                        i++;
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void start(String name) {
        if(thread == null) {
            thread = new Thread(this, name);
            thread.setDaemon(true);
            thread.start();
            System.out.println("Starting " + thread.getName());
        }
    }
}
