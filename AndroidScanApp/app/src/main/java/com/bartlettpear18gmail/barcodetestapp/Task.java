package com.bartlettpear18gmail.barcodetestapp;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by Joel.Bartlett18 on 6/19/2017.
 */
public class Task extends AsyncTask<Void,Void,Void> {

    private static Socket socket;
    private static InputStreamReader inputStreamReader;
    private static BufferedReader bufferedReader;
    private static PrintWriter printWriter;


    private static String ip = "10.0.0.162";
    private static int port = 5000;

    public static String message = "App Test";

    @Override
    protected Void doInBackground(Void ...params) {
        while(true) {
            try {
                connect();
                stream();
                sendString("Hello");
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    private void connect() throws IOException {
        socket = new Socket(ip,port);
    }

    private void stream() throws IOException {
        printWriter = new PrintWriter(socket.getOutputStream());
        printWriter.flush();


        inputStreamReader = new InputStreamReader(socket.getInputStream());
        bufferedReader = new BufferedReader(inputStreamReader);
        message = bufferedReader.readLine();
        System.out.println(message);



    }

    public static void sendInt(int msg) {
        printWriter.print(msg);
    }

    public static void sendString(String msg) {
        printWriter.write(msg);
    }

    private void close() throws IOException {
        printWriter.close();
        inputStreamReader.close();
        bufferedReader.close();
        socket.close();

    }
}

