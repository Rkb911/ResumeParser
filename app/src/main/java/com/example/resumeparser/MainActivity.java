package com.example.resumeparser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class MainActivity extends AppCompatActivity {

    private static final String urlFetch = "https://resume-parserapp.herokuapp.com/api/resume-list/";

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        processData();

    }

    // sample change
    // sample change 2


    public void processData() {


        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());


        StringRequest request=new StringRequest(urlFetch, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                GsonBuilder builder=new GsonBuilder();
                Gson gson=builder.create();
                Models data[] = gson.fromJson(response, Models[].class);

                MyAdapter adapter = new MyAdapter(data);
                recyclerView.setAdapter(adapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_LONG).show();
            }
        }
        );

        queue.add(request);



       /* JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                GsonBuilder builder = new GsonBuilder();
                Gson gson = builder.create();
                Models data[];


                try {

                    for (int i = 0 ; i < response.length() ; i++) {

                        JSONObject jsonObject = response.getJSONObject(i);

                        // Toast.makeText(MainActivity.this, jsonObject.toString(), Toast.LENGTH_LONG).show();

                        data = gson.fromJson(String.valueOf(jsonObject), Models[].class);

                        MyAdapter myAdapter = new MyAdapter(data);

                    }



                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Server is offline", Toast.LENGTH_LONG).show();

            }
        });

        queue.add(jsonArrayRequest);  */

    }
}