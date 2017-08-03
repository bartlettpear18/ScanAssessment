package com.bartlettpear18gmail.barcodetestapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.ArrayList;

import static com.bartlettpear18gmail.barcodetestapp.MainActivity.*;
import static com.bartlettpear18gmail.barcodetestapp.MainActivity.getClient;

public class Results extends AppCompatActivity {

    private ListView list;
    private ArrayAdapter adapter;
    private String tag = "Debug";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        setList();

    }

    @Override
    public void onStop() {
        super.onStop();
        try {
            getClient().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        getClient().cancel(true);
        setNull();
        System.gc();

    }
    public void setList() {
        list = (ListView) findViewById(R.id.listView);
        adapter = new ArrayAdapter<String>(this, R.layout.list, getClient().getResults());
        list.setAdapter(adapter);
    }

    public void csv(View view) throws InterruptedIOException { getClient().sendCSV(); }
}
