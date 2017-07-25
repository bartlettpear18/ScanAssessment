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
            gen = new Generator();
            gen.make1D();

            image = (ImageView) findViewById(R.id.imageView2);
            currentSym = (TextView) findViewById(R.id.currentSym);
            scanStatus = (TextView) findViewById(R.id.scanStatus);

        } catch (WriterException e) {
            e.printStackTrace();
        }

    }

    public void start(View view) throws WriterException, InterruptedException {

        //Tell Main the expected decode data
//        getClient().sendCode(gen.getDecodeData());

        String sym;
        String status = "Scanning...";
        scanStatus.setText(status);

        /*
        for(Bitmap map : gen.getOneDMaps()) {
            //Change image
            image.setImageBitmap(map);
            Log.d(tag, "Image set");

            //Update Current Symbology text field
            sym = "Current Symbology: " + gen.getFormats().get(formatCounter);
            currentSym.setText(sym);
            formatCounter++;

            //Tell main barcode is displayed
            getClient().sendReady();
            Log.d(tag, "Ready sent to Main. Waiting now.");

            //Wait for scan to complete
            while(!scanned) { }

            Log.d(tag, "Finished waiting");
            //Update Status after scan
            scanStatus.setText("Scan Complete");
            wait(500);
            Log.d(tag, "Scan complete");
        }
        */
        /*
        if (i < gen.getOneDMaps().size()-1) {
            image.setImageBitmap(gen.getOneDMaps().get(i));

            sym = "Current Symbology: " + gen.getFormats().get(i);
            currentSym.setText(sym);

            //Tell main barcode is displayed
            getClient().sendReady();

            //Wait for scan to complete
            while(!scanned) { }

            //Update Status after scan
            scanStatus.setText("Scan Complete");
            wait(500);
            Log.d(tag, "Scan complete");
            i++;
        }


        //Move on to Results Activity after finishing test
        Intent intent = new Intent(this, Results.class);
        startActivity(intent);
        */
        getClient().sendReady();

        while(!scanned) { }

        Log.d(tag, "Finished waiting");
        //Update Status after scan
        scanStatus.setText("Scan Complete");

    }


}
