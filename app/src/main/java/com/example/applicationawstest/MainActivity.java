package com.example.applicationawstest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.*;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    Button button;
    TextView textView;
    String serverUrl = ""; //Insert your aws server address here!

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button)findViewById(R.id.buttonTest);
        textView = (TextView)findViewById(R.id.textViewTest);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);

                JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.GET, serverUrl, null,

                        response -> {
                            try {
                                String email = response.getString("email");
                                textView.setText(email);
                                requestQueue.stop();
                            } catch (JSONException e) {
                                textView.setText("something with Request is wrong");
                                e.printStackTrace();
                                requestQueue.stop();
                            }
                        },
                        error -> {
                            textView.setText("something with Volley is wrong");
                            error.printStackTrace();
                            requestQueue.stop();
                        }) {
                };
                requestQueue.add(stringRequest);
            }
        });
    }
}