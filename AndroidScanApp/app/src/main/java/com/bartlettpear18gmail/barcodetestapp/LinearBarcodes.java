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
import static com.bartlettpear18gmail.barcodetestapp.Client.scanned;

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
            getClient().sendCode(gen.getDecodeData());
            Log.d(tag, "Code made");


            image = (ImageView) findViewById(R.id.imageView2);
            currentSym = (TextView) findViewById(R.id.currentSym);
            scanStatus = (TextView) findViewById(R.id.scanStatus);

        } catch (WriterException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void next(View view) throws WriterException, InterruptedException, IOException {

        Log.d(tag, "Starting loop");
        String sym;
        String status = "Scanning...";
        scanStatus.setText(status);
        Log.d(tag, String.valueOf(gen.getOneDMaps().size()));


        if (i < gen.getOneDMaps().size()) {
            image.setImageBitmap(gen.getOneDMaps().get(i));
            Log.d(tag, "Set image");

            sym = "Current Symbology: " + gen.getFormats().get(i);
            currentSym.setText(sym + " " + i+1);

            //Tell main barcode is displayed
            getClient().sendReady();
            Log.d(tag, "Sending ready");

            i++;

        } else {
            //Move on to Results Activity after finishing test
            Intent intent = new Intent(this, Results.class);
            startActivity(intent);
        }

    }

}
