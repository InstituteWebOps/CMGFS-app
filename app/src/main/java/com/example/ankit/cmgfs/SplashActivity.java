package com.example.ankit.cmgfs;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        final EditText et = (EditText) findViewById(R.id.password);
        Button login = (Button) findViewById(R.id.login);

        if (Utils.getprefString("authentication", SplashActivity.this).equals("true")) {
            int SPLASH_TIME_OUT = 2000;
            et.setText("Welcome !");
            et.setEnabled(false);
            login.setClickable(false);
            login.setVisibility(View.INVISIBLE);
            new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

                @Override
                public void run() {
                    // This method will be executed once the timer is over
                    // Start your app main activity
                    String cls = checkIntent(getIntent());
                    Intent i = new Intent(SplashActivity.this, MainActivity.class);//LoginActivity.class);
                    i.putExtra("class", cls);
                    startActivity(i);

                    // close this activity
                    finish();
                }
            }, SPLASH_TIME_OUT);
        } else {
            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (et.getText().toString().equals("1619")) {
                        Utils.saveprefString("authentication", "true", getBaseContext());
                        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                        startActivity(intent);
                    } else
                        Toast.makeText(SplashActivity.this, "Bad password :/", Toast.LENGTH_SHORT).show();
                }
            });
        }

    }

    /*
    public void getPass(){
        final StringRequest stringStudAddRequest = new StringRequest(Request.Method.GET, SUBMIT_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Toast.makeText(SplashActivity.this,"Thanks for your review..", Toast.LENGTH_SHORT).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                String message = null;
                if (error instanceof NetworkError) {
                    message = "Cannot connect to Internet. Please check your connection!!";
                } else if (error instanceof ServerError) {
                    message = "Server down. Please try again after some time!!";
                } else if (error instanceof AuthFailureError) {
                    message = "Authentication error!!";
                } else if (error instanceof ParseError) {
                    message = "Parsing error! Please try again after some time!!";
                } else if (error instanceof TimeoutError) {
                    message = "Connection TimeOut! Please check your internet connection.";
                }
                Toast.makeText(SplashActivity.this.getApplicationContext(), message, Toast.LENGTH_SHORT).show();
            }
        }) {

        };


        MySingleton.getInstance(SplashActivity.this.getApplicationContext()).addToRequestQueue(stringStudAddRequest);

    }
    */

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        checkIntent(intent);
    }

    public String checkIntent(Intent intent) {
        String cls = "HomeActivity";
        if (intent.hasExtra("activity")) {
            cls = intent.getExtras().get("activity").toString();
        }
        return cls;
    }

}