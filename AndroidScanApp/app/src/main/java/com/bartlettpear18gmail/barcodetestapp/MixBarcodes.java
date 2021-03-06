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

public class MixBarcodes extends AppCompatActivity {

    private ImageView image;
    private TextView currentSym;

    private Generator gen = null;
    private int i = 0;
    private String tag = "Debug";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mix_barcodes);
        Log.d(tag, "Starting Mix Activity");

        try {
            getClient().execute();
            gen = new Generator();
            gen.makeMix();
            Log.d(tag, "Code made");


            image = (ImageView) findViewById(R.id.imageView2);
            currentSym = (TextView) findViewById(R.id.currentSym);

        } catch (WriterException e) {
            e.printStackTrace();
        }

    }

    public void start(View view) throws WriterException, InterruptedException, IOException {

        String sym;

        if (i < gen.getMix().size()) {
            image.setImageBitmap(gen.getMix().get(i));
            Log.d(tag, "Set image");

            sym = "Current Symbology: " + gen.getFormats().get(i);
            currentSym.setText(sym + " " + (i+1));

            //Tell main barcode is displayed
            getClient().sendReady(gen.getCodes().get(i));
            Log.d(tag, "Sending ready");
            i++;
        } else {
            currentSym.setText("Test finished. View results on desktop app");
        }


    }
}
