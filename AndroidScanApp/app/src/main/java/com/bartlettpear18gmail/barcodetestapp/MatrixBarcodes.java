package com.bartlettpear18gmail.barcodetestapp;

import android.content.Intent;
   import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.zxing.WriterException;

import java.io.IOException;

import static com.bartlettpear18gmail.barcodetestapp.MainActivity.getClient;

public class MatrixBarcodes extends AppCompatActivity {

    private ImageView image;
    private TextView currentSym;
    private TextView scanStatus;

    private Generator gen = null;
    private int i = 0;
    private String tag = "Debug";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matrix_barcodes);
        Log.d(tag, "Starting 2D Activity");

        try {
            getClient().execute();
            gen = new Generator();
            gen.make2D();
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

    public void start(View view) throws WriterException, InterruptedException, IOException {

        Log.d(tag, "Starting loop");
        String sym;
        String status = "Scanning...";
        scanStatus.setText(status);


        if (i < gen.getTwoDMaps().size()-1) {
            image.setImageBitmap(gen.getTwoDMaps().get(i));
            Log.d(tag, "Set image");

            sym = "Current Symbology: " + gen.getFormats().get(i);
            currentSym.setText(sym + " " + i);

            //Tell main barcode is displayed
            getClient().sendReady();
            Log.d(tag, "Sending ready");


            i++;
        } else {
            Intent intent = new Intent(this, Results.class);
            startActivity(intent);
        }

    }}
