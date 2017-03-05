package com.example.movieexplorer;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity {

    Button submit;
    TextView textView;
    EditText title;
    EditText year;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        submit = (Button) findViewById(R.id.button);
        textView = (TextView) findViewById(R.id.textView);
        title = (EditText) findViewById(R.id.editText);
        year = (EditText) findViewById(R.id.editText);

        submit.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View v) {
                                          try {
                                              String movie_title = title.getText().toString();
                                              if(movie_title.length() != 0){
                                                  getData(movie_title);
                                              }else{
                                                  textView.setText("Please enter a movie title");
                                              }

                                          } catch (Exception e) {
                                              e.printStackTrace();
                                          }
                                      }
                                  }
        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void getData(String movie_title){

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://www.omdbapi.com/?t=" + movie_title + "&type=movie";

        // Request a string response from the provided URL.
        JsonObjectRequest jsonRequest = new JsonObjectRequest(url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Display the first 500 characters of the response string.
                        try{
                            if(response.get("Response").toString().equals("True")){
                                //textView.setText(response.get("Title").toString());
                                //textView.setText(response.toString(1));
                                Intent intent = new Intent(getApplicationContext(), MovieActivity.class).putExtra("movie", response.toString());
                                startActivity(intent);
                            }else{
                                textView.setText(response.get("Error").toString());
                            }

                        }catch(JSONException e){
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                   @Override
                   public void onErrorResponse(VolleyError error) {
                       textView.setText("That didn't work!");
                   }
                });

        // Add the request to the RequestQueue.
        queue.add(jsonRequest);
    }
}
