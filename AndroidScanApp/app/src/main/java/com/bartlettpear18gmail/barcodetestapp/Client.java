package com.bartlettpear18gmail.barcodetestapp;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
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
    public static String ip = "10.0.0.162";

    //Stream variables
    private ObjectInputStream inputStream = null;
    private ObjectOutputStream outputStream = null;

    //Strings
    private String ready = new String("Ready");
    private String done = new String("Done");
    private String csv = new String("CSV");
    private Integer decodeData = new Integer(0);

    //Booleans
    private boolean isReady = false;
    private boolean isDone = false;
    private boolean decode = false;
    private boolean toCSV = false;

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

    public void sendReady() throws InterruptedException {
        isReady = !isReady;
    }
    public void sendDone() throws InterruptedException {
        isDone = !isDone;
    }

    public void sendCSV() throws InterruptedIOException {
        toCSV = !toCSV;
    }
    public void sendDecode(int data) throws InterruptedException {
        decode = !decode;
        decodeData = data;
        Log.d(tag, String.valueOf(decodeData));
        decode = !decode;
    }


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
    public String getIp() { return ip; }

    private void setup() throws IOException {
        socket = new Socket(ip, port);
        Log.d(tag, "Created socket");
    }
    private void streams() throws IOException {
        outputStream = new ObjectOutputStream(socket.getOutputStream());
        inputStream = new ObjectInputStream(socket.getInputStream());
        Log.d(tag, "Streams created");

    }
    public void close() throws IOException {
        outputStream.flush();
        outputStream.close();
        inputStream.close();
    }

    public void runClient() throws ClassNotFoundException, IOException, InterruptedException {

        setup();
        streams();

        while(true) {
            if(inputStream.available() != 0) {
                Object temp = inputStream.readObject();
                Log.d(tag, temp.toString());
            }

            if(isReady) { outputStream.writeObject(ready); sendReady(); }
            if(isDone) { outputStream.writeObject(done); sendDone(); }
            if(decode) { outputStream.writeObject(decodeData);}
            if(toCSV) { outputStream.writeObject(csv); sendCSV(); }
        }
    }
}
