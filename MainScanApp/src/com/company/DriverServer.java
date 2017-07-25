package com.company;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import static com.company.Main.getMed;
import static com.company.Mediator.ready;

public class DriverServer implements Runnable{


    //Setup Objects
    Thread thread = null;
    private String received;

    //Driver Server Objects
    private static ServerSocket server;
    private static Socket socket;
    private static int port = 8000;

    //Stream Objects
    private InputStream inputStream;
    private OutputStream outputStream;


    private void setup() throws IOException {
        server = new ServerSocket(port);
        socket = server.accept();
        System.out.println("Driver Server connected to Socket");
    }

    private void streams() throws IOException {
        outputStream = socket.getOutputStream();
        inputStream = socket.getInputStream();
        System.out.println("Streams made");
    }

    private String receiveFromDriver() throws IOException {
        byte[] lenBytes = new byte[4];
        inputStream.read(lenBytes, 0, 4);
        int len = (((lenBytes[3] & 0xff) << 24) | ((lenBytes[2] & 0xff) << 16) |
                ((lenBytes[1] & 0xff) << 8) | (lenBytes[0] & 0xff));
        byte[] receivedBytes = new byte[len];
        inputStream.read(receivedBytes, 0, len);
        received = new String(receivedBytes, 0, len);
        return received;
    }

    private void sendToDriver(String msg) throws IOException {
        String toSend = msg;
        byte[] toSendBytes = toSend.getBytes();
        int toSendLen = toSendBytes.length;
        byte[] toSendLenBytes = new byte[4];
        toSendLenBytes[0] = (byte)(toSendLen & 0xff);
        toSendLenBytes[1] = (byte)((toSendLen >> 8) & 0xff);
        toSendLenBytes[2] = (byte)((toSendLen >> 16) & 0xff);
        toSendLenBytes[3] = (byte)((toSendLen >> 24) & 0xff);
        outputStream.write(toSendLenBytes);
        outputStream.write(toSendBytes);
        System.out.println("Sent to Driver: " + msg);
    }

    private void close() throws IOException {
        socket.close();
        server.close();
    }

    @Override
    public void run() {
        try {
            setup();
            streams();

            while(true) {
                if(getMed().getIsReady()) {
                    System.out.println("Telling driver ready");
                    sendToDriver(ready);
                    getMed().changeIsReady();
                    System.out.println("Message sent");

                    while(inputStream.available() == 0) {}

                    if(receiveFromDriver().equals("Scan complete")){
                        getMed().changeIsScanComplete();
                        System.out.println(getMed().getIsScanComplete());
                        System.out.println("Received from driver that Scan completed. Changed bool");
                    }
                }

                if(inputStream.available() != 0) {
                    System.out.println(receiveFromDriver());
                }

            }
        }
        catch (IOException e) {
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