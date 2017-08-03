package com.bartlettpear18gmail.barcodetestapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import static com.bartlettpear18gmail.barcodetestapp.MainActivity.getClient;

public class Configure extends AppCompatActivity {

    private static String tag = "Debug";
    private boolean bool = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configure);

        getClient().execute();
        Log.d(tag, "Client executed");
    }

    public void scan(View view) throws InterruptedException {
        if(!bool) {
            getClient().sendReady(0);
            Log.d(tag, "Sent Ready 0");
            bool = true;
        } else {
           Log.d(tag, "Scan engine already configured");
        }

    }



}
