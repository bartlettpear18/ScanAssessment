package com.bartlettpear18gmail.barcodetestapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;

import java.util.ArrayList;

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
            gen = new Generator();
            gen.make1D();

            image = (ImageView) findViewById(R.id.imageView2);
            currentSym = (TextView) findViewById(R.id.currentSym);
            scanStatus = (TextView) findViewById(R.id.scanStatus);

            getClient().sendDecode(gen.getDecodeData());

        } catch (WriterException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {

        }

    }

    public void start(View view) throws WriterException, InterruptedException {

        if (i < gen.getOneDMaps().size()-1) {
            image = (ImageView) findViewById(R.id.imageView2);
            image.setImageBitmap(gen.getOneDMaps().get(i));
            getClient().sendReady();

            String sym = "Current Symbology: " + gen.getFormats().get(i);
            currentSym.setText(sym);
            scanStatus.setText("Scanning...");
            i++;

        } else {
            Intent intent = new Intent(this, Results.class);
            startActivity(intent);
            getClient().sendDone();
        }

    }


}
