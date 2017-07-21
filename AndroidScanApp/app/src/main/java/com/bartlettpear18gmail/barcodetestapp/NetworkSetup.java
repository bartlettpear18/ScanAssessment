package com.bartlettpear18gmail.barcodetestapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.TextureView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import static com.bartlettpear18gmail.barcodetestapp.Client.setAddress;

public class NetworkSetup extends AppCompatActivity {

    private EditText text;
    private EditText text2;
    private EditText text3;
    private EditText text4;
    private TextView status;
    private String tag = "Debug";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network_setup);
    }

    public void changeIP(View view) {
        text = (EditText) findViewById(R.id.editText);
        text2 = (EditText) findViewById(R.id.editText2);
        text3 = (EditText) findViewById(R.id.editText3);
        text4 = (EditText) findViewById(R.id.editText4);

        status= (TextView) findViewById(R.id.statusText);

        String input =  text.getText().toString() + "." + text2.getText().toString() + "." + text3.getText().toString() + "." + text4.getText().toString();
        Log.d(tag,"Set Host IP in Client to " + input);

        if(setAddress(input)) {
            status.setText("Host IP set to: " + input);
        } else {
            status.setText("IP Input rejected. Please try again");
        }
        status.setVisibility(View.VISIBLE);
    }

}