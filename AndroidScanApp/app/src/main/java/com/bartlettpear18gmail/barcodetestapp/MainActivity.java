package com.bartlettpear18gmail.barcodetestapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


//    Button button;
//    ImageView image;
////    Setup setup;
////    ArrayList<AndroidBarcode> temp;
//    RelativeLayout rl;
//
//    private int i =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//
//        setup = Setup.getInstance();
//        temp = setup.randomize();

    }

//    private void runThread() {
//        new Thread() {
//            public void run() {
//                runOnUiThread(new Runnable() {
//                    public void run() {
//                        for (AndroidBarcode obj : temp) {
//                            image.setImageResource(obj.getImageLoc());
//
//                            try {
//                                Thread.sleep(1000);
//                                System.out.println("waiting 1 second");
//                            } catch (InterruptedException e) {
//                                e.printStackTrace();
//                            }
//                        }
//                    }
//                });
//            }
//        }.start();
//    }

    public void barcode(View view) {
        Intent intent = new Intent(this, LinearBarcodes.class);
        startActivity(intent);
    }

    public void testConnection(View view) {
        Client client = new Client();
        client.start();
    }

//    public void setImage(View view) {
//
////        System.out.println("Button pressed");
////        new AsyncRun().execute();
////        runThread();
////
////        if(i<temp.size()-1){
////            image.setImageResource(temp.get(i).getImageLoc());
////            i++;
////        }
////
////        /*
////
////        Server server = new Server();
////        server.execute();
////        System.out.println("button pressed");
////        */
////
////        Client client = new Client();
////        client.start();
////
//
//    }
//
//
////    public class AsyncRun extends AsyncTask<Void, Void, Void> {
////
////        @Override
////        protected Void doInBackground(Void... voids) {
////            return null;
////        }
////
////
////        @Override
////        protected void onPostExecute(Void result) {
////            for (AndroidBarcode obj : temp) {
////                image.setImageResource(obj.getImageLoc());
////
////                try {
////                    Thread.sleep(1000);
////                    System.out.println("waiting 1 second");
////                } catch (InterruptedException e) {
////                    e.printStackTrace();
////                }
////            }
////        }
////    }
//
//
}
