package com.example.leeseungchan.android_a;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class UserInput extends AppCompatActivity {

    protected InfoView buildingId;
    protected InfoView currentFloor;
    protected InfoView targetFloor;

    private String urls = "http://10.210.60.115:3000/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        buildingId = findViewById(R.id.building_id);
        currentFloor = findViewById(R.id.current_floor);
        targetFloor = findViewById(R.id.target_floor);

        Button button = findViewById(R.id.send_user_input);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] request = new String[3];
                request[0] = buildingId.infoExample.getText().toString();
                request[1] = currentFloor.infoExample.getText().toString();
                request[2] = targetFloor.infoExample.getText().toString();

                System.out.println("get data");
                sendJsonRequest(urls,request);
            }
        });

        if(AppHelper.requestQueue == null){
            AppHelper.requestQueue = Volley.newRequestQueue(getApplicationContext());
        }
    }

    private void sendJsonRequest(String url, String[] requests){
        JSONObject json = new JSONObject();
        try {
            json.put("buildingNum",requests[0]);
            json.put("currentFloor", requests[1]);
            json.put("targetFloor", requests[2]);
        } catch(JSONException e){
            e.printStackTrace();
        }

        System.out.println(json);

        JsonObjectRequest request2Server = new JsonObjectRequest(
                Request.Method.POST,
                url,
                json,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.println(response);
                        Intent intent = new Intent(getApplicationContext(), Results.class);
                        intent.putExtra("timeData", response.toString());
                        startActivity(intent);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println(error.networkResponse);
                    }
                }
        ){
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }
        };

        request2Server.setShouldCache(false);
        AppHelper.requestQueue.add(request2Server);

    }
}
