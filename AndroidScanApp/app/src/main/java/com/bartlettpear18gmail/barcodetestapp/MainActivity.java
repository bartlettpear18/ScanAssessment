package com.bartlettpear18gmail.barcodetestapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    public String tag = "Debug";
    public static Client client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        client = new Client();
        client.execute();

    }


    public static Client getClient() { return client; }

    public void linearBarcodes(View view) {
        Intent intent = new Intent(this, LinearBarcodes.class);
        startActivity(intent);
        Log.d(tag, "Starting Linear Barcodes");
    }

    public void matrixBarcodes(View view) {
        Intent intent = new Intent(this, MatrixBarcodes.class);
        startActivity(intent);
        Log.d(tag, "Starting Matrix Barcodes");
    }

    public void mixBarcodes(View view) {
        Intent intent = new Intent(this, MixBarcodes.class);
        startActivity(intent);
        Log.d(tag, "Starting Mixed Barcodes");
    }

    public void setIP(View view) {
        Intent intent = new Intent(this, NetworkSetup.class);
        startActivity(intent);
        Log.d(tag, "Starting IP setup");
    }

    public void testConnection(View view) {
        System.out.println(client.getIp());
        Intent intent = new Intent(this, Results.class);
        startActivity(intent);
        Log.d(tag, "Testing TCP");
    }
}
