package com.example.leeseungchan.android_a;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.AuthFailureError;

import org.json.JSONException;
import org.json.JSONObject;

public class Results extends AppCompatActivity {

    ResultContent stair;
    ResultContent elevator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        stair = findViewById(R.id.result_stair);
        elevator = findViewById(R.id.result_elevator);

        String data = getIntent().getStringExtra("timeData");
        try{
            JSONObject json = new JSONObject(data);
            jsonResponse(json);
        } catch (JSONException e){
            e.printStackTrace();
        }
    }

    protected void jsonResponse(JSONObject json) throws JSONException {
        String stairTime = json.getString("stair");
        String elevatorTime = json.getString("elevator");

        stair.time.setText(stairTime);
        elevator.time.setText(elevatorTime);
    }
}
