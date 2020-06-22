package com.example.healthmonitor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

import static com.example.healthmonitor.MainActivity.exercisesList;

public class History extends AppCompatActivity {


    BottomNavigationView bnv;

    String actFromMain, durFromMain;


    ListView listView;
    ArrayAdapter<String> adapter;
    ArrayList<String> mExercisesList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        listView = (ListView) findViewById(R.id.historyList);

        actFromMain = getIntent().getStringExtra("ACTIVITY");
        durFromMain = getIntent().getStringExtra("DURATION");

        //mExercisesList = getIntent().getStringArrayListExtra("exercisesList");
        mExercisesList = exercisesList;
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,mExercisesList);
        listView.setAdapter(adapter);



        bnv = (BottomNavigationView) findViewById(R.id.navigation);

        bnv.setSelectedItemId(R.id.navigation_history);

        bnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_select:
                        Intent i = new Intent(History.this,MainActivity.class);
                        i.putStringArrayListExtra("previous",mExercisesList);
                        startActivity(i);
                        return true;
                    case R.id.navigation_body:
                        Intent i2 = new Intent(History.this,BodyPartsActivity.class);
                        i2.putExtra("ACTIVITY",actFromMain);
                        i2.putExtra("DURATION",durFromMain);
                        startActivity(i2);
                        return true;
                    case R.id.navigation_history:

                        return true;
                }
                return false;
            }
        });


    }
}
