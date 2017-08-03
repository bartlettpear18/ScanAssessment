package com.bartlettpear18gmail.barcodetestapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.zxing.WriterException;

import java.io.IOException;

import static com.bartlettpear18gmail.barcodetestapp.MainActivity.getClient;

public class LinearBarcodes extends AppCompatActivity {

    private ImageView image;
    private TextView currentSym;
    private TextView scanStatus;

    private Generator gen = null;
    private int i = 0;
    private String tag = "Debug";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linear_barcodes);
        Log.d(tag, "Starting 1D Activity");

        try {
            getClient().execute();
            gen = new Generator();
            gen.make1D();


            image = (ImageView) findViewById(R.id.imageView2);
            currentSym = (TextView) findViewById(R.id.currentSym);
            scanStatus = (TextView) findViewById(R.id.scanStatus);

        } catch (WriterException e) {
            e.printStackTrace();
        }

    }

    public void next(View view) throws WriterException, InterruptedException, IOException {

        String sym;
        String status = "Scanning...";
        scanStatus.setText(status);


        if (i < gen.getOneDMaps().size()) {
            image.setImageBitmap(gen.getOneDMaps().get(i));

            sym = "Current Symbology: " + gen.getFormats().get(i);
            currentSym.setText(sym + " " + (i+1));

            //Tell main barcode is displayed
            getClient().sendReady(gen.getCodes().get(i));
            Log.d(tag, "Sending ready");

            i++;

        }

    }

}
