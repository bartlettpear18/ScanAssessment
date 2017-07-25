package com.bartlettpear18gmail.barcodetestapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.InterruptedIOException;
import java.util.ArrayList;

import static com.bartlettpear18gmail.barcodetestapp.MainActivity.getClient;

public class Results extends AppCompatActivity {

    private ListView list;
    private ArrayAdapter adapter;
    private ArrayList<String> array = new ArrayList<String>();
    private String tag = "Debug";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);


        for(int i = 0; i < 5; i++ ) {
            array.add("Scan Data");
        }

        setList();

    }

    public void setList() {
        list = (ListView) findViewById(R.id.listView);
        adapter = new ArrayAdapter<String>(this, R.layout.list, array);
        list.setAdapter(adapter);
    }

    public void csv(View view) throws InterruptedIOException { getClient().sendCSV(); }
}
