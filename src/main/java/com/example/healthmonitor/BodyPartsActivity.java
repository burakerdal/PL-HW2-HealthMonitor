package com.example.healthmonitor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import static com.example.healthmonitor.MainActivity.batary;
import static com.example.healthmonitor.MainActivity.brainEnergy;
import static com.example.healthmonitor.MainActivity.bodyEnergy;
import static com.example.healthmonitor.MainActivity.eyesEnergy;
import static com.example.healthmonitor.MainActivity.armsEnergy;
import static com.example.healthmonitor.MainActivity.legsEnergy;


public class BodyPartsActivity extends AppCompatActivity {




    String actFromMain, durFromMain;
    ImageView brainImage,eyesImage,bodyImage,armsImage,legsImage,bat;
    TextView bataryEnergyTV,brainEnergyTV,eyesEnergyTV,bodyEnergyTV,armsEnergyTV,legsEnergyTV,lastActivity;

    BottomNavigationView bnv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_body_parts);


        brainImage= (ImageView) findViewById(R.id.brainBatary);
        eyesImage= (ImageView) findViewById(R.id.eyesBatary);
        bodyImage= (ImageView) findViewById(R.id.bodyBatary);
        armsImage= (ImageView) findViewById(R.id.armsBatary);
        legsImage= (ImageView) findViewById(R.id.legsBatary);
        bat = (ImageView) findViewById(R.id.bataryImage);

        actFromMain = getIntent().getStringExtra("ACTIVITY");
        durFromMain = getIntent().getStringExtra("DURATION");

        bataryEnergyTV=(TextView)findViewById(R.id.bataryEnergyTV);
        brainEnergyTV=(TextView)findViewById(R.id.brainEnergyTV);
        eyesEnergyTV=(TextView)findViewById(R.id.eyesEnergyTV);
        bodyEnergyTV=(TextView)findViewById(R.id.bodyEnergyTV);
        armsEnergyTV=(TextView)findViewById(R.id.armsEnergyTV);
        legsEnergyTV=(TextView)findViewById(R.id.legsEnergyTV);




        lastActivity =(TextView) findViewById(R.id.bpTv);

        if(actFromMain.equalsIgnoreCase("none") && durFromMain.equalsIgnoreCase("none")){
            lastActivity.setText("-");
        }
        else{
            lastActivity.setText(actFromMain+" "+durFromMain+" min");
            int actmin = Integer.valueOf(durFromMain);

            int brainEffect = 0,eyesEffect = 0,bodyEffect = 0,armsEffect = 0,legsEffect = 0,totalEffect = 0;

            if(actFromMain.equalsIgnoreCase("Jogging")){
                brainEffect = 5;
                eyesEffect  = 5;
                bodyEffect  = 5;
                armsEffect  = 10;
                legsEffect = 12;
            }
            if(actFromMain.equalsIgnoreCase("Cycling")){
                brainEffect = 7;
                eyesEffect  = 8;
                bodyEffect  = 6;
                armsEffect  = 9;
                legsEffect = 10;
            }
            if(actFromMain.equalsIgnoreCase("Football")){
                brainEffect = 9;
                eyesEffect  = 7;
                bodyEffect  = 8;
                armsEffect  = 7;
                legsEffect = 13;
            }
            if(actFromMain.equalsIgnoreCase("Course")){
                brainEffect = 12;
                eyesEffect  = 10;
                bodyEffect  = 5;
                armsEffect  = 6;
                legsEffect = 3;

            }
            if(actFromMain.equalsIgnoreCase("Cinema")){
                brainEffect = 9;
                eyesEffect  = 10;
                bodyEffect  = 4;
                armsEffect  = 3;
                legsEffect = 2;

            }
            if(actFromMain.equalsIgnoreCase("Music")){
                brainEffect = 7;
                eyesEffect  = 5;
                bodyEffect  = 4;
                armsEffect  = 5;
                legsEffect = 3;

            }
            if(actFromMain.equalsIgnoreCase("Eating")){
                brainEffect = -5;
                eyesEffect  = -5;
                bodyEffect  = -5;
                armsEffect  = -5;
                legsEffect  = -5;

            }
            if(actFromMain.equalsIgnoreCase("Sleeping")){
                brainEffect = -6;
                eyesEffect  = -8;
                bodyEffect  = -9;
                armsEffect  = -7;
                legsEffect  = -8;

            }

            brainEnergy = brainEnergy - brainEffect*actmin;
            eyesEnergy = eyesEnergy - eyesEffect*actmin;
            bodyEnergy = bodyEnergy - bodyEffect*actmin;
            armsEnergy = armsEnergy - armsEffect*actmin;
            legsEnergy = legsEnergy - legsEffect*actmin;
            totalEffect = brainEffect+eyesEffect+bodyEffect+armsEffect+legsEffect;
            batary = batary -(totalEffect);

            if(brainEnergy>800 ){
                brainImage.setImageDrawable(getResources().getDrawable(R.drawable.batary100));
            }
            if(brainEnergy>600 && brainEnergy<=800){
                brainImage.setImageDrawable(getResources().getDrawable(R.drawable.batary80));
            }
            if(brainEnergy>400 && brainEnergy<=600){
                brainImage.setImageDrawable(getResources().getDrawable(R.drawable.batary60));
            }
            if(brainEnergy>200 && brainEnergy<=400){
                brainImage.setImageDrawable(getResources().getDrawable(R.drawable.batary40));
            }
            if(brainEnergy<=200){
                brainImage.setImageDrawable(getResources().getDrawable(R.drawable.batary20));
            }

            if(eyesEnergy>800 ){
                eyesImage.setImageDrawable(getResources().getDrawable(R.drawable.batary100));
            }
            if(eyesEnergy>600 && eyesEnergy<=800){
                eyesImage.setImageDrawable(getResources().getDrawable(R.drawable.batary80));
            }
            if(eyesEnergy>400 && eyesEnergy<=600){
                eyesImage.setImageDrawable(getResources().getDrawable(R.drawable.batary60));
            }
            if(eyesEnergy>200 && eyesEnergy<=400){
                eyesImage.setImageDrawable(getResources().getDrawable(R.drawable.batary40));
            }
            if(eyesEnergy<=200){
                eyesImage.setImageDrawable(getResources().getDrawable(R.drawable.batary20));
            }

            if(bodyEnergy>800 ){
                bodyImage.setImageDrawable(getResources().getDrawable(R.drawable.batary100));
            }
            if(bodyEnergy>600 && bodyEnergy<=800){
                bodyImage.setImageDrawable(getResources().getDrawable(R.drawable.batary80));
            }
            if(bodyEnergy>400 && bodyEnergy<=600){
                bodyImage.setImageDrawable(getResources().getDrawable(R.drawable.batary60));
            }
            if(bodyEnergy>200 && bodyEnergy<=400){
                bodyImage.setImageDrawable(getResources().getDrawable(R.drawable.batary40));
            }
            if(bodyEnergy<=200){
                bodyImage.setImageDrawable(getResources().getDrawable(R.drawable.batary20));
            }

            if(armsEnergy>800 ){
                armsImage.setImageDrawable(getResources().getDrawable(R.drawable.batary100));
            }
            if(armsEnergy>600 && armsEnergy<=800){
                armsImage.setImageDrawable(getResources().getDrawable(R.drawable.batary80));
            }
            if(armsEnergy>400 && armsEnergy<=600){
                armsImage.setImageDrawable(getResources().getDrawable(R.drawable.batary60));
            }
            if(armsEnergy>200 && armsEnergy<=400){
                armsImage.setImageDrawable(getResources().getDrawable(R.drawable.batary40));
            }
            if(armsEnergy<=200){
                armsImage.setImageDrawable(getResources().getDrawable(R.drawable.batary20));
            }

            if(legsEnergy>800 ){
                legsImage.setImageDrawable(getResources().getDrawable(R.drawable.batary100));
            }
            if(legsEnergy>600 && legsEnergy<=800){
                legsImage.setImageDrawable(getResources().getDrawable(R.drawable.batary80));
            }
            if(legsEnergy>400 && legsEnergy<=600){
                legsImage.setImageDrawable(getResources().getDrawable(R.drawable.batary60));
            }
            if(legsEnergy>200 && legsEnergy<=400){
                legsImage.setImageDrawable(getResources().getDrawable(R.drawable.batary40));
            }
            if(legsEnergy<=200){
                legsImage.setImageDrawable(getResources().getDrawable(R.drawable.batary20));
            }

            if(batary>4000 ){
                bat.setImageDrawable(getResources().getDrawable(R.drawable.batary100));
            }
            if(batary>3000 && batary<=4000){
                bat.setImageDrawable(getResources().getDrawable(R.drawable.batary80));
            }
            if(batary>2000 && batary<=3000){
                bat.setImageDrawable(getResources().getDrawable(R.drawable.batary60));
            }
            if(batary>1000 && batary<=2000){
                bat.setImageDrawable(getResources().getDrawable(R.drawable.batary40));
            }
            if(batary<=1000){
                bat.setImageDrawable(getResources().getDrawable(R.drawable.batary20));
            }

            bataryEnergyTV.setText(String.valueOf(batary));
            brainEnergyTV.setText(String.valueOf(brainEnergy));
            eyesEnergyTV.setText(String.valueOf(eyesEnergy));
            bodyEnergyTV.setText(String.valueOf(bodyEnergy));
            armsEnergyTV.setText(String.valueOf(armsEnergy));
            legsEnergyTV.setText(String.valueOf(legsEnergy));
        }


        bnv = (BottomNavigationView) findViewById(R.id.navigation);

        bnv.setSelectedItemId(R.id.navigation_body);

        bnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_select:
                        Intent i = new Intent(BodyPartsActivity.this,MainActivity.class);
                        startActivity(i);
                        return true;
                    case R.id.navigation_body:

                        return true;
                    case R.id.navigation_history:
                        Intent i3 = new Intent(BodyPartsActivity.this,History.class);
                        i3.putExtra("ACTIVITY",actFromMain);
                        i3.putExtra("DURATION",durFromMain);
                        startActivity(i3);
                        return true;
                }
                return false;
            }
        });










    }
}
