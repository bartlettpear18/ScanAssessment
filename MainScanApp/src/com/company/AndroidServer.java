package com.company;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import static com.company.Main.getMed;

//        Application.launch(Display.class,args);

public class AndroidServer implements Runnable{


    public Thread thread = null;
    public int decodeData;

    //AndroidServer variables
    private static ServerSocket server;
    private static Socket socket;
    private static int port = 5000;

    //Stream variables
    private ObjectInputStream inputStream = null;
    private ObjectOutputStream outputStream = null;

    //Strings
    private String scanComplete = new String("Scan complete");

    //Results data
    public static ArrayList<String> resultsData = new ArrayList<>();

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

    private String receiveFromAndroid() throws IOException, ClassNotFoundException, InterruptedException {
        String temp = (String) inputStream.readObject();
        return temp;
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
        System.out.println("Streams closed");
    }

    @Override
    public void run() {
        try {
            setup();
            streams();

            decodeData = Integer.parseInt(receiveFromAndroid());
            System.out.println(decodeData);

            while(true) {

                if(receiveFromAndroid().equals("Ready")) {
                    getMed().changeIsReady();
                    System.out.println("Received ready, prepping to send to Driver");

//                    while(!getMed().getIsScanComplete()) {}
//
//                    if(getMed().getIsScanComplete()) {
//                        System.out.println("Sending message to Android");
//                        sendToAndroid(scanComplete);
//                        outputStream.flush();
//                        System.out.println("Scan complete. Sending to Android");
//                        getMed().changeIsScanComplete();
//                        System.out.println("Done");
//                    }

                }

//                if(receiveFromAndroid().equals("Done")) {
//                    for (String data : resultsData) {
//                        sendToAndroid(data);
//                    }
//                }


                if(inputStream.available() != 0) {
                    System.out.println(receiveFromAndroid());
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void start(String name) {
        if(thread == null) {
            thread = new Thread(this, name);
            thread.start();
            System.out.println("Starting " + thread.getName());
        }
    }
}
