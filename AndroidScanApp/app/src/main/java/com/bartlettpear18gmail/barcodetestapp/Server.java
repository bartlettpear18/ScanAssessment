//package com.bartlettpear18gmail.barcodetestapp;
//
//
//import android.os.AsyncTask;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.ObjectInputStream;
//import java.io.ObjectOutput;
//import java.io.ObjectOutputStream;
//import java.io.PrintWriter;
//import java.net.Socket;
//
///**
// * Created by Joel.Bartlett18 on 6/19/2017.
// */
//public class Server extends AsyncTask<Void,Void,Void> {
//
//    private static Socket socket;
////    private static InputStreamReader inputStreamReader;
////    private static BufferedReader bufferedReader;
////    private static PrintWriter printWriter;
//    private static ObjectOutputStream objectOutputStream;
////    private static ObjectInputStream objectInputStream;
//
//    private static String ip = "192.168.3.69";
//    private static int port = 5000;
//    private Barcode tempBarcode = new Barcode("1234","sym");
//
//
//    @Override
//    protected Void doInBackground(Void ...params) {
//        try {
//            System.out.println("Beginning message");
//            connect();
//            stream();
////            printWriter.println(tempBarcode);
//            objectOutputStream.writeObject(tempBarcode);
//            System.out.println("Barcode sent");
//            close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    private void connect() throws IOException {
//        socket = new Socket(ip,port);
//    }
//
//    private void stream() throws IOException {
////        printWriter = new PrintWriter(socket.getOutputStream());
////        printWriter.flush();
////        objectInputStream = new ObjectInputStream(socket.getInputStream());
//        objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
//        objectOutputStream.flush();
//
//    }
//    private void close() throws IOException {
////        printWriter.close();
//        objectOutputStream.close();
////        objectInputStream.close();
//        socket.close();
//    }
//}