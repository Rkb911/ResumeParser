package com.example.resumeparser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ResumeDetailActivity extends AppCompatActivity {

    TextView pName, pPhone, pEmail, pSkills, pDesignation, pDegree, pCollege, pExperience;

    int fileId ;
    private static final String urlDetail = "https://resume-parserapp.herokuapp.com/api/resume-detail/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resume_detail);

        pName = findViewById(R.id.nameParsed);
        pPhone = findViewById(R.id.phoneParsed);
        pEmail = findViewById(R.id.emailParsed);
        pSkills = findViewById(R.id.skillsParsed);
        pDesignation = findViewById(R.id.designationParsed);
        pDegree = findViewById(R.id.degreeParsed);
        pCollege = findViewById(R.id.collegeParsed);
        pExperience = findViewById(R.id.experienceParsed);

        Intent intent = getIntent();
        fileId = intent.getIntExtra("fileID",0);


        Toast.makeText(ResumeDetailActivity.this, String.valueOf(fileId), Toast.LENGTH_SHORT).show();

        ProcessData();










    }



    public void ProcessData() {

        String DETAIL_URL = urlDetail + fileId + "/";

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());

        StringRequest request=new StringRequest(DETAIL_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                GsonBuilder builder=new GsonBuilder();
                Gson gson=builder.create();
                Models data = gson.fromJson(response, Models.class);

                pName.setText(data.getName());
                pPhone.setText(data.getPhone());
                pEmail.setText(data.getEmail());
                pSkills.setText(data.getSkills());
                pDesignation.setText(data.getDesignation());
                pDegree.setText(data.getDegree());
                pCollege.setText(data.getCollege());
                pExperience.setText(data.getExperience());


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_LONG).show();
            }
        }
        );

        queue.add(request);

    }
}