package com.example.movieexplorer;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import org.json.JSONObject;

public class MovieActivity extends AppCompatActivity {

    TextView displayTitle;
    TextView displayYear;
    TextView displayDirector;
    TextView displayPlot;
    TextView displayAwards;
    TextView displayRatings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        displayTitle = (TextView) findViewById(R.id.textView2);
        displayYear = (TextView) findViewById(R.id.textView3);
        displayDirector = (TextView) findViewById(R.id.textView4);
        displayPlot = (TextView) findViewById(R.id.textView5);
        displayAwards = (TextView) findViewById(R.id.textView6);
        displayRatings = (TextView) findViewById(R.id.textView7);

        Intent intent = getIntent();
        JSONObject response = null;
        try{
            response = new JSONObject(intent.getStringExtra("movie"));
            displayTitle.setText(response.get("Title").toString());
            displayYear.setText(response.get("Year").toString());
            displayDirector.setText("Director:\n" + response.get("Director").toString());
            displayPlot.setText("Plot\n" + response.get("Plot").toString());
            if(!response.get("Awards").toString().equals("N/A")){
                displayAwards.setText("Awards:\n" + response.get("Awards").toString().substring(0, response.get("Awards").toString().indexOf('.')));
            }else{
                displayAwards.setText(response.get("Awards").toString());
            }
            displayRatings.setText("imdbRating:\n" + response.get("imdbRating").toString());
        }catch (Exception e){
            e.printStackTrace();
        }







    }

}
