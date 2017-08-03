package com.bartlettpear18gmail.barcodetestapp;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Array;
import java.net.Socket;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Client extends AsyncTask<Void, Void, Void> {

    private static String tag = "Debug";

    private static final String IP_PATTERN =
            "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
            "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
            "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
            "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";

    //Server variables
    private static Socket socket;
    private static int port = 5000;
    public static String ip = "192.168.43.81";

    //Stream variables
    private ObjectInputStream inputStream = null;
    private ObjectOutputStream outputStream = null;

    private boolean isReady = false;
    private boolean toCSV = false;

    //Stupid cheating
    private String currentCode = new String("");

    //Results
    public static ArrayList<String> results = new ArrayList<>();

    public Client() {}

    @Override
    protected Void doInBackground(Void... voids) {

        try {
            runClient();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void sendReady(int current) throws InterruptedException {
        currentCode = String.valueOf(current);
        isReady = !isReady;

    }
    public void sendCSV() throws InterruptedIOException { toCSV = !toCSV; }

    private static boolean checkIp(String text) {
        Pattern p = Pattern.compile(IP_PATTERN);
        Matcher m = p.matcher(text);
        return m.find();
    }
    public static boolean setAddress(String newIp) {
        boolean change = false;
        if(checkIp(newIp)) {
            ip = newIp;
            change = true;
            Log.d(tag, "IP Confirmed and set");
        } else {
            Log.d(tag, "IP Submission rejected");
            change = false;
        }
        return change;
    }
    public ArrayList<String> getResults() { return results; }

    private void setup() throws IOException {
        socket = new Socket(ip, port);
        Log.d(tag, "Created socket");
    }

    private void streams() throws IOException {
        outputStream = new ObjectOutputStream(socket.getOutputStream());
        inputStream = new ObjectInputStream(socket.getInputStream());
        Log.d(tag, "Streams created");

    }

    private String receiving() throws IOException, ClassNotFoundException, InterruptedException {
        String temp = (String) inputStream.readObject();
        return temp;
    }

    public void close() throws IOException {
        outputStream.flush();
        outputStream.close();
        inputStream.close();
    }

    public void runClient() throws ClassNotFoundException, IOException, InterruptedException {

        setup();
        streams();

        while (true) {
            if (isReady) {
                outputStream.writeObject(currentCode);
                isReady = false;
                outputStream.flush();
            }
        }
    }
}
