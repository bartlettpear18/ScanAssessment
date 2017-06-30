package com.company;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable{

    //Server variables
    private static ServerSocket server;
    private static Socket connection;
    private static int port = 5000;

    //Stream variables
    private static InputStreamReader inputStreamReader;
    private static OutputStreamWriter outputStreamWriter;
    private static PrintWriter printWriter;
    private static BufferedReader bufferedReader;
    private static BufferedWriter bufferedWriter;

    //testing string
    private static String message;

    /**
     * Create input stream
     * @throws IOException
     */
    private static void stream() throws IOException {
        //printWriter = new PrintWriter(connection.getOutputStream());
        outputStreamWriter = new OutputStreamWriter(connection.getOutputStream());
        bufferedWriter = new BufferedWriter(outputStreamWriter);

        inputStreamReader = new InputStreamReader(connection.getInputStream());
        bufferedReader = new BufferedReader(inputStreamReader);

        message = bufferedReader.readLine();
        System.out.println(message);

        bufferedWriter.flush();
    }

    /**
     * close stream and connection
     * @throws IOException
     */
    private static void close() throws IOException {
        inputStreamReader.close();
        outputStreamWriter.close();
        //printWriter.close();
        bufferedReader.close();
        bufferedWriter.close();
        server.close();
        connection.close();
    }

    /**
     * send commands
     */
    private void sendCommand(String cmd) throws IOException {
        bufferedWriter.write(cmd);
        System.out.println("Message sent " + cmd);
    }

    @Override
    public void run() {
        while (true) {
            try {
                server = new ServerSocket(port);
                connection = server.accept();

                stream();
                sendCommand("hello");

                System.out.println(message);
                close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

//(new Thread(new Server())).start();

