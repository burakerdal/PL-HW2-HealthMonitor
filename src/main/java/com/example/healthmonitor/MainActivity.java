package com.example.healthmonitor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    public static int batary = 5000;

    public static int brainEnergy = 1000;
    public static int eyesEnergy = 1000;
    public static int armsEnergy = 1000;
    public static int bodyEnergy = 1000;
    public static int legsEnergy = 1000;


    public static ArrayList<String> exercisesList = new ArrayList<String>();
    ArrayList<String> prevList;

    BottomNavigationView bnv;

    String spinner1selected="";
    String spinner2selected="";
    Spinner spinner1, spinner2;
    Button  run;
    EditText duration;

    TextView startTime, startTimePrint;

    Context mContext = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(getIntent().getStringArrayListExtra("prevList")!=null){
            prevList = getIntent().getStringArrayListExtra("prevList");
            exercisesList.addAll(prevList);
        }

        duration = (EditText) findViewById(R.id.actDuration);

        final Calendar calendar = Calendar.getInstance();

        final int hour = calendar.get(Calendar.HOUR_OF_DAY);

        final int minute = calendar.get(Calendar.MINUTE);

        startTime = (TextView) findViewById(R.id.startTimeTV);
        startTimePrint = (TextView) findViewById(R.id.timeTV);
        startTimePrint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(mContext, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        String printHour ="";
                        String printMinute="";
                        if(hourOfDay<10){
                            String zero ="0";
                            String h = String.valueOf(hourOfDay);
                            zero = zero.concat(h);
                            printHour = zero;
                        }
                        else{
                            printHour = String.valueOf(hourOfDay);
                        }

                        if(minute<10){
                            String zero ="0";
                            String h = String.valueOf(minute);
                            zero = zero.concat(h);
                            printMinute = zero;
                        }
                        else {

                            printMinute = String.valueOf(minute);
                        }
                        startTimePrint.setText(printHour+":"+printMinute);

                    }
                },hour,minute,android.text.format.DateFormat.is24HourFormat(mContext));
                timePickerDialog.show();
            }
        });



        bnv = (BottomNavigationView) findViewById(R.id.navigation);

        bnv.setSelectedItemId(R.id.navigation_select);

        bnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_select:

                        return true;
                    case R.id.navigation_body:
                        Intent i2 = new Intent(MainActivity.this,BodyPartsActivity.class);
                        if(spinner2selected.equalsIgnoreCase("(none)")){
                            i2.putExtra("ACTIVITY","none");
                            i2.putExtra("DURATION","none");
                        }
                        else{
                            i2.putExtra("ACTIVITY",spinner2selected);
                            i2.putExtra("DURATION",duration.getText().toString());
                        }
                        startActivity(i2);
                        return true;
                    case R.id.navigation_history:
                        Intent i3 = new Intent(MainActivity.this,History.class);
                        i3.putExtra("ACTIVITY",spinner2selected);
                        i3.putExtra("DURATION",duration.getText().toString());
                        startActivity(i3);
                        return true;
                }
                return false;
            }
        });



        spinner1 = (Spinner) findViewById(R.id.Spinner1);

        spinner2 = (Spinner) findViewById(R.id.Spinner2);


        ArrayAdapter adapter1 = ArrayAdapter.createFromResource(this,R.array.types,R.layout.spinner_item);
        spinner1.setAdapter(adapter1);


        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinner1selected = parent.getItemAtPosition(position).toString();
                   if(spinner1selected.equals("(none)")){
                       ArrayAdapter adapter2 = ArrayAdapter.createFromResource(MainActivity.this,R.array.none,R.layout.spinner_item);
                       spinner2.setAdapter(adapter2);

                   }
                   else if(spinner1selected.equals("Physical")){
                       ArrayAdapter adapter2 = ArrayAdapter.createFromResource(MainActivity.this,R.array.Physical,R.layout.spinner_item);
                       spinner2.setAdapter(adapter2);
                   }
                   else if (spinner1selected.equals("Non-physical")){
                    ArrayAdapter adapter2 = ArrayAdapter.createFromResource(MainActivity.this,R.array.Non_physical,R.layout.spinner_item);
                    spinner2.setAdapter(adapter2);
                   }
                   else if (parent.getItemAtPosition(position).toString().equals("Regular")){
                       ArrayAdapter adapter2 = ArrayAdapter.createFromResource(MainActivity.this,R.array.Regular,R.layout.spinner_item);
                       spinner2.setAdapter(adapter2);
                   }




            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinner2selected = parent.getItemAtPosition(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        run = (Button) findViewById(R.id.runButton);
        run.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               //Toast.makeText(getApplicationContext(),""+calendar.getTime(),Toast.LENGTH_LONG).show();
                if(spinner1selected.equals("(none)")){
                    Toast.makeText(getApplicationContext(),"Please should choose activity type !",Toast.LENGTH_SHORT).show();
                }
                else{
                    if(duration.getText().toString().equals("")){
                        Toast.makeText(getApplicationContext(),"Please should define activity duration !",Toast.LENGTH_SHORT).show();
                    }
                    else{

                        String exercise = "   "+startTimePrint.getText().toString()+"     "+spinner1selected+"      "+spinner2selected+"    "+duration.getText().toString()+" min";
                        exercisesList.add(exercise);
                        Toast.makeText(getApplicationContext(),spinner2selected+" "+duration.getText().toString()+" min",Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });







    }


}
