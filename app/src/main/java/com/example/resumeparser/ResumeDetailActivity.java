package com.example.resumeparser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ResumeDetailActivity extends AppCompatActivity {

    TextView pName, pPhone, pEmail, pSkills, pDesignation, pDegree, pCollege, pExperience;


    Models data[];

    Bundle bundle = getIntent().getExtras();
    int fileId = bundle.getInt("fileID");



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resume_detail);

        data = MyAdapter.sendData();
        pName = findViewById(R.id.nameParsed);
        pPhone = findViewById(R.id.phoneParsed);
        pEmail = findViewById(R.id.emailParsed);
        pSkills = findViewById(R.id.skillsParsed);
        pDesignation = findViewById(R.id.designationParsed);
        pDegree = findViewById(R.id.degreeParsed);
        pCollege = findViewById(R.id.collegeParsed);
        pExperience = findViewById(R.id.experienceParsed);

        pName.setText(data[fileId].getName());
        pPhone.setText(data[fileId].getPhone());
        pEmail.setText(data[fileId].getEmail());
        pSkills.setText(data[fileId].getSkills());
        pDesignation.setText(data[fileId].getDesignation());
        pDegree.setText(data[fileId].getDegree());
        pCollege.setText(data[fileId].getCollege());
        pExperience.setText(data[fileId].getExperience());



    }
}